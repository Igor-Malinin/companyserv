package com.labs.companyserv.repository;

import com.labs.companyserv.entity.PgCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PgCompanyRepository extends JpaRepository<PgCompany, String> {
}
