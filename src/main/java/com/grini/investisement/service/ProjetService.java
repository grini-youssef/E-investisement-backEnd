package com.grini.investisement.service;

import com.grini.investisement.dto.ProjetRequest;
import com.grini.investisement.dto.ProjetResponse;
import com.grini.investisement.entity.Commune;
import com.grini.investisement.entity.Projet;
import com.grini.investisement.entity.User;
import com.grini.investisement.exception.CommuneNotFoundException;
import com.grini.investisement.exception.ProjetNotFoundException;
import com.grini.investisement.mapper.ProjetMapper;
import com.grini.investisement.repository.CommuneRepository;
import com.grini.investisement.repository.ProjetRepository;
import com.grini.investisement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ProjetService {
    private final ProjetRepository projetRepository;
    private final UserRepository userRepository;
    private final CommuneRepository communeRepository;
    private final AuthService authService;
    private final ProjetMapper projetMapper;


    public void save(ProjetRequest projetRequest) {

        Commune commune = communeRepository.findByNom(projetRequest.getCommune())
                .orElseThrow(() -> new CommuneNotFoundException(projetRequest.getCommune()));
        projetRepository.save(projetMapper.map(projetRequest,commune ,authService.getCurrentUser()));

    }


    @Transactional(readOnly = true)
    public List<ProjetResponse> getAllProjets() {
        return projetRepository.findAll()
                .stream()
                .map(projetMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public ProjetResponse getProjet(Long id) {
         Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new ProjetNotFoundException(id.toString()));
        return projetMapper.mapToDto(projet);
    }

    @Transactional(readOnly = true)
    public List<ProjetResponse> getProjetsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return projetRepository.findByUser(user)
                .stream()
                .map(projetMapper::mapToDto)
                .collect(toList());
    }
}
