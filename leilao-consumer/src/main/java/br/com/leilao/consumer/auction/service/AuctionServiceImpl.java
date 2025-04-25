package br.com.leilao.consumer.auction.service;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.mapper.AuctionMapper;
import br.com.leilao.consumer.auction.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository repository;

    @Override
    public void save(AuctionDto auction) {
        repository.save(AuctionMapper.INSTANCE.toEntity(auction));
    }

    @Override
    public void update(AuctionDto auction) {
        repository.save(AuctionMapper.INSTANCE.toEntity(auction));
    }
}
