package br.com.leilao.consumer.auction.service;

import java.util.function.Function;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.dto.BidAuctionDto;
import br.com.leilao.consumer.auction.entity.Auction;
import br.com.leilao.consumer.auction.mapper.AuctionMapper;
import br.com.leilao.consumer.auction.repository.AuctionRepository;
import br.com.leilao.consumer.auction.service.functions.UpdateBidFunction;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository repository;

    @Override
    public void save(AuctionDto auction) {
        repository.save(AuctionMapper.INSTANCE.toEntity(auction));
    }

    @Override
    public void update(BidAuctionDto auction) {
        Function<BidAuctionDto, Auction> updateAuction = new UpdateBidFunction(repository);
        repository.save(updateAuction.apply(auction));
    }
}
