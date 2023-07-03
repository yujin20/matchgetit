package com.matchgetit.backend.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentRecordEntity is a Querydsl query type for PaymentRecordEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentRecordEntity extends EntityPathBase<PaymentRecordEntity> {

    private static final long serialVersionUID = -480501042L;

    public static final QPaymentRecordEntity paymentRecordEntity = new QPaymentRecordEntity("paymentRecordEntity");

    public final DateTimePath<java.time.LocalDateTime> cancelDate = createDateTime("cancelDate", java.time.LocalDateTime.class);

    public final StringPath gameNumber = createString("gameNumber");

    public final NumberPath<Long> paymentNumber = createNumber("paymentNumber", Long.class);

    public final DateTimePath<java.time.LocalDateTime> transactionDate = createDateTime("transactionDate", java.time.LocalDateTime.class);

    public final EnumPath<com.matchgetit.backend.constant.PaymentStatus> transactionStatus = createEnum("transactionStatus", com.matchgetit.backend.constant.PaymentStatus.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath userName = createString("userName");

    public QPaymentRecordEntity(String variable) {
        super(PaymentRecordEntity.class, forVariable(variable));
    }

    public QPaymentRecordEntity(Path<? extends PaymentRecordEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentRecordEntity(PathMetadata metadata) {
        super(PaymentRecordEntity.class, metadata);
    }

}

