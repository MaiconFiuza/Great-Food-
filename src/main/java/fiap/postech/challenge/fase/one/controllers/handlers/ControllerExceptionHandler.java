package fiap.postech.challenge.fase.one.controllers.handlers;

import fiap.postech.challenge.fase.one.dtos.InternalServerErrorDTO;
import fiap.postech.challenge.fase.one.dtos.ResourceNotFoundDTO;
import fiap.postech.challenge.fase.one.dtos.UnprocessableEntityDTO;
import fiap.postech.challenge.fase.one.dtos.ValidationErrorDTO;
import fiap.postech.challenge.fase.one.services.exceptions.InternalServerErrorException;
import fiap.postech.challenge.fase.one.services.exceptions.ResourceNotFoundException;
import fiap.postech.challenge.fase.one.services.exceptions.UnprocessableEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value())
                .body(new ResourceNotFoundDTO(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<InternalServerErrorDTO> handlerInternalServerErrorException(
            InternalServerErrorException exception
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status.value())
                .body(new InternalServerErrorDTO(exception.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for(var error: exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": "+ error.getDefaultMessage());
        }
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<UnprocessableEntityDTO> handlerUnprocessableEntityException(
            UnprocessableEntityException exception
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        return ResponseEntity.status(status.value())
                .body(new UnprocessableEntityDTO(exception.getMessage(), status.value()));
    }
}
