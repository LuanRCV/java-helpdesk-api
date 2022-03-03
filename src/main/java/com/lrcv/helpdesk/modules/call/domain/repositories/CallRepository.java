package com.lrcv.helpdesk.modules.call.domain.repositories;

import com.lrcv.helpdesk.modules.call.domain.models.Call;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer> {}