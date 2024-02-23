package br.com.herbertnordson.gestao_vagas.modules.company.useCases.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.herbertnordson.gestao_vagas.modules.company.entities.JobEntity;
import br.com.herbertnordson.gestao_vagas.modules.company.repository.CompanyRepository;
import br.com.herbertnordson.gestao_vagas.modules.company.repository.JobRepository;

@Service
public class CreateJobUseCases {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    CompanyRepository companyRepository;

    @SuppressWarnings("null")
    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Company not found");
        });
        return this.jobRepository.save(jobEntity);
    }

}
