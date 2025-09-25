package com.example.aulajpa.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.aulajpa.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Intercepta exceções lançadas por qualquer Controller
public class ResourceExceptionHandler {

    /**
     * Manipula a exceção ResourceNotFoundException.
     * @param e A exceção lançada (ResourceNotFoundException).
     * @param request O objeto da requisição HTTP.
     * @return ResponseEntity com status 404 Not Found e o corpo do erro.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        
        // Define o código de status HTTP 404 (Not Found)
        HttpStatus status = HttpStatus.NOT_FOUND; 
        
        // 1. Cria o objeto de erro padrão (StandardError)
        StandardError err = new StandardError(
            Instant.now(),                       // Horário do erro
            status.value(),                      // Código 404
            "Resource Not Found",                // Tipo de erro
            e.getMessage(),                      // Mensagem detalhada da exceção
            request.getRequestURI()              // URI que causou o erro
        );
        
        // 2. Retorna a resposta com o status 404 e o corpo de erro
        return ResponseEntity.status(status).body(err);
    }
}
