package br.com.leilao.api.bid.dto;

import java.math.BigDecimal;

public record SearchBidDto(Long auctionId, String bidderId, BigDecimal amount) {
}
