package com.grini.investisement.controller;

import com.grini.investisement.dto.ProjetRequest;
import com.grini.investisement.dto.ProjetResponse;
import com.grini.investisement.service.ProjetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/projets")
@AllArgsConstructor
public class ProjetController {
    private final ProjetService projetService;

    @PostMapping
    public ResponseEntity<Void> createProjet(@RequestBody ProjetRequest projetRequest) {
        projetService.save(projetRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjetResponse>> getAllProjets() {
        return status(HttpStatus.OK).body(projetService.getAllProjets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(projetService.getProjet(id));
    }

    @GetMapping("/by-user/{name}")
    public ResponseEntity<List<ProjetResponse>> getPostsByUsername(@PathVariable String name) {
        return status(HttpStatus.OK).body(projetService.getProjetsByUsername(name));
    }
}
