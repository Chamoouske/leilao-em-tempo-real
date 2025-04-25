package br.com.leilao.api.auction.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuctionController {
    @MutationMapping(value = "new/auction")
    public void publishAuctionMessage(@Argument String det) {
        System.out.println(det);
    }

    @QueryMapping(value = "list/auction")
    public List<String> stringList() {
        return List.of("1", "2");
    }
}
