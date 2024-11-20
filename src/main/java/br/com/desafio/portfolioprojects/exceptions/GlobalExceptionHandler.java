package br.com.desafio.portfolioprojects.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PessoaNaoPodeSerAdicionadaAoProjetoException.class)
    public ModelAndView handlePessoaNaoPodeSerAdicionadaAoProjetoException(PessoaNaoPodeSerAdicionadaAoProjetoException ex) {
        ModelAndView model = new ModelAndView("projeto/detalhes");
        model.addObject("mensagemErro", ex.getMessage());
        return model;
    }

    @ExceptionHandler(ProjetoNaoEncontradoException.class)
    public ModelAndView handleProjetoNaoEncontradoException(ProjetoNaoEncontradoException ex) {
        ModelAndView model = new ModelAndView("error/error");
        model.addObject("mensagemErro", ex.getMessage());
        return model;
    }

    @ExceptionHandler(GerenteNaoEncontradoException.class)
    public ModelAndView handleGerenteNaoEncontradoException(GerenteNaoEncontradoException ex) {
        ModelAndView model = new ModelAndView("projeto/form");
        model.addObject("mensagemErro", ex.getMessage());
        return model;
    }

    @ExceptionHandler(GerenteNaoPodeSerExcluidoException.class)
    public ResponseEntity<Map<String, String>> handleGerenteNaoPodeSerExcluidoException(GerenteNaoPodeSerExcluidoException ex) {
        return ResponseEntity
            .badRequest()
            .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(ProjetoNaoPodeSerExcluidoException.class)
    public ResponseEntity<Map<String, String>> handleProjetoNaoPodeSerExcluidoException(ProjetoNaoPodeSerExcluidoException ex) {
        return ResponseEntity
            .badRequest()
            .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<String> handlePessoaNaoEncontradaException(PessoaNaoEncontradaException ex) {
        return ResponseEntity
            .notFound()
            .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage)
            .collect(Collectors.toList());

        return ResponseEntity
            .badRequest()
            .body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
            .badRequest()
            .body(Map.of("error", "Atribuição inválida."));
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleRuntimeException(Exception ex) {
        ModelAndView model = new ModelAndView("error/error");
        model.addObject("mensagemErro", "Ocorreu um erro interno no servidor. Detalhes: " + ex.getMessage());

        return model;
    }
}
