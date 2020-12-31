package com.grini.investisement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grini.investisement.dto.IdeeRequest;
import com.grini.investisement.dto.IdeeResponse;
import com.grini.investisement.entity.Commune;
import com.grini.investisement.entity.Idee;
import com.grini.investisement.entity.User;
import com.grini.investisement.exception.CommuneNotFoundException;
import com.grini.investisement.exception.IdeeNotFoundException;
import com.grini.investisement.mapper.IdeeMapper;
import com.grini.investisement.repository.CommuneRepository;
import com.grini.investisement.repository.IdeeRepository;
import com.grini.investisement.repository.UserRepository;

import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class IdeeService {
	
	private final IdeeRepository ideeRepository;
    private final UserRepository userRepository;
    private final CommuneRepository communeRepository;
    private final AuthService authService;
    private final IdeeMapper ideeMapper;

	public void save(IdeeRequest ideeRequest) {
		
		Commune commune = communeRepository.findByNom(ideeRequest.getCommune())
                .orElseThrow(() -> new CommuneNotFoundException(ideeRequest.getCommune()));
		ideeRepository.save(ideeMapper.map(ideeRequest,commune ,authService.getCurrentUser()));
		
	}

	
	@Transactional(readOnly = true)
    public List<IdeeResponse> getAllIdees() {
        return ideeRepository.findAll()
                .stream()
                .map(ideeMapper::mapToDto)
                .collect(toList());
    }
	
	@Transactional(readOnly = true)
    public IdeeResponse getIdee(Long id) {
        Idee idee = ideeRepository.findById(id)
                .orElseThrow(() -> new IdeeNotFoundException(id.toString()));
        return ideeMapper.mapToDto(idee);
    }
	
	@Transactional(readOnly = true)
    public List<IdeeResponse> getIdeesByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return ideeRepository.findByUser(user)
                .stream()
                .map(ideeMapper::mapToDto)
                .collect(toList());
    }

}
