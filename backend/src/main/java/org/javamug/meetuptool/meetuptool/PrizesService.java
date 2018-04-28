package org.javamug.meetuptool.meetuptool;

import org.javamug.meetuptool.meetuptool.domain.Prize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static org.javamug.meetuptool.meetuptool.domain.Fake.*;

@Service
public class PrizesService {

    //Technically the prize store would be per meetup group
    private ConcurrentHashMap<Long, Prize> prizeStore = new ConcurrentHashMap<>();

    public PrizesService() {

        //Put our default prizes in there
        Arrays.stream(new Prize[]{
                INTELLIJ(Optional.empty()),
                MANNING_BOOK(Optional.empty()),
                AGILE_LEARNER(Optional.empty()),
                AMAZON_50_1(Optional.empty()),
                AMAZON_50_2(Optional.empty())
        }).forEach(prize -> {
            prizeStore.put(prize.getId(), prize);
        });
    }

    public Mono<List<Prize>> getStandardPrizes() {
        return Mono.just(new ArrayList<>(prizeStore.values()));
    }

    //Add standard prize
    //remove standard prize
}
