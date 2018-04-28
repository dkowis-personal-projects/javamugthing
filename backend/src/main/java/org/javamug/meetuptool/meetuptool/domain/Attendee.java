package org.javamug.meetuptool.meetuptool.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {

    public Attendee(String email, String name, String company) {
        this(email, name, company, true);
    }

    private String email;
    private String name;
    private String company;
    private boolean present = true; //present until marked absent
}
