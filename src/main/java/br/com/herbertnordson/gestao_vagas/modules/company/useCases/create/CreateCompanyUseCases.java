package br.com.herbertnordson.gestao_vagas.modules.company.useCases.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.herbertnordson.gestao_vagas.exceptions.UserFoundException;
import br.com.herbertnordson.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.herbertnordson.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class CreateCompanyUseCases {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((company) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);

    }
}
