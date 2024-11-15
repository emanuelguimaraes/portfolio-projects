package br.com.desafio.portfolioprojects.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjetoNaoPodeSerExcluidoException.class)
    public ResponseEntity<String> handleProjetoNaoPodeSerExcluidoException(ProjetoNaoPodeSerExcluidoException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
    }

    @ExceptionHandler(PessoaNaoPodeSerAdicionadaAoProjetoException.class)
    public ResponseEntity<String> handlePessoaNaoPodeSerAdicionadaAoProjetoException(PessoaNaoPodeSerAdicionadaAoProjetoException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
    }

    @ExceptionHandler(ProjetoNaoEncontradoException.class)
    public ResponseEntity<String> handleProjetoNaoEncontradoException(ProjetoNaoEncontradoException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(GerenteNaoEncontradoException.class)
    public ResponseEntity<String> handleGerenteNaoEncontradoException(GerenteNaoEncontradoException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(PessoaNaoEncontradaException.class)
    public ResponseEntity<String> handleGerenteNaoEncontradoException(PessoaNaoEncontradaException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Ocorreu um erro interno no servidor.");
    }
}
