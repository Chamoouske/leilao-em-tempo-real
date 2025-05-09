package br.com.leilao.api.auction.service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.leilao.api.auction.dto.AuctionDto;
import br.com.leilao.api.auction.mapper.AuctionMapper;
import br.com.leilao.api.auction.repository.AuctionRepository;
import br.com.leilao.api.auction.repository.AuctionSpecification;
import br.com.leilao.api.bid.dto.SearchBidDto;
import br.com.leilao.api.publishresponse.dto.PublishResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionService {
    private final AuctionRepository repository;
    private final Consumer<AuctionDto> producer;

    public PublishResponseDto createAuction(AuctionDto auctionDto) {
        producer.accept(auctionDto);
        return new PublishResponseDto("Auction created successfully", true);
    }

    public List<AuctionDto> listAuction(SearchBidDto search) {
        return repository.findAll(new AuctionSpecification(search)).stream()
                .map(AuctionMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }
}
