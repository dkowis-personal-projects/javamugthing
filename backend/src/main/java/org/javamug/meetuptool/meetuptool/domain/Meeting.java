package org.javamug.meetuptool.meetuptool.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

    private Integer id;
    private LocalDate date;
    private String topic;
}
