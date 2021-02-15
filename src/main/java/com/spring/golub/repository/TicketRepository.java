package com.spring.golub.repository;

import com.spring.golub.entity.Schedule;
import com.spring.golub.entity.Ticket;
import com.spring.golub.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ticket (id_exp, id) VALUES (:exp_id, :user_id)", nativeQuery = true)
    void createByIds(Long exp_id, Long user_id);

    @Query(value = "SELECT * FROM TICKET, EXPOSITION, USER WHERE ticket.id_exp = exposition.id_exp and user.id = :id" +
            " ORDER BY ticket.ticket_id desc", nativeQuery = true)
    List<Ticket> getAll(@Param("id") Long id);
}
