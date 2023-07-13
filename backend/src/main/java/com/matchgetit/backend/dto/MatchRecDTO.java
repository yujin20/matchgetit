package com.matchgetit.backend.dto;

import com.matchgetit.backend.constant.MatchState;
import com.matchgetit.backend.entity.MemberEntity;
import com.matchgetit.backend.entity.StadiumEntity;
import jakarta.persistence.*;

import java.util.Date;

public class MatchRecDTO {
    private Long matchRecId;
    private MemberEntity partyLeader;
    private StadiumEntity stadium;
    //matchWait 칼럼 외래키
    private MemberEntity manager;//매니저 id ( = 유저id )
    private String matchScore;
    private Date matchSearchStr;
    private Date matchSearchEnd;
    private String team;
    private String applicationTime;
    private Date applicationDate;
    private Long crd;
    private MatchState matchState;
    private String etc;
}
