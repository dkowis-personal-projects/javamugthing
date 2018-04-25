package org.javamug.meetuptool.meetuptool.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    private String email;
    private String name;
    private String company;
}
