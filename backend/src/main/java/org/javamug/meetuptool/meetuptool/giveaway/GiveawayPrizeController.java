package org.javamug.meetuptool.meetuptool.giveaway;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/meetings/{meetingId}/giveaway/prizes")
public class GiveawayPrizeController {

    //prizes for this giveaway
    @GetMapping("/")
    @ResponseBody
    public Mono<List<GiveawayPrize>> listPrizes(
            @RequestParam("meetingId") String meetingId) {
        return Mono.empty();
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<GiveawayPrize> addPrize(
            @RequestParam("meetingId") String meetingId,
            @RequestBody GiveawayPrize newPrize) {
        return Mono.empty();
    }

    @PutMapping("/{prizeId}")
    @ResponseBody
    public Mono<GiveawayPrize> addPrize(
            @RequestParam("meetingId") String meetingId,
            @RequestParam("prizeId") String prizeId,
            @RequestBody GiveawayPrize newPrize) {
        return Mono.empty();
    }

    @DeleteMapping("/{prizeId}")
    @ResponseBody
    public Mono<GiveawayPrize> removePrize( //TODO: return type, just want a 201
                                            @RequestParam("meetingId") String meetingId,
                                            @RequestParam("prizeId") String prizeId) {
        return Mono.empty();
    }



}
