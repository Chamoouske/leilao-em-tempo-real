package br.com.leilao.api.auction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import br.com.leilao.api.auction.dto.AuctionDto;
import br.com.leilao.api.auction.service.AuctionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuctionController {
    private final AuctionService service;

    @MutationMapping(value = "new/auction")
    public void createAuction(@Argument AuctionDto det) {
        service.createAuction(det);
    }

    @QueryMapping(value = "list/auction")
    public List<String> stringList() {
        return List.of("1", "2");
    }
}
