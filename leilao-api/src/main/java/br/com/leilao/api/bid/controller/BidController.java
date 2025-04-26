package br.com.leilao.api.bid.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.com.leilao.api.auction.dto.AuctionDto;
import br.com.leilao.api.bid.dto.BidAuctionDto;
import br.com.leilao.api.bid.dto.SearchBidDto;
import br.com.leilao.api.bid.service.BidService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BidController {
    private final BidService service;

    @MutationMapping(value = "bid/auction")
    public void bidAuction(@Argument BidAuctionDto bid) {
        service.createBid(bid);
    }

    @QueryMapping(value = "list/bid")
    public List<AuctionDto> listBid(@Argument SearchBidDto search) {
        return null;
    }
}
