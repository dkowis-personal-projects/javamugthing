package org.javamug.meetuptool.meetuptool;

import lombok.extern.slf4j.Slf4j;
import org.javamug.meetuptool.meetuptool.domain.Attendee;
import org.javamug.meetuptool.meetuptool.domain.MeetingDetails;
import org.javamug.meetuptool.meetuptool.domain.MeetupEvent;
import org.javamug.meetuptool.meetuptool.domain.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static org.javamug.meetuptool.meetuptool.domain.Fake.APRIL_ATTENDEES;
import static org.javamug.meetuptool.meetuptool.domain.Fake.APRIL_MEETUP;
import static org.javamug.meetuptool.meetuptool.domain.Fake.APRIL_PRIZES;

@Service
@Slf4j
public class MeetingDetailsService {

    private final MeetupService meetupService;
    private AtomicLong sequence = new AtomicLong(0);
    private ConcurrentHashMap<String, MeetingDetails> detailsStore = new ConcurrentHashMap<>();

    //TODO: don't allow modification to a completed meeting
    //Save one already completed meeting
    @Autowired
    public MeetingDetailsService(MeetupService meetupService) {
        this.meetupService = meetupService;
        //How did I end up with 3 meetups?
        createMeeting(
                Mono.just(APRIL_MEETUP),
                Mono.just(APRIL_ATTENDEES),
                Mono.just(APRIL_PRIZES)
        ).block();//CONSUME IT
    }

    //Create a meeting given the information.
    public Mono<MeetingDetails> createMeeting(
            Mono<MeetupEvent> meetupMono,
            Mono<List<Attendee>> attendeesMono,
            Mono<List<Prize>> prizesMono) {

        //TODO: If already created, need to tank somehow
        return meetupMono.zipWith(attendeesMono, (meetup, attendeesList) -> {
            MeetingDetails details = new MeetingDetails();
            details.setMeetingId(Long.toString(sequence.getAndIncrement()));
            details.setMeetup(meetup);
            details.setAttendees(attendeesList);
            return details;
        }).zipWith(prizesMono, (details, prizeList) -> {
            //List of prizes need to get new IDs, every time, because each prize is copied ?
            details.setPrizes(prizeList);
            return details;
        }).doOnNext(details -> {
            log.debug("Saving the meeting {}", details);
            //save it! as a side effect, and return it
            detailsStore.put(details.getMeetingId(), details);
        });
    }

    public Mono<List<MeetingDetails>> listMeetings() {
        //Get the meetups from the meetup service as well, and package those into a meeting details

        //TODO: this whole thing feels real bad, need to figure this out...
        return Mono.just(new ArrayList<>(detailsStore.values()))
                .zipWith(meetupService.listRecentMeetups(), (meetingDetails, meetupEvents) -> {
                    //two lists, return the intersection of them
                    List<String> knownMeetingIds = meetingDetails.stream().map(MeetingDetails::getMeetingId).collect(Collectors.toList());
                    List<MeetingDetails> meetupMeetingDetails = meetupEvents.stream()
                            .filter(m -> !knownMeetingIds.contains(m.getId()))
                            .map(event -> {
                                MeetingDetails details = new MeetingDetails();
                                details.setMeetup(event);
                                details.setMeetingId(event.getId());
                                return details;
                            })
                            .collect(Collectors.toList());
                    //feels super gross
                    meetupMeetingDetails.addAll(meetingDetails);
                    //TODO: sort them by date?
                    return meetupMeetingDetails;
                });
    }

    public Mono<MeetingDetails> getMeeting(String meetingId) {
        //TODO: if it's a meetup meeting, and hasn't been created yet....?
        return Mono.justOrEmpty(detailsStore.get(meetingId));
    }

    public Mono<MeetingDetails> addAdhocPrizeToMeeting(String meetingId, Mono<Prize> prizeMono) {
        return getMeeting(meetingId)
                .zipWith(prizeMono, (details, prize) -> {
                    details.getPrizes().add(prize);
                    detailsStore.put(details.getMeetingId(), details);
                    return details;
                });
    }

    public Mono<MeetingDetails> winPrize(String meetingId, long prizeId, Mono<Attendee> winnerMono) {
        return getMeeting(meetingId)
                .filter(meetingDetails -> {
                    return meetingDetails.getPrizes().stream()
                            .anyMatch(prize -> prize.getId() == prizeId);
                })
                .zipWith(winnerMono)
                .filter(tuple -> {
                    return tuple.getT1().getAttendees().stream().anyMatch(attendee -> attendee.equals(tuple.getT2()));
                })
                .map(tuple -> {
                    MeetingDetails details = tuple.getT1();
                    details.getPrizes().stream()
                            .filter(prize -> prize.getId() == prizeId)
                            .forEach(prize -> prize.setWinner(Optional.of(tuple.getT2())));
                    detailsStore.put(details.getMeetingId(), details);
                    return details;
                });
    }

    public Mono<MeetingDetails> commitMeeting(Mono<MeetingDetails> detailsMono) {
        return detailsMono.map(details -> {
            details.setComplete(true);
            detailsStore.put(details.getMeetingId(), details);
            return details;
        });
    }


    //Get a random new winner from the list of folks that haven't won and are present
    public Mono<Attendee> selectRandomWinner(Mono<MeetingDetails> meetingMono, long randomSeed) {
        //Construct a list of attendees that haven't already won a prize, and are present, then get random one
        final Random r = new Random(randomSeed);

        return meetingMono
                .map(meeting -> Tuples.of(meeting.getPrizes(), meeting.getAttendees()))
                .flatMapIterable(t -> {
                    //return only a list of attendees that haven't already won prizes, and not marked absent
                    final List<Attendee> winners = t.getT1().stream().filter(p -> p.getWinner().isPresent())
                            .map(p -> p.getWinner().get())
                            .collect(Collectors.toList());
                    //A list of people who haven't won yet.
                    return t.getT2().stream().filter(a -> !winners.contains(a)).collect(Collectors.toList());
                })
                .filter(a -> a.isPresent())
                .collect(Collectors.toList())
                .map(presentAttendees -> {
                    //Get a random one out of the list
                    return presentAttendees.get(r.nextInt(presentAttendees.size()));
                });
    }

    public Mono<List<Attendee>> markAttendeeNonPresent(String meetingId, Mono<Attendee> attendeeMono) {
        return getMeeting(meetingId).zipWith(attendeeMono, (meetingDetails, attendee) -> {
            //get the attendee out of the list, and replace it with one that's not present
            //TODO: Mutating a list, blech
            //TODO: I'm pretty sure there's a bug here... Might need to use a UUID instead
            meetingDetails.getAttendees().remove(attendee);
            attendee.setPresent(false);
            meetingDetails.getAttendees().add(attendee);

            detailsStore.put(meetingDetails.getMeetingId(), meetingDetails); //Update the save?
            return meetingDetails;
        })
                .map(MeetingDetails::getAttendees);

    }

    public Mono<MeetingDetails> completeMeeting(String meetingId) {
        return getMeeting(meetingId)
                .map(meetingDetails -> {
                    meetingDetails.setComplete(true);

                    detailsStore.put(meetingDetails.getMeetingId(), meetingDetails);
                    return meetingDetails;
                });
    }
}
