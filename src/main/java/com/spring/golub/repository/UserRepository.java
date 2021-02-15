package com.spring.golub.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.spring.golub.entity.User;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user SET balance=:balance WHERE id=:id", nativeQuery = true)
    void alterBalanceById(BigDecimal balance, Long id);
}
