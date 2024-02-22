package br.com.herbertnordson.gestao_vagas.modules.candidate.useCases.auth;

import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.herbertnordson.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.herbertnordson.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;

@Service
public class AuthCandidateUseCase {

   @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException{
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("Username/password incorrect");
        });

        var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

        if(!passwordMatches) throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
        .withIssuer("candidates")
        .withExpiresAt(Instant.now().plus(java.time.Duration.ofHours(2)))
        .withClaim("roles", Arrays.asList("canidate"))
        .withSubject(candidate.getId().toString())
        .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
        .access_token(token)
        .build();

        return authCandidateResponse;
        
    }

}
