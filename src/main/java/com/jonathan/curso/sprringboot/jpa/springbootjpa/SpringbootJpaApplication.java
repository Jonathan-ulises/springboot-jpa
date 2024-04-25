package com.jonathan.curso.sprringboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonathan.curso.sprringboot.jpa.springbootjpa.entities.Person;
import com.jonathan.curso.sprringboot.jpa.springbootjpa.repositories.PersonRepository;

@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// list();
		findOne();
	}

	public void findOne() {
		// Person person = null;
		// Optional<Person> optional = repository.findById(1L);
		// if (optional.isPresent()) {
		// 	person = optional.get();
		// }

		// System.out.println(person);

		// repository.findById(1L).ifPresent(System.out::println);
		// repository.findOne(1L).ifPresent(System.out::println);
		// repository.findOneName("Pepe").ifPresent(System.out::println);
		// repository.findOneLikeName("se").ifPresent(System.out::println);
		repository.findByNameContaining("hn").ifPresent(System.out::println);
	}

	public void list() {
		// List<Person> persons = (List<Person>) repository.findAll();
		// List<Person> persons = (List<Person>) repository.buscarByProgrammingLanguage("Java", "Andres");
		List<Person> persons = (List<Person>) repository.findByProgrammingLanguageAndName("Java", "Andres");
		
		persons.stream().forEach(System.out::println);

		List<Object[]> personsValues = repository.obtenerPersonData();
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
			System.out.println(person);
		});
	}
}
