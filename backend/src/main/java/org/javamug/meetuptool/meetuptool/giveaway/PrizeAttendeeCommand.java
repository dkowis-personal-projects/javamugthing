package org.javamug.meetuptool.meetuptool.giveaway;

import lombok.Data;

@Data
public class PrizeAttendeeCommand {
    private String attendeeId;
    private PrizeCommand command;
}
