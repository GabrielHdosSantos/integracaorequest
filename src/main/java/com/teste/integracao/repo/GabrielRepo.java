package com.teste.integracao.repo;

import com.teste.integracao.modelo.Gabriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GabrielRepo extends JpaRepository<Gabriel, Long> {
}
