package com.grini.investisement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grini.investisement.entity.Commune;

public interface CommuneRepository extends JpaRepository<Commune, Long> {

}
