package org.javamug.meetuptool.meetuptool.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class Fake {
    //Meetings
    public static final MeetupEvent APRIL_MEETUP = new MeetupEvent("0", LocalDate.of(2018, Month.APRIL, 11), "Building a scalable application with an In-Memory Data Grid");
    public static final MeetupEvent NOW_MEETUP = new MeetupEvent( "1", LocalDate.now(), "Test MeetupEvent 1");


    //Attendees
    public static final Attendee DUDE_BRO = new Attendee("dude@bro.com", "Dude Bro", "Unemployed");
    public static final Attendee REAL_PERSON = new Attendee("lol@lol.com", "Real Person", "Wal-Mart");
    public static final Attendee LOGAN = new Attendee("one@two.com", "Logan Smith", "two.com");
    public static final Attendee LARRY = new Attendee("larry@oracle.com", "Larry Ellison", "Oracle");
    public static final Attendee ANDROID = new Attendee("theZuck@facebook.com", "Mark Zuckerberg", "Facebook");
    public static final Attendee CHEETOH = new Attendee("BESTPRESIDENTOFALLTIME@THEWHITEHOUSEISBESTHOUSE.GOV", "DONALD THE BEST TRUMP", "GUBMINT");
    public static final Attendee DAVID = new Attendee("david@kow.is", "David Kowis", "Southwest Airlines");
    public static final Attendee JORGE = new Attendee("jorge@landiv.ar", "Jorge Landivar", "DataDog");

    public static final List<Attendee> APRIL_ATTENDEES = Arrays.asList(
            DUDE_BRO,
            REAL_PERSON,
            LOGAN,
            LARRY,
            ANDROID,
            CHEETOH,
            DAVID
    );

    public static final List<Attendee> NOW_ATTENDEES = Arrays.asList(
            DUDE_BRO,
            REAL_PERSON,
            LOGAN,
            LARRY,
            ANDROID,
            CHEETOH,
            DAVID,
            JORGE
    );

    //MeetupEvent Prizes
    public static final List<Prize> APRIL_PRIZES = Arrays.asList(
            AMAZON(Optional.of(DAVID)),
            INTELLIJ(Optional.of(LARRY)),
            AGILE_LEARNER(Optional.of(CHEETOH)),
            AMAZON_50_1(Optional.of(ANDROID)),
            AMAZON_50_2(Optional.of(LOGAN)),
            MANNING_BOOK(Optional.of(DUDE_BRO))
    );

    public static final List<Prize> NOW_PRIZES = Arrays.asList(
            AMAZON(Optional.empty()),
            INTELLIJ(Optional.empty()),
            AGILE_LEARNER(Optional.empty()),
            AMAZON_50_1(Optional.empty()),
            AMAZON_50_2(Optional.empty()),
            MANNING_BOOK(Optional.empty())
    );

    //Adhoc Prizes
    public static Prize AMAZON(Optional<Attendee> winner) {
     return  new Prize(0L, "$50 amazon gift card", winner);
    }

    //Normal Prizes
    public static Prize INTELLIJ(Optional<Attendee> winner) {
        return new Prize(1L,"Intellij Toolbox License", winner);
    }
    public static Prize MANNING_BOOK(Optional<Attendee> winner) {
        return new Prize(2L, "Manning E-Book", winner);
    }
    public static Prize AGILE_LEARNER(Optional<Attendee> winner) {
        return new Prize(3L, "1 Month Agile Learner Subscription", winner);
    }
    public static Prize AMAZON_50_1(Optional<Attendee> winner) {
        return new Prize(4L, "$50 Amazon Gift Card", winner);
    }
    public static Prize AMAZON_50_2(Optional<Attendee> winner) {
        return new Prize(5L, "$50 Amazon Gift Card", winner);
    }
}
