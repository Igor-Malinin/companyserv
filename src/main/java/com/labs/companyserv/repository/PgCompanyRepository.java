package com.labs.companyserv.repository;

import com.labs.companyserv.entity.PgCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PgCompanyRepository extends JpaRepository<PgCompany, String> {
    @Query("select c from PgCompany c where c.deleted=true and c.id=?1")
    Optional<PgCompany> findByIdAndDeleted(String id, boolean value);
}
