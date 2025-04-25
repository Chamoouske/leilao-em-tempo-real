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
        try {
            log.info("Receiving auction: {}", auction);
            auctionService.save(auction);
            log.info("Auction saved successfully");
        } catch (Exception e) {
            log.error("Error saving auction: {}", e.getMessage(), e);
            throw e;
        }
    }
}
