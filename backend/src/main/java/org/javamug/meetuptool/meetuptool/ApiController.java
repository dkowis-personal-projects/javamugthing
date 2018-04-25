package org.javamug.meetuptool.meetuptool;

import org.javamug.meetuptool.meetuptool.domain.Meeting;
import org.javamug.meetuptool.meetuptool.domain.MeetingDetails;
import org.javamug.meetuptool.meetuptool.exceptions.MeetingNotFoundException;
import org.javamug.meetuptool.meetuptool.exceptions.MeetupToolException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.javamug.meetuptool.meetuptool.domain.Fake.*;

@Controller
@RequestMapping("/api")
public class ApiController {


    @GetMapping("/meetings")
    @ResponseBody
    public Mono<List<Meeting>> listMeetings() {

        //TODO: query the meetings list from meetup.com and the database?
        return Mono.just(Arrays.asList(
                APRIL_MEETING, NOW_MEETING
        ));
    }

    @GetMapping("/meetings/{id}")
    @ResponseBody
    public Mono<MeetingDetails> meetingDetails(@PathVariable("id") int id) throws MeetupToolException {

        //TODO: implement with the meetup api, and my database
        MeetingDetails details = new MeetingDetails();
        if (id == 0) {
            details.setPrizes(Arrays.asList(
                    AMAZON(Optional.of(DAVID)),
                    INTELLIJ(Optional.of(LARRY)),
                    AGILE_LEARNER(Optional.of(CHEETOH)),
                    AMAZON_50_1(Optional.of(ANDROID)),
                    AMAZON_50_2(Optional.of(LOGAN)),
                    MANNING_BOOK(Optional.of(DUDE_BRO))
            ));
            details.setAttendees(Arrays.asList(
                    DUDE_BRO,
                    REAL_PERSON,
                    LOGAN,
                    LARRY,
                    ANDROID,
                    CHEETOH,
                    DAVID
            ));
        } else if (id == 1) {
            details.setPrizes(Arrays.asList(
                    AMAZON(Optional.empty()),
                    INTELLIJ(Optional.empty()),
                    AGILE_LEARNER(Optional.empty()),
                    AMAZON_50_1(Optional.empty()),
                    AMAZON_50_2(Optional.empty()),
                    MANNING_BOOK(Optional.empty())
            ));
            details.setAttendees(Arrays.asList(
                    DUDE_BRO,
                    REAL_PERSON,
                    LOGAN,
                    LARRY,
                    ANDROID,
                    CHEETOH,
                    DAVID
            ));
        } else {
            throw new MeetingNotFoundException(id);
        }


        return Mono.just(details);
    }
}
