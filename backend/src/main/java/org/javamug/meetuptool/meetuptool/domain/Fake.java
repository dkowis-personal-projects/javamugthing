package org.javamug.meetuptool.meetuptool.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

public final class Fake {
    //Meetings
    public static final Meeting APRIL_MEETING = new Meeting(0, LocalDate.of(2018, Month.APRIL, 11), "Building a scalable application with an In-Memory Data Grid");
    public static final Meeting NOW_MEETING = new Meeting(1, LocalDate.now(), "Test Meeting 1");


    //Attendees
    public static final Attendee DUDE_BRO = new Attendee("dude@bro.com", "Dude Bro", "Unemployed");
    public static final Attendee REAL_PERSON = new Attendee("lol@lol.com", "Real Person", "Wal-Mart");
    public static final Attendee LOGAN = new Attendee("one@two.com", "Logan Smith", "two.com");
    public static final Attendee LARRY = new Attendee("larry@oracle.com", "Larry Ellison", "Oracle");
    public static final Attendee ANDROID = new Attendee("theZuck@facebook.com", "Mark Zuckerberg", "Facebook");
    public static final Attendee CHEETOH = new Attendee("BESTPRESIDENTOFALLTIME@THEWHITEHOUSEISBESTHOUSE.GOV", "DONALD THE BEST TRUMP", "GUBMINT");
    public static final Attendee DAVID = new Attendee("david@kow.is", "David Kowis", "Southwest Airlines");
    public static final Attendee JORGE = new Attendee("jorge@landiv.ar", "Jorge Landivar", "DataDog");


    //Adhoc Prizes
    public static Prize AMAZON(Optional<Attendee> winner) {
     return  new Prize("$50 amazon gift card", winner);
    }

    //Normal Prizes
    public static Prize INTELLIJ(Optional<Attendee> winner) {
        return new Prize("Intellij Toolbox License", winner);
    }
    public static Prize MANNING_BOOK(Optional<Attendee> winner) {
        return new Prize("Manning E-Book", winner);
    }
    public static Prize AGILE_LEARNER(Optional<Attendee> winner) {
        return new Prize("1 Month Agile Learner Subscription", winner);
    }
    public static Prize AMAZON_50_1(Optional<Attendee> winner) {
        return new Prize("$50 Amazon Gift Card", winner);
    }
    public static Prize AMAZON_50_2(Optional<Attendee> winner) {
        return new Prize("$50 Amazon Gift Card", winner);
    }
}
