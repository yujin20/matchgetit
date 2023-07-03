package com.matchgetit.backend.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RankDTO {
    private String rankId;

    private String  userId;

    private String groupId;

    private String name;

    private Integer rating;

    private String win;

    private String lose;

    private String vicRating;

    private String rank;

    public RankDTO(String rankId, String userId,String groupId, String name, Integer rating, String win , String lose, String vicRating, String rank){
        this.rankId = rankId;
        this.userId = userId;
        this.groupId = groupId;
        this.name = name;
        this.rating = rating;
        this.win = win;
        this.lose = lose;
        this.vicRating = vicRating;
        this.rank = rank;
    }

}
