package br.com.leilao.api.bid.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record SearchBidDto(Long auctionId, String bidderId, BigDecimal amount, String description) {
}
