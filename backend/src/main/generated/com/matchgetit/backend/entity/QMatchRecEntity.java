package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMatchRecEntity is a Querydsl query type for MatchRecEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMatchRecEntity extends EntityPathBase<MatchRecEntity> {

    private static final long serialVersionUID = -1679500870L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMatchRecEntity matchRecEntity = new QMatchRecEntity("matchRecEntity");

    public final DatePath<java.util.Date> applicationDate = createDate("applicationDate", java.util.Date.class);

    public final StringPath applicationTime = createString("applicationTime");

    public final DatePath<Long> crd = createDate("crd", Long.class);

    public final StringPath etc = createString("etc");

    public final QMemberEntity manager;

    public final NumberPath<Long> matchRecId = createNumber("matchRecId", Long.class);

    public final StringPath matchScore = createString("matchScore");

    public final DateTimePath<java.util.Date> matchSearchEnd = createDateTime("matchSearchEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> matchSearchStr = createDateTime("matchSearchStr", java.util.Date.class);

    public final EnumPath<com.matchgetit.backend.constant.MatchState> matchState = createEnum("matchState", com.matchgetit.backend.constant.MatchState.class);

    public final QMemberEntity member;

    public final QMemberEntity partyLeader;

    public final QStadiumEntity stadium;

    public final StringPath team = createString("team");

    public QMatchRecEntity(String variable) {
        this(MatchRecEntity.class, forVariable(variable), INITS);
    }

    public QMatchRecEntity(Path<? extends MatchRecEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMatchRecEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMatchRecEntity(PathMetadata metadata, PathInits inits) {
        this(MatchRecEntity.class, metadata, inits);
    }

    public QMatchRecEntity(Class<? extends MatchRecEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new QMemberEntity(forProperty("manager"), inits.get("manager")) : null;
        this.member = inits.isInitialized("member") ? new QMemberEntity(forProperty("member"), inits.get("member")) : null;
        this.partyLeader = inits.isInitialized("partyLeader") ? new QMemberEntity(forProperty("partyLeader"), inits.get("partyLeader")) : null;
        this.stadium = inits.isInitialized("stadium") ? new QStadiumEntity(forProperty("stadium")) : null;
    }

}

