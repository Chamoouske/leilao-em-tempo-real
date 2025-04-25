package br.com.leilao.consumer.auction.dto;

import java.math.BigDecimal;

public record BidAuctionDto(Long auctionId, String bidderId, BigDecimal amount, String auctionVersion) {
}