package br.com.desafio.portfolioprojects.repositories;

import br.com.desafio.portfolioprojects.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
