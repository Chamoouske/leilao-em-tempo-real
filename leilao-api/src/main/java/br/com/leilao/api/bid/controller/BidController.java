package br.com.leilao.api.bid.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import br.com.leilao.api.bid.dto.BidAuctionDto;
import br.com.leilao.api.bid.service.BidService;
import br.com.leilao.api.publishresponse.dto.PublishResponseDto;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BidController {
    private final BidService service;

    @MutationMapping(value = "createBid")
    public PublishResponseDto bidAuction(@Argument("input") BidAuctionDto bid) {
        return service.createBid(bid);
    }
}
