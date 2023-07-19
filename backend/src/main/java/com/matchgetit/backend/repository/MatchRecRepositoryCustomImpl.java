package com.matchgetit.backend.repository;

import com.matchgetit.backend.dto.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.thymeleaf.util.StringUtils;

//import java.util.Date;
import java.sql.Date;
import java.util.List;

import static com.matchgetit.backend.entity.QMatchRecEntity.matchRecEntity;

public class MatchRecRepositoryCustomImpl implements MatchRecRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public MatchRecRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AdminMatchListDTO> getMatchListBy() {
        List<AdminMatchListDTO> content = queryFactory
                .selectDistinct(new QAdminMatchListDTO(
//                        matchRecEntity.matchRecId,
                        matchRecEntity.applicationTime,
                        matchRecEntity.applicationDate,
                        matchRecEntity.matchState,
                        matchRecEntity.stadium.stdId,
                        matchRecEntity.stadium.stdName,
                        matchRecEntity.manager.userId,
                        matchRecEntity.manager.name
                ))
                .from(matchRecEntity)
                .where(
                        (Predicate) null
                )
                .orderBy(matchRecEntity.applicationDate.desc())
                .fetch();
        return content;
    }


    public List<AdminMatchRecDTO> getMatchInfoBy(String matchDate, String matchTime, Long stadiumId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (!StringUtils.isEmpty(matchDate)) {
            Date from = Date.valueOf(matchDate);
            Date to = Date.valueOf(matchDate);
            to.setDate(to.getDate()+1);
            booleanBuilder.and(matchRecEntity.applicationDate.between(from, to));
        }

        if (!StringUtils.isEmpty(matchTime)) {
            booleanBuilder.and(matchRecEntity.applicationTime.eq(matchTime));
        }

        if (stadiumId != null) {
            booleanBuilder.and(matchRecEntity.stadium.stdId.eq(stadiumId));
        }

        List<AdminMatchRecDTO> matchInfoDTO = queryFactory
                .selectDistinct(new QAdminMatchRecDTO(
                        matchRecEntity.matchRecId,
                        matchRecEntity.member,
                        matchRecEntity.applicationTime,
                        matchRecEntity.applicationDate,
                        matchRecEntity.matchState,
                        matchRecEntity.team,
                        matchRecEntity.matchScore,
                        matchRecEntity.etc,
                        matchRecEntity.stadium.stdId,
                        matchRecEntity.stadium.stdName,
                        matchRecEntity.manager.userId,
                        matchRecEntity.manager.name
                ))
                .from(matchRecEntity)
                .where(booleanBuilder)
                .orderBy(matchRecEntity.matchRecId.desc())
                .fetch();

        return matchInfoDTO;
    }

}
