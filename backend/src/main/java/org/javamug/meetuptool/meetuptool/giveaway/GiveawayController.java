package org.javamug.meetuptool.meetuptool.giveaway;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @PostMapping("/prizes")
    @ResponseBody
    public Mono<GiveawayPrize> addPrize(
            @RequestParam("meetingId") String meetingId,
            @RequestBody GiveawayPrize newPrize) {
        return Mono.empty();
    }

    @PutMapping("/prizes/{prizeId}")
    @ResponseBody
    public Mono<GiveawayPrize> addPrize(
            @RequestParam("meetingId") String meetingId,
            @RequestParam("prizeId") String prizeId,
            @RequestBody GiveawayPrize newPrize) {
        return Mono.empty();
    }

    @DeleteMapping("/prizes/{prizeId}")
    @ResponseBody
    public Mono<GiveawayPrize> removePrize( //TODO: return type, just want a 201
            @RequestParam("meetingId") String meetingId,
            @RequestParam("prizeId") String prizeId) {
        return Mono.empty();
    }




}
