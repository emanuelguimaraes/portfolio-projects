package br.com.desafio.portfolioprojects.repositories;

import br.com.desafio.portfolioprojects.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
