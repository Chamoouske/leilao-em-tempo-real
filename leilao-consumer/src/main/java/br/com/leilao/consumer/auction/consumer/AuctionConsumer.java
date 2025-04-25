package br.com.leilao.consumer.auction.consumer;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuctionConsumer implements Consumer<AuctionDto> {
    private final AuctionService auctionService;

    @Override
    public void accept(AuctionDto auction) {
        log.info("Auction: {}", auction);
        auctionService.save(auction);
    }
}
