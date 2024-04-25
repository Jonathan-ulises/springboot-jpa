package com.jonathan.curso.sprringboot.jpa.springbootjpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jonathan.curso.sprringboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
