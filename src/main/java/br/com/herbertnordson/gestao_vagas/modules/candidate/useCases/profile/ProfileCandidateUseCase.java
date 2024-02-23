package br.com.herbertnordson.gestao_vagas.modules.candidate.useCases.profile;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.herbertnordson.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.herbertnordson.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.herbertnordson.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        var CandidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
                
        return CandidateDTO;
    }

}
