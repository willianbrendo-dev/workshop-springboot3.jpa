package com.example.aulajpa.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
     * Construtor que recebe o ID do recurso não encontrado.
     * @param id O ID que o usuário tentou buscar.
     */
    public ResourceNotFoundException(Object id) {
        // Chama o construtor da superclasse (RuntimeException)
        // A mensagem será: "Resource not found. Id [ID do recurso]"
        super("Resource not found. Id " + id);
    }
	
}
