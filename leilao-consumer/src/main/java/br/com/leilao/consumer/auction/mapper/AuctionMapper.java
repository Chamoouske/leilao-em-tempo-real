package br.com.leilao.consumer.auction.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.entity.Auction;

@Mapper(config = AuctionMapperConfig.class)
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);
    Long DEFAULT_VERSION = 1L;
    BigDecimal DEFAULT_CURRENT_BID = BigDecimal.ZERO;

    @Mapping(target = "currentBidder", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "version", expression = "java(DEFAULT_VERSION)")
    @Mapping(target = "createdAt", expression = "java(setCreatedAt(null))")
    @Mapping(target = "currentBid", expression = "java(DEFAULT_CURRENT_BID)")
    Auction toEntity(AuctionDto auctionDto);

    default LocalDateTime setCreatedAt(LocalDateTime localDateTime) {
        Optional<LocalDateTime> offsetDateTime = Optional.ofNullable(localDateTime);
        return offsetDateTime.orElseGet(LocalDateTime::now);
    }
}
