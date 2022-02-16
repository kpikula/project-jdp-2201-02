package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.exception.groupException.GroupExistInRepositoryException;
import com.kodilla.ecommercee.exception.groupException.GroupNameIsEmptyStringException;
import com.kodilla.ecommercee.exception.groupException.GroupNotFoundException;
import com.kodilla.ecommercee.exception.groupException.InvalidGroupDtoRequestException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException cartNotFoundException) {
        return new ResponseEntity<>("Cart with given id doesn't exist or can't be found in repository", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return new ResponseEntity<>("Product with given id doesn't exist or can't be found in repository", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotAvailableException.class)
    public ResponseEntity<Object> handleProductNotAvailableException(ProductNotAvailableException productNotAvailableException) {
        return new ResponseEntity<>("Product with given id is not available at the moment", HttpStatus.GONE);
    }

    @ExceptionHandler(ProductNotFoundInCartException.class)
    public ResponseEntity<Object> handleProductNotFoundInCartException(ProductNotFoundInCartException productNotFoundInCartException) {
        return new ResponseEntity<>("Product with given id doesn't exist or can't be found in the Cart with given id", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFoundException(OrderNotFoundException orderNotFoundException) {
        return new ResponseEntity<>("Order with given id doesn't exist or can't be found", HttpStatus.NOT_FOUND);
    }

    // Group entity exception handlers:
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleGroupNotFoundException(GroupNotFoundException groupNotFoundException ) {
        return new ResponseEntity<>("Group with given id doesn't exist or can't be found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GroupNameIsEmptyStringException.class)
    public ResponseEntity<Object> handleNameIsEmptyStringException(GroupNameIsEmptyStringException groupNameIsEmptyStringException) {
        return new ResponseEntity<>("Updating group by giving empty groupName is not possible", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(GroupExistInRepositoryException.class)
    public ResponseEntity<Object> handleExistInRepositoryException(GroupExistInRepositoryException groupExistInRepositoryException) {
        return new ResponseEntity<>("Group with given name exist in the repository", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidGroupDtoRequestException.class)
    public ResponseEntity<Object> handleInvalidGroupDtoRequestException(InvalidGroupDtoRequestException invalidGroupDtoRequestException) {
        return new ResponseEntity<>("Invalid GroupDto request - \"id\" is obligatory", HttpStatus.BAD_REQUEST);
    }
}
