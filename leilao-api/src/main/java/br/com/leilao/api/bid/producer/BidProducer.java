package br.com.leilao.api.bid.producer;

import java.util.function.Consumer;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import br.com.leilao.api.bid.dto.BidAuctionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BidProducer implements Consumer<BidAuctionDto> {
    private final StreamBridge streamBridge;

    @Override
    public void accept(BidAuctionDto bid) {
        log.info("Sending bid to Kafka: {}", bid);
        streamBridge.send("bid-out-0", bid);
    }
}
