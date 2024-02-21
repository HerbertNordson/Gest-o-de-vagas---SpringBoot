package br.com.herbertnordson.gestao_vagas.modules.company.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {
    
    private UUID id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String website;
    private String description;
    
}
