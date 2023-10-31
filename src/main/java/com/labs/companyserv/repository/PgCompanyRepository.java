package com.labs.microver4.repository;

import com.labs.microver4.entity.PgUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PgUserRepository extends JpaRepository<PgUser, String> {
}
