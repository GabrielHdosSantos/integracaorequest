package com.teste.integracao.repo;

import com.teste.integracao.dto.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repo extends JpaRepository<Pessoa, Long> {
}
