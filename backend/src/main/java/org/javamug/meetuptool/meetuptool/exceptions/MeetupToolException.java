package org.javamug.meetuptool.meetuptool.exceptions;

public class MeetupToolException extends Exception {
    public MeetupToolException() {
        super();
    }

    public MeetupToolException(String message) {
        super(message);
    }

    public MeetupToolException(String message, Throwable cause) {
        super(message, cause);
    }

    public MeetupToolException(Throwable cause) {
        super(cause);
    }
}
