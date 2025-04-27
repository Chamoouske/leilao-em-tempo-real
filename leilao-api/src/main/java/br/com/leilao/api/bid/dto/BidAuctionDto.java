package br.com.leilao.api.bid.dto;

import java.math.BigDecimal;

public record BidAuctionDto(Long auctionId, String bidderId, BigDecimal amount, Long auctionVersion) {
}