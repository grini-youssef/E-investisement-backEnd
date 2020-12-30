package com.grini.investisement.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grini.investisement.dto.AuthenticationResponse;
import com.grini.investisement.dto.LoginRequest;
import com.grini.investisement.dto.RegisterRequest;
import com.grini.investisement.entity.NotificationEmail;
import com.grini.investisement.entity.User;
import com.grini.investisement.entity.VerificationToken;
import com.grini.investisement.exception.SpringRedditException;
import com.grini.investisement.repository.UserRepository;
import com.grini.investisement.repository.VerificationTokenRepository;
import com.grini.investisement.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        
        userRepository.save(user);
        
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));

	}
	
	private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

	public void verifyAccount(String token) {
		verificationTokenRepository.findByToken(token);
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token")));
	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		
		String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
		
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
		
        return new AuthenticationResponse(token, loginRequest.getUsername());
	}
	
	@Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }
	
}





