package br.com.leilao.api.auction.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import br.com.leilao.api.auction.entity.Auction;
import br.com.leilao.api.bid.dto.SearchBidDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuctionSpecification implements Specification<Auction> {
    private final SearchBidDto search;

    @Override
    public Predicate toPredicate(@NonNull Root<Auction> root, @NonNull CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(
                criteriaBuilder.equal(root.get("id"), search.auctionId()),
                criteriaBuilder.equal(root.get("bidderId"), search.bidderId()),
                criteriaBuilder.equal(root.get("amount"), search.amount()));
    }

    public static Specification<Auction> id(Long auctionId) {
        return (root, query, criteriaBuilder) -> {
            if (auctionId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), auctionId);
        };
    }

    public static Specification<Auction> bidderId(String bidderId) {
        return (root, query, criteriaBuilder) -> {
            if (bidderId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("bidderId"), bidderId);
        };
    }

    public static Specification<Auction> amout(BigDecimal amount) {
        return (root, query, criteriaBuilder) -> {
            if (amount == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("amount"), amount);
        };
    }
}
