package com.labs.companyserv.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Entity
@Table(name = "company", schema = "company-schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Where(clause = "deleted=false")
public class PgCompany {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",
        strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;
    @Column(name = "companyName")
    private String companyName;
    @Column(name = "directorId")
    private String directorId;
    @Column(name = "description")
    private String description;
    private boolean deleted = Boolean.FALSE;
}
