package com.matchgetit.backend.repository;

import com.matchgetit.backend.entity.MatchRecEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRecRepository extends JpaRepository<MatchRecEntity,Long> {
}
