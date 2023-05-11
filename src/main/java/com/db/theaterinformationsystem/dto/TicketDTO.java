package com.db.theaterinformationsystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDTO {

    private Long id;
    private BigDecimal cost;
    private Integer place;
    private Integer row;
    private Long playId;

}
