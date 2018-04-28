package org.javamug.meetuptool.meetuptool.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prize {
    private Long id;
    private String name;
    private Optional<Attendee> winner = Optional.empty();
}
