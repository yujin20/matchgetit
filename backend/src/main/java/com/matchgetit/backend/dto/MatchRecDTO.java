package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.MatchState;
import com.matchgetit.backend.entity.StadiumEntity;
import jakarta.persistence.*;

import java.util.Date;

public class MatchRecDTO {
    private Long matchRecId;
    private Long partyLeaderId;
    private StadiumEntity stadium;
    private String matchScore;
    private Date matchSearchStr;
    private Date matchSearchEnd;
    private String team;
    private String applicationTime;
    private Date applicationDate;
    private Long crd;
    private Long point;
    private MatchState matchState;
    private String etc;
}
