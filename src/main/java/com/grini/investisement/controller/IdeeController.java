package com.grini.investisement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grini.investisement.dto.IdeeRequest;
import com.grini.investisement.dto.IdeeResponse;
import com.grini.investisement.service.IdeeService;

import static org.springframework.http.ResponseEntity.status;
import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/idees")
@AllArgsConstructor
public class IdeeController {

	private final IdeeService ideeService;

    @PostMapping
    public ResponseEntity<Void> createIdee(@RequestBody IdeeRequest ideeRequest) {
    	ideeService.save(ideeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IdeeResponse>> getAllIdees() {
        return status(HttpStatus.OK).body(ideeService.getAllIdees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdeeResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(ideeService.getIdee(id));
    }

    @GetMapping("/by-user/{name}")
    public ResponseEntity<List<IdeeResponse>> getPostsByUsername(String username) {
        return status(HttpStatus.OK).body(ideeService.getIdeesByUsername(username));
    }
	
}
