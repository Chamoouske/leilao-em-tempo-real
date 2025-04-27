package br.com.leilao.api.auction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AuctionDto(Long id, String name, String description,
        String status, LocalDateTime endDate, Long version, LocalDateTime createdAt,
        BigDecimal currentBid, String currentBidder) {
}
