package org.javamug.meetuptool.meetuptool.meetings;

import lombok.Data;

@Data
public class MeetingAttendee {
    //TODO: meetup details?
    private String id;

    private String raw;
    private String name;
    private String email;
    private String company;

    private Boolean isValid() {
        //TODO: check to make sure this user object is valid
        return false;
    }
}
