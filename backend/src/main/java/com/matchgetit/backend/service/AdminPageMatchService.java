package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.AdminMatchRecDTO;
import com.matchgetit.backend.dto.AdminMatchInfoDTO;
import com.matchgetit.backend.dto.AdminMatchListDTO;
import com.matchgetit.backend.repository.MatchRecRepository;
import com.matchgetit.backend.repository.MatchRepository;
import com.matchgetit.backend.repository.MatchWaitRepository;
import com.matchgetit.backend.util.FormatDate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminPageMatchService {
    private final MatchRepository matchRepository;
    private final MatchWaitRepository matchWaitRepository;
    private final MatchRecRepository matchRecRepository;
    private final ModelMapper modelMapper;


    public List<AdminMatchListDTO> getMatchList() {
        return matchRecRepository.getMatchListBy();
    }

    public AdminMatchInfoDTO getMatchInfo(String matchDate, String matchTime, Long stadiumId) {
        List<AdminMatchRecDTO> matchInfoList = matchRecRepository.getMatchInfoBy(matchDate, matchTime, stadiumId);
        AdminMatchRecDTO temp = matchInfoList.get(0);

        AdminMatchInfoDTO matchInfo = modelMapper.map(temp, AdminMatchInfoDTO.class);
        matchInfo.setApplicationDate(FormatDate.formatDateToString(temp.getApplicationDate()));

        for (AdminMatchRecDTO dto: matchInfoList) {
            Map<String, String> map = new HashMap<>();
            map.put("memberId", String.valueOf(dto.getMember().getUserId()));
            map.put("memberName", dto.getMember().getName());
            map.put("memberLevel", dto.getMember().getPrfcn().name().toLowerCase());
            map.put("team", dto.getTeam());
            map.put("score", dto.getMatchScore());

            if (dto.getTeam().equalsIgnoreCase("A"))
                matchInfo.getTeamA_Members().add(map);
            else if (dto.getTeam().equalsIgnoreCase("B"))
                matchInfo.getTeamB_Members().add(map);
        }

        matchInfo.getTeamA_Members().get(0).get("team");

        return matchInfo;
    }

}
