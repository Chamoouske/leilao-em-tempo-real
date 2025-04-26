package br.com.leilao.api.publishresponse.dto;

public record PublishResponseDto(String message, Boolean success, Long auctionId) {
}
