package br.com.herbertnordson.gestao_vagas.modules.company.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.herbertnordson.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.herbertnordson.gestao_vagas.modules.company.entities.JobEntity;
import br.com.herbertnordson.gestao_vagas.modules.company.useCases.create.CreateJobUseCases;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    CreateJobUseCases createJobUseCases;

    @PostMapping("/")
    public JobEntity execute(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        JobEntity jobEntity = JobEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .companyId(UUID.fromString(companyId.toString()))
                .build();
        return this.createJobUseCases.execute(jobEntity);
    }
}
