package com.matchgetit.backend.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.matchgetit.backend.dto.QAdminMatchListDTO is a Querydsl Projection type for AdminMatchListDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAdminMatchListDTO extends ConstructorExpression<AdminMatchListDTO> {

    private static final long serialVersionUID = -1324767939L;

    public QAdminMatchListDTO(com.querydsl.core.types.Expression<String> matchTime, com.querydsl.core.types.Expression<? extends java.util.Date> matchDate, com.querydsl.core.types.Expression<com.matchgetit.backend.constant.MatchState> matchState, com.querydsl.core.types.Expression<Long> stadiumId, com.querydsl.core.types.Expression<String> stadiumName, com.querydsl.core.types.Expression<Long> managerId, com.querydsl.core.types.Expression<String> managerName) {
        super(AdminMatchListDTO.class, new Class<?>[]{String.class, java.util.Date.class, com.matchgetit.backend.constant.MatchState.class, long.class, String.class, long.class, String.class}, matchTime, matchDate, matchState, stadiumId, stadiumName, managerId, managerName);
    }

}

