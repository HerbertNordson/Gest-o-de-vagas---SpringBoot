package br.com.herbertnordson.gestao_vagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.herbertnordson.gestao_vagas.modules.company.entities.JobEntity;
import br.com.herbertnordson.gestao_vagas.modules.company.useCases.CreateJobUseCases;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    CreateJobUseCases createJobUseCases;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody JobEntity jobEntity){
        return this.createJobUseCases.execute(jobEntity);
    }
}
