package com.matchgetit.backend.controller;

import com.matchgetit.backend.dto.AdminMatchInfoDTO;
import com.matchgetit.backend.dto.AdminMatchListDTO;
import com.matchgetit.backend.service.AdminPageMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/matchGetIt/admin")
@RequiredArgsConstructor
public class AdminMatchController {
    private final AdminPageMatchService matchService;

    private final String path = "admin/pages/Match/";
//    private final String alertViewPath = "admin/components/Utils/alert";

    @GetMapping({"/matchList", "/matchList/{page}"})
    public String matchList(Model model) {
        List<AdminMatchListDTO> matchHistories = matchService.getMatchList();
        model.addAttribute("matchHistories", matchHistories);
        return path + "MatchList";
    }

    @GetMapping("matchInfo")
    public String matchInfo(Model model, @RequestParam String matchDate, @RequestParam String matchTime, @RequestParam Long stadiumId) {
        AdminMatchInfoDTO matchInfo = matchService.getMatchInfo(matchDate, matchTime, stadiumId);
        model.addAttribute("game", matchInfo);
        return path + "MatchInfo";
    }

}
