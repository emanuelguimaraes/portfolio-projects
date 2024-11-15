package br.com.desafio.portfolioprojects.controllers;

import br.com.desafio.portfolioprojects.dto.PessoaDTO;
import br.com.desafio.portfolioprojects.models.Pessoa;
import br.com.desafio.portfolioprojects.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
        Pessoa novaPessoa = service.criarPessoaExterna(pessoaDTO);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(novaPessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return service.listarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @Valid @RequestBody PessoaDTO pessoaDTO) {
        return ResponseEntity.ok(service.atualizarPessoa(id, pessoaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPessoa(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok("Pessoa excluída com sucesso.");
    }
}
