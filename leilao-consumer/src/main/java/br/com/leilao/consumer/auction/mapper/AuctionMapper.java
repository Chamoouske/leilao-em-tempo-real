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

    @Mapping(target = "version", expression = "java(auctionDto.auctionVersion())")
    @Mapping(target = "createdAt", expression = "java(mapLocalDateTime(auctionDto.createdAt()))")
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
}
