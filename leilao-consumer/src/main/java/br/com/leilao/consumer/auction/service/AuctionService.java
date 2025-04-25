package br.com.leilao.consumer.auction.service;

import br.com.leilao.consumer.auction.dto.AuctionDto;

public interface AuctionService {
    void save(AuctionDto auction);

    void update(AuctionDto auction);
}
