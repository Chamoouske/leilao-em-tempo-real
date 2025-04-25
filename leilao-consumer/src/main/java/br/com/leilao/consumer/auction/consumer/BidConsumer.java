package br.com.leilao.consumer.auction.consumer;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import br.com.leilao.consumer.auction.dto.BidAuctionDto;
import br.com.leilao.consumer.auction.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BidConsumer implements Consumer<BidAuctionDto> {
    private final AuctionService auctionService;

    @Override
    public void accept(BidAuctionDto newBidAuctionDto) {
        log.info("Receiving new bid: {}", newBidAuctionDto);
        try {
            auctionService.update(newBidAuctionDto);
            log.info("New bid processed successfully");
        } catch (Exception e) {
            log.error("Error processing bid: {}", e.getMessage(), e);
            throw e;
        }
    }
}
