package com.jonathan.curso.sprringboot.jpa.springbootjpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jonathan.curso.sprringboot.jpa.springbootjpa.dto.PersonDto;
import com.jonathan.curso.sprringboot.jpa.springbootjpa.entities.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {


    @Query("SELECT p FROM Person p WHERE p.id IN ?1")
    public List<Person> getPersonByIds(List<Long> ids);

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p WHERE LENGTH(p.name)=(SELECT MIN(LENGTH(p.name)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("SELECT p FROM Person p WHERE p.id = (SELECT MAX(p.id) FROM Person p)")
    public Optional<Person> getLastRegistration();

    @Query("SELECT MIN(p.id), MAX(p.id), SUM(p.id), AVG(LENGTH(p.name)), COUNT(p.id) FROM Person p")
    public Object getResumeAggregationFunction();

    @Query("SELECT MIN(LENGTH(p.name)) FROM Person p")
    public Integer getMinLengthName();

    @Query("SELECT MAX(LENGTH(p.name)) FROM Person p")
    public Integer getMaxLengthName();

    @Query("SELECT p.name, LENGTH(p.name) FROM Person p")
    public List<Object[]> getPersonNameLength();

    @Query("SELECT COUNT(p) FROM Person p")
    Long totalPerson();

    @Query("SELECT MIN(p.id) FROM Person p")
    Long minId();

    @Query("SELECT MAX(p.id) FROM Person p")
    Long maxId();

    List<Person> findAllByOrderByNameAscLastnameDesc();

    @Query("SELECT p FROM Person p ORDER BY p.name, p.lastname DESC")
    List<Person> getAllOrdered();

    List<Person> findByIdBetweenOrderByIdDesc(Long id1, Long id2);

    List<Person> findByIdBetween(Long id1, Long id2);

    List<Person> findByNameBetween(String name1, String name2);

    @Query("SELECT p FROM Person p WHERE p.name BETWEEN ?1 AND ?2 ORDER BY p.name ASC, p.lastname DESC")
    List<Person> fullAllBetweenName(String c1, String c2);

    @Query("SELECT p FROM Person p WHERE p.id between ?1 and ?2 ORDER BY p.name DESC")
    List<Person> fullAllBetweenId(Long id1, Long id2);

    @Query("SELECT p.id, UPPER(p.name), LOWER(p.lastname), UPPER(p.programmingLanguage) FROM Person p")
    List<Object[]> fullAllPersonDataListCase();
    
    @Query("SELECT LOWER(CONCAT(p.name, ' ',p.lastname)) FROM Person p")
    List<String> findAllFullNameConcatLower();

    @Query("SELECT UPPER(p.name || ' ' || p.lastname) FROM Person p")
    List<String> findAllFullNameConcatUpper();

    // @Query("SELECT CONCAT(p.name, ' ',p.lastname) FROM Person p")
    @Query("SELECT p.name || ' ' || p.lastname FROM Person p")
    List<String> findAllFullNameConcat();

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
