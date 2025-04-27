package br.com.leilao.api.auction.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.leilao.api.auction.dto.AuctionDto;
import br.com.leilao.api.auction.entity.Auction;

@Mapper(config = AuctionMapperConfig.class)
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);
    Long DEFAULT_VERSION = 1L;
    BigDecimal DEFAULT_CURRENT_BID = BigDecimal.ZERO;

    @Mapping(target = "endDate", expression = "java(auction.getEndDate())")
    @Mapping(target = "version", defaultExpression = "java(DEFAULT_VERSION)")
    @Mapping(target = "currentBid", defaultExpression = "java(DEFAULT_CURRENT_BID)")
    @Mapping(target = "createdAt", expression = "java(mapLocalDateTime(auction.getCreatedAt()))")
    AuctionDto toDto(Auction auction);

    default LocalDateTime mapLocalDateTime(LocalDateTime localDateTime) {
        Optional<LocalDateTime> offsetDateTime = Optional.ofNullable(localDateTime);
        return offsetDateTime.orElseGet(LocalDateTime::now);
    }
}
