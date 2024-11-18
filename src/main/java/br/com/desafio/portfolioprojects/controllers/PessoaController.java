package br.com.desafio.portfolioprojects.controllers;

import br.com.desafio.portfolioprojects.dto.PessoaDTO;
import br.com.desafio.portfolioprojects.models.Pessoa;
import br.com.desafio.portfolioprojects.services.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService service;

    @Autowired
    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @GetMapping
    public String listarPessoas(Model model) {
        List<Pessoa> pessoas = service.listarTodos();
        model.addAttribute("pessoas", pessoas);
        return "pessoa/lista";
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pessoa> criarPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
        Pessoa novaPessoa = service.criarPessoaExterna(pessoaDTO);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(novaPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPessoa(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok("Pessoa excluída com sucesso.");
    }
}
