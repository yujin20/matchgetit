package com.matchgetit.backend.repository;

import com.matchgetit.backend.dto.AdminMatchRecDTO;
import com.matchgetit.backend.dto.AdminMatchListDTO;

import java.util.List;

public interface MatchRecRepositoryCustom {
    List<AdminMatchListDTO> getMatchListBy();
    List<AdminMatchRecDTO> getMatchInfoBy(String matchDate, String matchTime, Long stadiumId);
}
