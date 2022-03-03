package com.lrcv.helpdesk.modules.person.domain.repositories;

import com.lrcv.helpdesk.modules.person.domain.models.Technical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalRepository extends JpaRepository<Technical, Integer> {}