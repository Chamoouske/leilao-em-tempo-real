package br.com.leilao.consumer.auction.service;

import org.springframework.stereotype.Service;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.dto.BidAuctionDto;
import br.com.leilao.consumer.auction.repository.AuctionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuctionServiceLogger implements AuctionService {
    private final AuctionService auctionService;

    public AuctionServiceLogger(AuctionRepository repository) {
        this.auctionService = new AuctionServiceImpl(repository);
    }

    @Override
    @Transactional
    public void save(AuctionDto auction) {
        log.info("Saving Auction: {}", auction);
        auctionService.save(auction);
    }

    @Override
    public void update(BidAuctionDto auction) {
        log.info("Updating Auction: {}", auction);
        auctionService.update(auction);
    }
}
