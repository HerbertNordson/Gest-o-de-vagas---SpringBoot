package br.com.herbertnordson.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.herbertnordson.gestao_vagas.exceptions.UserFoundException;
import br.com.herbertnordson.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.herbertnordson.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CreateCompanyUseCases {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((company) -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(companyEntity);

    }
}
