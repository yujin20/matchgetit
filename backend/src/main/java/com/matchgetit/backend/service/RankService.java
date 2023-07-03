package com.matchgetit.backend.service;

import com.matchgetit.backend.dto.RankDTO;
import com.matchgetit.backend.entity.RankEntity;
import com.matchgetit.backend.repository.RankRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankService {

    private final RankRepository rankRepository;

    public String gradeComf(Integer rating){
        String grade ="";
        if(rating>=1001) {
            grade = "E";
        } else if (rating>=851){
            grade = "D";
        } else if (rating>=501){
            grade = "C";
        } else if (rating>=201){
            grade = "B";
        } else {
            grade = "A";
        }
        return grade;
    }


    public String VicRatingComf(String win,String lose){
        int vicRating;
        vicRating = Integer.parseInt(win)*100/(Integer.parseInt(win)+Integer.parseInt(lose));
        return String.valueOf(vicRating);
    }


    public List<RankDTO> getRankList() {
        List<RankEntity> rankEntityList = rankRepository.findRankAll();
        List<RankDTO> rankDTOList = new ArrayList<>();

        for(int i=0; i<rankEntityList.size();i++){
            RankEntity rE = rankEntityList.get(i);
            String grade = gradeComf(rE.getRating());
            RankDTO rankDTO = new RankDTO(rE.getGroupId(), rE.getUserId(), grade, rE.getName(),
                    rE.getRating(), rE.getWin(), rE.getLose(),VicRatingComf(rE.getWin(),
                    rE.getLose())+"%",rE.getRank());
            System.out.println(rE.getRank());
            rankDTOList.add(rankDTO);
        }
        return rankDTOList;
    }
}
