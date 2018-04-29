package org.javamug.meetuptool.meetuptool;

import lombok.extern.slf4j.Slf4j;
import org.javamug.meetuptool.meetuptool.domain.Attendee;
import org.javamug.meetuptool.meetuptool.domain.MeetingDetails;
import org.javamug.meetuptool.meetuptool.domain.Prize;
import org.javamug.meetuptool.meetuptool.exceptions.MeetingNotFoundException;
import org.javamug.meetuptool.meetuptool.exceptions.MeetupToolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController {


    private final MeetingDetailsService meetingDetailsService;
    private final MeetupService meetupService;
    private final PrizesService prizesService;

    @Autowired
    public ApiController(
            MeetingDetailsService meetingDetailsService,
            MeetupService meetupService,
            PrizesService prizesService) {
        this.meetingDetailsService = meetingDetailsService;
        this.meetupService = meetupService;
        this.prizesService = prizesService;
    }

    @GetMapping("/meetings")
    @ResponseBody
    public Mono<List<MeetingDetails>> listMeetings() {
        return meetingDetailsService.listMeetings();
    }

    @GetMapping("/meetings/{id}")
    @ResponseBody
    public Mono<MeetingDetails> meetingDetails(@PathVariable("id") String id) throws MeetupToolException {
        //TODO: could be a meetup meeting that hasn't been "created" yet
        return meetingDetailsService.getMeeting(id)
                .switchIfEmpty(meetupService.getMeetupById(id).map(meetupEvent -> {
                    MeetingDetails details = new MeetingDetails();
                    details.setMeetingId(meetupEvent.getId());
                    details.setMeetup(meetupEvent);
                    return details;
                }))
                .switchIfEmpty(Mono.error(new MeetingNotFoundException(id)));
    }

    @PostMapping("/meetings/{meetingId}/import")
    @ResponseBody
    public Mono<MeetingDetails> importMeeting(@PathVariable("meetingId") String id) {
        log.debug("importing meeting for {}", id);
        //Time to load all the things
        //Get the events from meetup
        return meetingDetailsService.createMeeting(
                meetupService.listRecentMeetups()
                        .flatMapIterable(p -> p)
                        .filter(event -> event.getId().equals(id))
                        .single(),
                meetupService.attendeesForMeetup(id),
                prizesService.getStandardPrizes()
        );
    }

    @GetMapping("/meetings/{meetingId}/prizes")
    @ResponseBody
    public Mono<List<Prize>> prizesForMeeting(@PathVariable("meetingId") String meetingId) throws MeetupToolException {
        return meetingDetailsService.getMeeting(meetingId)
                .map(MeetingDetails::getPrizes);
    }

    //TODO: add an ad-hoc prize to the meetup  POST /meetings/{meetingId}/prizes
    @PostMapping("/meetings/{meetingId}/prizes")
    @ResponseBody
    public Mono<List<Prize>> addAddHocPrizeToMeeting(@PathVariable("meetingId") String meetingId, @RequestBody Mono<Prize> adhocPrize) throws MeetupToolException {
        return meetingDetailsService.addAdhocPrizeToMeeting(meetingId, adhocPrize)
                .map(MeetingDetails::getPrizes);
    }

    //TODO: update a winner of the prize PUT /meetings/{meetingId}/prizes/{prizeId}
    @PutMapping("/meetings/{meetingId}/prizes/{prizeId}")
    @ResponseBody
    public Mono<List<Prize>> winPrize(@PathVariable("meetingId") String meetingId,
                                      @PathVariable("prizeId") long prizeId,
                                      @RequestBody Mono<Attendee> winner) throws MeetupToolException {

        return meetingDetailsService.winPrize(meetingId, prizeId, winner)
                .map(MeetingDetails::getPrizes);
    }

    //TODO: select a random winner that is present from the meetup's attendees GET /meetings/{meetingId}/randomAttendee
    @GetMapping("/meetings/{meetingId}/randomAttendee")
    @ResponseBody
    public Mono<Attendee> selectRandomAttendee(@PathVariable("meetingId") String meetingId) {
        return meetingDetailsService.selectRandomWinner(meetingDetailsService.getMeeting(meetingId), System.currentTimeMillis());
    }

    //TODO: mark an attendee non-present PUT /meetings/{meetingId}/notPresent}
    @PutMapping("/meetings/{meetingId}/notPresent")
    @ResponseBody
    public Mono<List<Attendee>> nonPresentAttendee(@PathVariable("meetingId") String meetingId, @RequestBody Mono<Attendee> attendeeMono) throws MeetupToolException {
        return meetingDetailsService.markAttendeeNonPresent(meetingId, attendeeMono);
    }

    //TODO: something for "did not want" ? (maybe just to keep a record of it)

    //TODO: Complete prize selection for a meetup POST /meetings/{meetingId}/complete
    @PostMapping("/meetings/{meetingId}/complete")
    @ResponseBody
    public Mono<MeetingDetails> commitMeeting(@PathVariable("meetingId") String meetingId) throws MeetupToolException {
        return meetingDetailsService.completeMeeting(meetingId);
    }
}
