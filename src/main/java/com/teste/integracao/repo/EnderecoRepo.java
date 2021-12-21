package com.teste.integracao.repo;

import com.teste.integracao.modelo.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepo extends JpaRepository<Endereco, Long> {
}
