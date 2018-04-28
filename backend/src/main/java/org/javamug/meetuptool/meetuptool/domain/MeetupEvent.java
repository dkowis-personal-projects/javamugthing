package org.javamug.meetuptool.meetuptool.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetupEvent {

    private String id;
    private LocalDate date;
    private String topic;
}
