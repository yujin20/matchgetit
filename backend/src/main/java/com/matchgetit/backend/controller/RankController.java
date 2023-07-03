package com.matchgetit.backend.controller;

import com.matchgetit.backend.dto.RankDTO;
import com.matchgetit.backend.entity.RankEntity;
import com.matchgetit.backend.repository.RankRepository;
import com.matchgetit.backend.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class RankController {

    @Autowired
    private RankService rankService;

    @GetMapping(value = "/matchGetIt/rank")
    public List<RankDTO> getDRank() {
        List<RankDTO> rankDTOs = rankService.getRankList();
        return rankDTOs;
    }
}
