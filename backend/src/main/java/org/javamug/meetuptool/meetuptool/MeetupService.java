package org.javamug.meetuptool.meetuptool;

import org.javamug.meetuptool.meetuptool.domain.Attendee;
import org.javamug.meetuptool.meetuptool.domain.MeetupEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.javamug.meetuptool.meetuptool.domain.Fake.APRIL_MEETUP;
import static org.javamug.meetuptool.meetuptool.domain.Fake.NOW_MEETUP;

@Service
public class MeetupService {
    private final String urlname;
    //TODO: make API calls to meetup, but until then use fake data
    private ConcurrentHashMap<String, MeetupEvent> meetupStore = new ConcurrentHashMap<>();

    public MeetupService(
            @Value("${meetup.api.urlname}") String urlname
    ) {
        this.urlname = urlname;
        //Store some meetups in the in-memory store
        meetupStore.put("0", APRIL_MEETUP);
        meetupStore.put("1", NOW_MEETUP);
    }

    public Mono<MeetupEvent> getMeetupById(String meetupId) {
        return Mono.justOrEmpty(meetupStore.get(meetupId));
    }

    //TODO: will probably want a way to just hand off a buffer, because it probably isn't a streaming API
    public Flux<MeetupEvent> listMeetups() {
        return Flux.fromIterable(meetupStore.values());
    }

    //This is probably more useful, get the 12 latest meetups or something, or some range
    //Probably also list meetups past a date range
    public Mono<List<MeetupEvent>> listRecentMeetups() {
        return Mono.just(new ArrayList<>(meetupStore.values()));
    }

    //Get the attendees from the meetup API
    public Mono<List<Attendee>> attendeesForMeetup(String meetupId) {
        return Mono.empty();
    }
}
