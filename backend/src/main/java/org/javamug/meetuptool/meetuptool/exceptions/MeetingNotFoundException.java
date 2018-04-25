package org.javamug.meetuptool.meetuptool.exceptions;

public class MeetingNotFoundException extends MeetupToolException {
    public MeetingNotFoundException(int id) {
        super("Unable to find meeting by " + id);
    }
}
