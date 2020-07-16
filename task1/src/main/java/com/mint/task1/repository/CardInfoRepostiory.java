package com.mint.task1.repository;

import com.mint.task1.data.CardInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CardInfoRepostiory extends PagingAndSortingRepository<CardInfo, Long> {

    CardInfo save(CardInfo cardInfo);

    Optional<CardInfo> findById(Long id);

    Optional<CardInfo> findByCardNumber(String cardNumber);

    Page<CardInfo> findAll(Pageable pageable);
}
