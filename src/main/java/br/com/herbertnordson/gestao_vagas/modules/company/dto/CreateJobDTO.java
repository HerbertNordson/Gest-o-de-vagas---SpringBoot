package br.com.herbertnordson.gestao_vagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String description;
    private String level;
    private String benefits;
}
