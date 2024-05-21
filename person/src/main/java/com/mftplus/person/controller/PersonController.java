package com.mftplus.person.controller;

import com.mftplus.person.entity.Person;
import com.mftplus.person.exception.NoContentException;
import com.mftplus.person.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> save( @Valid Person person, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        service.save(person);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Person Saved\n" + person.toString());
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> edit( @Valid Person person, BindingResult result) throws NoContentException {
        if (result.hasErrors()) {
            throw new ValidationException(
                    result
                            .getAllErrors()
                            .stream()
                            .map((event) -> event.getDefaultMessage())
                            .collect(Collectors.toList()).toString()
            );
        }
        service.update(person);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Person Edited\n" + person.toString());
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove( @PathVariable Long id) throws NoContentException {
        service.logicalRemoveWithReturn(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.TEXT_PLAIN)
                .body("Person Removed\n" + id);
    }


    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Optional<Person>> findById( @PathVariable Long id) throws NoContentException {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findPersonByIdAndDeletedFalse(id));


    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> findAll() {

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findPersonByDeletedFalse());
    }


}
