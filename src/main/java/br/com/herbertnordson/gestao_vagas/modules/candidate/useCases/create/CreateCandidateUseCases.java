package br.com.herbertnordson.gestao_vagas.modules.candidate.useCases.create;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.herbertnordson.gestao_vagas.exceptions.UserFoundException;
import br.com.herbertnordson.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.herbertnordson.gestao_vagas.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCases {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });
        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }

}
