package com.lrcv.helpdesk.modules.technical.domain.repositories;

import com.lrcv.helpdesk.modules.technical.domain.models.Technical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalRepository extends JpaRepository<Technical, Integer> {}