package com.spring.golub.service;

import com.spring.golub.entity.Schedule;
import com.spring.golub.entity.Ticket;
import com.spring.golub.repository.ScheduleRepository;
import com.spring.golub.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    @Autowired
    UserService userService;
    @Autowired
    ExpositionService expositionService;
    @Autowired private TicketRepository ticketRepository;

    /**
     * ticket buying.
     *
     * @param email String.
     * @param id long.
     */
    @Transactional
    public BigDecimal ticketBuy(String email, Long id) {
        BigDecimal balance = Objects.requireNonNull(userService.findByUserLogin(email).orElse(null)).getBalance();
        BigDecimal price = Objects.requireNonNull(expositionService.findById(id).orElse(null)).getPrice();
        Long user_id = Objects.requireNonNull(userService.findByUserLogin(email).orElse(null)).getId();

        if ( balance.compareTo(new BigDecimal(Double.toString(0.0))) > 0
                && balance.compareTo(price) >= 0){
            userService.alterBalanceById(balance.subtract(price), user_id);
            ticketRepository.createByIds(id, user_id);
            return balance.subtract(price);
        }
        return balance;
    }

    public List<Ticket> getAllTickets(Long id) {
            return ticketRepository.getAll(id);
    }

}
