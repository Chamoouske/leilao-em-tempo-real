package br.com.leilao.api.auction.producer;

import java.util.function.Consumer;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import br.com.leilao.api.auction.dto.AuctionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuctionProducer implements Consumer<AuctionDto> {
    private final StreamBridge streamBridge;

    @Override
    public void accept(AuctionDto auction) {
        log.info("Sending auction to Kafka: {}", auction);
        streamBridge.send("auction-out-0", auction);
    }
}
