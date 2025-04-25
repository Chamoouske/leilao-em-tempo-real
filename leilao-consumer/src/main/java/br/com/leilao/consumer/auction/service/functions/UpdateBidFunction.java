package br.com.leilao.consumer.auction.service.functions;

import java.util.Optional;
import java.util.function.Function;

import br.com.leilao.consumer.auction.dto.BidAuctionDto;
import br.com.leilao.consumer.auction.entity.Auction;
import br.com.leilao.consumer.auction.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateBidFunction implements Function<BidAuctionDto, Auction> {
    private final AuctionRepository repository;

    @Override
    public Auction apply(BidAuctionDto newBidAuctionDto) {
        Optional<Auction> auction = repository.findById(newBidAuctionDto.auctionId());

        auction.ifPresent(a -> {
            a.updateCurrentBid(newBidAuctionDto);
        });

        return auction.orElseThrow(() -> new RuntimeException("Auction not found"));
    }
}
