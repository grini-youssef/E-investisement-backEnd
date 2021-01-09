package com.grini.investisement.repository;

import com.grini.investisement.entity.Projet;
import com.grini.investisement.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ProjetRepository extends JpaRepository<Projet, Long> {
    Page<Projet> findByTitreContaining(@RequestParam("titre") String titre, Pageable pageable);

    List<Projet> findByUser(User user);
}
