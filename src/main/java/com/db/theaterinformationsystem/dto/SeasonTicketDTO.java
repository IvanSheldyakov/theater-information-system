package com.db.theaterinformationsystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SeasonTicketDTO {
    private Long id;
    private Long authorId;
    private Long genreId;
    private List<Long> ticketIds;
    private BigDecimal cost;
}
