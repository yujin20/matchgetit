package com.matchgetit.backend.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.matchgetit.backend.dto.QAdminMatchRecDTO is a Querydsl Projection type for AdminMatchRecDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAdminMatchRecDTO extends ConstructorExpression<AdminMatchRecDTO> {

    private static final long serialVersionUID = -1814838573L;

    public QAdminMatchRecDTO(com.querydsl.core.types.Expression<Long> matchRecId, com.querydsl.core.types.Expression<? extends com.matchgetit.backend.entity.MemberEntity> member, com.querydsl.core.types.Expression<String> applicationTime, com.querydsl.core.types.Expression<? extends java.util.Date> applicationDate, com.querydsl.core.types.Expression<com.matchgetit.backend.constant.MatchState> matchState, com.querydsl.core.types.Expression<String> team, com.querydsl.core.types.Expression<String> matchScore, com.querydsl.core.types.Expression<String> etc, com.querydsl.core.types.Expression<Long> stadiumId, com.querydsl.core.types.Expression<String> stadiumName, com.querydsl.core.types.Expression<Long> managerId, com.querydsl.core.types.Expression<String> managerName) {
        super(AdminMatchRecDTO.class, new Class<?>[]{long.class, com.matchgetit.backend.entity.MemberEntity.class, String.class, java.util.Date.class, com.matchgetit.backend.constant.MatchState.class, String.class, String.class, String.class, long.class, String.class, long.class, String.class}, matchRecId, member, applicationTime, applicationDate, matchState, team, matchScore, etc, stadiumId, stadiumName, managerId, managerName);
    }

}

