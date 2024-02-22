package br.com.herbertnordson.gestao_vagas.modules.company.useCases.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.herbertnordson.gestao_vagas.modules.company.entities.JobEntity;
import br.com.herbertnordson.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCases {
    
    @Autowired
    JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }

}
