package br.com.leilao.consumer.auction.service;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.dto.BidAuctionDto;

public interface AuctionService {
    void save(AuctionDto auction);

    void update(BidAuctionDto auction);
}
