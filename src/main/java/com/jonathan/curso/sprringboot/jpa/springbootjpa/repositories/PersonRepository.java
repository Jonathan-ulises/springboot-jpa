package com.jonathan.curso.sprringboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jonathan.curso.sprringboot.jpa.springbootjpa.dto.PersonDto;
import com.jonathan.curso.sprringboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {


    @Query("SELECT COUNT(DISTINCT(p.programmingLanguage)) FROM Person p")
    Long findAllProgrammintLanguageDistinctCount();

    @Query("SELECT DISTINCT(p.programmingLanguage) FROM Person p")
    List<String> findAllProgrammintLanguageDistinct();

    @Query("SELECT p.name FROM Person p")
    List<String> findAllNames();

    @Query("SELECT DISTINCT(p.name) FROM Person p")
    List<String> findAllNamesDistinct();

    @Query("SELECT new com.jonathan.curso.sprringboot.jpa.springbootjpa.dto.PersonDto(p.name, p.lastname) FROM Person p")
    List<PersonDto> findAllObjectPersonDto();

    @Query("SELECT new Person(p.name, p.lastname) FROM Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("SELECT p.name FROM Person p WHERE p.id = ?1")
    String getNameById(long id);

    @Query("SELECT p.id FROM Person p WHERE p.id = ?1")
    Long getIdById(long id);

    @Query("SELECT CONCAT(p.name, ' ',p.lastname) as fullname FROM Person p WHERE p.id = ?1")
    String getFullNameById(long id);

    @Query("SELECT p FROM Person p WHERE p.id = ?1")
    Optional<Person> findOne(Long id);
    
    @Query("SELECT p FROM Person p WHERE p.name = ?1")
    Optional<Person> findOneName(String name);

    @Query("SELECT p FROM Person p WHERE p.name LIKE %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name);

    List<Person> findByProgrammingLanguage(String programmingLanguage);
    
    @Query("SELECT p FROM Person p WHERE p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);
    
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("SELECT p, p.programmingLanguage FROM Person p")
    List<Object[]> findAllMixPersonDataList();
    
    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("SELECT p.id, p.name, p.lastname, p.programmingLanguage FROM Person p WHERE p.id = ?1")
    Object obtenerPersonDataById(Long id);

    @Query("SELECT p.name, p.programmingLanguage FROM Person p")
    List<Object[]> obtenerPersonData();

    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.name=?1")
    List<Object[]> obtenerPersonData(String name);
    
    @Query("SELECT p.name, p.programmingLanguage FROM Person p WHERE p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

    
}
