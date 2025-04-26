package br.com.leilao.api.bid.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import br.com.leilao.api.auction.dto.AuctionDto;
import br.com.leilao.api.auction.service.AuctionService;
import br.com.leilao.api.bid.dto.BidAuctionDto;
import br.com.leilao.api.bid.dto.SearchBidDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BidService {
    private final Consumer<BidAuctionDto> producer;
    private final AuctionService auctionService;

    public void createBid(BidAuctionDto bid) {
        producer.accept(bid);
    }

    public List<AuctionDto> listBid(SearchBidDto search) {
        return auctionService.listAuction(search);
    }
}
