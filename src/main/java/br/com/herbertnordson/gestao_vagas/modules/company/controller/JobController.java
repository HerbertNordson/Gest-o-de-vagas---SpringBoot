package br.com.herbertnordson.gestao_vagas.modules.company.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    CreateJobUseCases createJobUseCases;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<Object> execute(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        try {
            var jobEntity = JobEntity.builder()
                    .benefits(createJobDTO.getBenefits())
                    .description(createJobDTO.getDescription())
                    .level(createJobDTO.getLevel())
                    .companyId(UUID.fromString(companyId.toString()))
                    .build();

            var result = this.createJobUseCases.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
