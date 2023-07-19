package com.matchgetit.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class AdminSearchMatchDTO {
    private String searchDateStart;
    private String searchDateEnd;
    private String searchMatchDate;
    private String searchMatchTime;
    private Long stadiumId;
}
