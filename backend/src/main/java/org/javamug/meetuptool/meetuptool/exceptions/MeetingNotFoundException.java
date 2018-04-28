package org.javamug.meetuptool.meetuptool.exceptions;

public class MeetingNotFoundException extends MeetupToolException {
    public MeetingNotFoundException(String id) {
        super("Unable to find meetup by " + id);
    }
}
