package br.com.leilao.consumer.auction.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import br.com.leilao.consumer.auction.dto.BidAuctionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tab_auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "version")
    private Long version;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "current_bid")
    private BigDecimal currentBid;
    @Column(name = "current_bidder")
    private String currentBidder;

    public void updateCurrentBid(BidAuctionDto newBidAuctionDto) {
        if (!Objects.equals(this.version, newBidAuctionDto.auctionVersion())) {
            throw new RuntimeException("Auction version is not valid");
        }

        if (newBidAuctionDto.amount().compareTo(this.currentBid) <= 0) {
            throw new RuntimeException("New bid is not greater than current bid");
        }

        this.currentBid = newBidAuctionDto.amount();
        this.currentBidder = newBidAuctionDto.bidderId();
        this.version++;
    }
}
