package org.javamug.meetuptool.meetuptool.giveaway;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/meetings/{meetingId}/giveaway")
public class GiveawayController {

    @GetMapping("/")
    @ResponseBody
    public Mono<GiveawayDetails> giveawayDetails(@RequestParam("meetingId") String meetingId) {
        return Mono.empty();
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<GiveawayDetails> startGiveaway(
            @RequestParam("meetingId") String meetingId,
            @RequestBody GiveawayTrigger payload) {
        return Mono.empty();
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<GiveawayDetails> completeGiveaway(
            @RequestParam("meetingId") String meetingId,
            @RequestBody GiveawayTrigger payload) {
        return Mono.empty();
    }

    //prizes for this giveaway
    @GetMapping("/prizes")
    @ResponseBody
    public Mono<List<GiveawayPrize>> listPrizes(
            @RequestParam("meetingId") String meetingId) {
        return Mono.empty();
    }

}
