package br.com.leilao.consumer.auction.mapper;

import java.time.LocalDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.entity.Auction;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    Auction toEntity(AuctionDto auctionDto);

    AuctionDto toDto(Auction auction);

    default LocalDateTime mapLocalDateTime(LocalDateTime localDateTime) {
        Optional<LocalDateTime> offsetDateTime = Optional.ofNullable(localDateTime);
        return offsetDateTime.orElseGet(LocalDateTime::now);
    }
}
