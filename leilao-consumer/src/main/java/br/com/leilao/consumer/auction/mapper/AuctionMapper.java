package br.com.leilao.consumer.auction.mapper;

import java.time.LocalDateTime;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.leilao.consumer.auction.dto.AuctionDto;
import br.com.leilao.consumer.auction.entity.Auction;

@Mapper
public interface AuctionMapper {
    AuctionMapper INSTANCE = Mappers.getMapper(AuctionMapper.class);

    @Mapping(target = "version", expression = "java(mapVersion(auctionDto.version()))")
    @Mapping(target = "createdAt", expression = "java(mapLocalDateTime(auctionDto.createdAt()))")
    @Mapping(target = "currentBid", expression = "java(mapCurrentBid(auctionDto.currentBid()))")
    Auction toEntity(AuctionDto auctionDto);

    AuctionDto toDto(Auction auction);

    default LocalDateTime mapLocalDateTime(LocalDateTime localDateTime) {
        Optional<LocalDateTime> offsetDateTime = Optional.ofNullable(localDateTime);
        return offsetDateTime.orElseGet(LocalDateTime::now);
    }

    default Long mapVersion(Long version) {
        Optional<Long> offsetVersion = Optional.ofNullable(version);
        return offsetVersion.orElse(1L);
    }

    default Long mapCurrentBid(Long currentBid) {
        Optional<Long> offsetCurrentBid = Optional.ofNullable(currentBid);
        return offsetCurrentBid.orElse(0L);
    }
}
