package org.javamug.meetuptool.meetuptool.meetings;

import org.javamug.meetuptool.meetuptool.domain.MeetingDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class MeetingController {


    @GetMapping("/meetings")
    @ResponseBody
    public Mono<List<MeetingDetails>> listMeetings() {
        return Mono.empty();
    }

    @GetMapping("/meetings/{id}")
    @ResponseBody
    public Mono<MeetingDetails> meetingDetails(@RequestParam("id") String meetingId) {
        return Mono.empty();
    }

    //Meeting attendees controller?

    @PostMapping("/importMeeting")
    @ResponseBody
    public Mono<MeetingDetails> importMeeting(@RequestBody Mono<ImportMeetingRequest> imr) {
        return Mono.empty();
    }

    @GetMapping("/meetings/{meetingId}/attendees")
    @ResponseBody
    public Mono<List<MeetingAttendee>> listAttendeesForMeeting(@RequestParam("meetingId") String meetingId) {
        return Mono.empty();
    }

    @PostMapping("/meetings/{meetingId}/attendees")
    @ResponseBody
    public Mono<MeetingAttendee> attendeeDetails(@RequestParam("meetingId") String meetingId) {
        return Mono.empty();
    }

    @PutMapping("/meetings/{meetingId}/attendees/{attendeeId}")
    @ResponseBody
    public Mono<MeetingAttendee> updateAttendee(
            @RequestParam("meetingId") String meetingId,
            @RequestParam("attendeeId") String attendeeId,
            @RequestBody Mono<MeetingAttendee> meetingAttendeeMono) {
        return Mono.empty();
    }
}
