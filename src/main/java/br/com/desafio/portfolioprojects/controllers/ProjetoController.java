package br.com.desafio.portfolioprojects.controllers;

import br.com.desafio.portfolioprojects.dto.ProjetoDTO;
import br.com.desafio.portfolioprojects.models.Projeto;
import br.com.desafio.portfolioprojects.services.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService service;

    @Autowired
    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Projeto> criarProjeto(@Valid @RequestBody ProjetoDTO projetoDTO) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.salvar(projetoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjetoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public List<Projeto> listarProjetos() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @Valid @RequestBody ProjetoDTO projetoDTO) {
        return ResponseEntity.ok(service.atualizar(id, projetoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirProjeto(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok("Projeto excluído com sucesso.");
    }
}
