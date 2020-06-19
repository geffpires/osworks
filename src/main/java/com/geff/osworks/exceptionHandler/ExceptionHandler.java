package com.geff.osworks.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldProblem> fieldProblems = new ArrayList<>();

        for (ObjectError error :
                ex.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            String msg = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            fieldProblems.add(new FieldProblem(field, msg));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle("Um ou mais campos incorreto");
        problem.setDataHora(LocalDateTime.now());
        problem.setFieldProblemList(fieldProblems);

        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }
}
