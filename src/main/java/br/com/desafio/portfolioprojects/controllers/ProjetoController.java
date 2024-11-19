package br.com.desafio.portfolioprojects.controllers;

import br.com.desafio.portfolioprojects.dto.ProjetoDTO;
import br.com.desafio.portfolioprojects.models.Pessoa;
import br.com.desafio.portfolioprojects.models.Projeto;
import br.com.desafio.portfolioprojects.models.enums.ClassificacaoRisco;
import br.com.desafio.portfolioprojects.models.enums.StatusProjeto;
import br.com.desafio.portfolioprojects.services.PessoaService;
import br.com.desafio.portfolioprojects.services.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    private final ProjetoService service;
    private final PessoaService pessoaService;

    @Autowired
    public ProjetoController(ProjetoService service, PessoaService pessoaService) {
        this.service = service;
        this.pessoaService = pessoaService;
    }

    @PostMapping("/salvar")
    public String criarProjeto(@Valid @ModelAttribute("projetoDTO") ProjetoDTO projetoDTO,
        BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pessoas", pessoaService.listarTodos());
            model.addAttribute("classificacoesRisco", ClassificacaoRisco.values());
            model.addAttribute("statusProjeto", StatusProjeto.values());
            return "projeto/form";
        }

        service.salvar(projetoDTO);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Projeto criado com sucesso!");
        return "redirect:/projetos";
    }

    @GetMapping
    public String listarProjetos(Model model) {
        List<Projeto> projetos = service.listarTodos();
        model.addAttribute("projetos", projetos);
        return "projeto/lista";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("projetoDTO", new ProjetoDTO());
        model.addAttribute("pessoas", pessoaService.listarTodos());
        model.addAttribute("classificacoesRisco", ClassificacaoRisco.values());
        model.addAttribute("statusProjeto", StatusProjeto.values());
        return "projeto/form";
    }

    @GetMapping("/{id}")
    public String exibirProjeto(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Projeto projeto = service.buscarPorId(id);
        List<Pessoa> pessoasDisponiveis = pessoaService.listarTodos();
        pessoasDisponiveis.removeAll(projeto.getMembros());

        model.addAttribute("projeto", projeto);
        model.addAttribute("pessoasDisponiveis", pessoasDisponiveis);
        return "projeto/detalhes";
    }

    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Projeto projeto = service.buscarPorId(id);
        model.addAttribute("projetoID", projeto.getId());
        model.addAttribute("projetoDTO", new ProjetoDTO(projeto));
        model.addAttribute("pessoas", pessoaService.listarTodos());
        model.addAttribute("classificacoesRisco", ClassificacaoRisco.values());
        model.addAttribute("statusProjeto", StatusProjeto.values());
        return "projeto/form";
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id, @Valid @RequestBody ProjetoDTO projetoDTO) {
        return ResponseEntity.ok(service.atualizar(id, projetoDTO));
    }

    @PostMapping("/{projetoId}/membro")
    public String adicionarMembroAoProjeto(@PathVariable Long projetoId, @RequestParam Long pessoaId, RedirectAttributes redirectAttributes) {
        try {
            service.adicionarMembroAoProjeto(projetoId, pessoaId);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Membro adicionado ao projeto com sucesso!");

        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro", ex.getMessage());
        }

        return "redirect:/projetos/" + projetoId;
    }

    @DeleteMapping("/{projetoId}/membro/{pessoaId}")
    public ResponseEntity<String> removerMembroProjeto(@PathVariable Long projetoId, @PathVariable Long pessoaId) {
        service.removerMembroProjeto(projetoId, pessoaId);
        return ResponseEntity.ok("Membro removido do projeto com sucesso!");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> excluirProjeto(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok("Projeto excluído com sucesso.");
    }
}
