package com.mftplus.person.service;


import com.mftplus.person.entity.Person;
import com.mftplus.person.exception.NoContentException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person save(Person person);
    Person update(Person person) throws NoContentException;

    @Transactional
    void logicalRemove(Long id) throws NoContentException;

    List<Person> findAll();
    Optional<Person> findById(Long id) throws NoContentException;
    Long getPersonsCount();

    Person logicalRemoveWithReturn(Long id) throws NoContentException;


    List<Person> findPersonByDeletedFalse();
    Optional<Person> findPersonByIdAndDeletedFalse(Long id) throws NoContentException;
    List<Person> findPersonByNameAndLastnameAndDeletedFalse(String name , String lastName);
    Optional<Person> findPersonByNationalIDAndDeletedFalse(String nationalId) throws NoContentException;


    Long countByDeletedFalse();

}
