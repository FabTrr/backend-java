package com.example.proyectoIntegrador11.exception;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.persistence.EntityNotFoundException;


@ControllerAdvice
public class GlobalException {
    private static final Logger logger = LogManager.getLogger(GlobalException.class);

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamientoResourceNotFoundException(ResourceNotFoundException rnfe){
        logger.error("Recurso no encontrado: ", rnfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mensaje: "+rnfe.getMessage());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamientoBadRequestException(BadRequestException bre) {
        logger.error("Error 400 Bad Request: ", bre);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("mensaje: " + bre.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException enfe) {
        logger.error("Entidad no encontrada: ", enfe);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mensaje: " + enfe.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerError(Exception ex) {
        logger.error("Error en servidor: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
    }

}
