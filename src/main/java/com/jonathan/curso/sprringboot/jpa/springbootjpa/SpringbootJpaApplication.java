package com.jonathan.curso.sprringboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		// findOne();
		// create();
		// update();
		// delete();
		// delete2();
		personalizedQueries();
	}

	@Transactional(readOnly = true)
	public void personalizedQueries() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("========== Consulta solo el nombre por el id ==========");
		System.out.println("Ingrese el id para el nombre: ");
		Long id = scanner.nextLong();
		scanner.close();

		System.out.println("========== Mostrando solo el nombre ==========");
		String name = repository.getNameById(id);
		System.out.println(name);

		System.out.println("========== Mostrando solo el id ==========");
		Long idBd = repository.getIdById(id);
		System.out.println(idBd);

		System.out.println("========== Mostrando nombre completo con concat ==========");
		String fullname = repository.getFullNameById(id);
		System.out.println(fullname);

		System.out.println("========== Consulta por campos personalizador por el id ==========");
		System.out.println("Consulta por campos personalizador por el id");
		Object[] personReg = (Object[]) repository.obtenerPersonDataById(id);
		System.out.println("id=" + personReg[0] + ", nombre = " + personReg[1] + ", apellido = " + personReg[2]);


		System.out.println("========== Consulta por campos personalizados lista ==========");
		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre = " + reg[1] + ", apellido = " + reg[2]));
	}

	@Transactional
	public void delete2() {
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrse le ID de la persona a eliminar: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresentOrElse(repository::delete, 
			() -> System.out.println("El usuario no existe"));
		scanner.close();
		
		repository.findAll().forEach(System.out::println);
	}

	@Transactional
	public void delete() {
		repository.findAll().forEach(System.out::println);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrse le ID de la persona a eliminar: ");
		Long id = scanner.nextLong();
		repository.deleteById(id);
		scanner.close();

		repository.findAll().forEach(System.out::println);

		scanner.close();
	}

	@Transactional
	public void update() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrse le ID de la persona a editar: ");
		Long id = scanner.nextLong();

		Optional<Person> optionalPerson = repository.findById(id);
		optionalPerson.ifPresent(person -> {
			System.out.println(person);
			System.out.println("Ingrese el lenguaje de programacion:");
			String programmingLanguage = scanner.next();
			person.setProgrammingLanguage(programmingLanguage);
			Person prsonBd = repository.save(person);
			System.out.println(prsonBd);
		});
		scanner.close();
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre: ");
		String name = scanner.next();
		System.out.println("Ingrese el apellido: Pepa");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion: ");
		String programmingLanguage = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastname, programmingLanguage);

		Person personNew = repository.save(person);
		System.out.println(personNew);

		repository.findById(personNew.getId()).ifPresent(System.out::println);
	}

	@Transactional(readOnly = true) // transaccion de solo lectura
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

	@Transactional(readOnly = true) // transaccion de solo lectura
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
