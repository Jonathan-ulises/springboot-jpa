package com.jonathan.curso.sprringboot.jpa.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jonathan.curso.sprringboot.jpa.springbootjpa.dto.PersonDto;
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
		// personalizedQueries();
		// personalizedQueries2();
		// personalizedQuieriesDistinct();
		// personalizedQuieriesConcatUpperAndLowerCase();
		perzonalizedQueriesBetween();
	}

	@Transactional(readOnly = true)
	public void perzonalizedQueriesBetween() {
		System.out.println("========== consultas por rangos ==========");
		List<Person> persons = repository.findByIdBetweenOrderByIdDesc(2L, 5L);
		persons.forEach(System.out::println);

		persons = repository.fullAllBetweenName("J", "Q");
		persons.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void personalizedQuieriesConcatUpperAndLowerCase() {
		System.out.println("========== consulta nombres y apellidos de personas ==========");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("========== consulta nombres y apellidos mayusculas ==========");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("========== consulta nombres y apellidos minusculas ==========");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);

		System.out.println("========== consulta personalizada persona upper y lower case ==========");
		List<Object[]> regs = repository.fullAllPersonDataListCase();
		regs.forEach(reg -> System.out.println("id=" + reg[0] + ", nombre = " + reg[1] + ", apellido = " + reg[2] + ", lenguaje = " + reg[3]));
	}

	@Transactional(readOnly = true)
	public void personalizedQuieriesDistinct() {
		System.out.println("========== consultas con nombres de personas ==========");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("========== consultas con nombres unicos de personas ==========");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("========== consultas con lenguaje de programacion unicos ==========");
		List<String> languages = repository.findAllProgrammintLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("========== consulta con tolta de lenguajes de programacion unicas  ==========");
		Long totalLanguage = repository.findAllProgrammintLanguageDistinctCount();
		System.out.println("Total de lenguajes de programacion : " + totalLanguage);
	}

	@Transactional(readOnly = true)
	public void personalizedQueries2() {
		System.out.println("========== Consulta por objeto persona y lenguage de programacion ==========");
		List<Object[]> personsReg = repository.findAllMixPersonDataList();

		personsReg.forEach(reg -> {
			System.out.println("programmingLanguage="+reg[1] + ", person=" + reg[0]);
		});

		System.out.println(" Consulta que puebla y devuelve objeto entity de una instancia personalizada");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("Consulta que puebla y devuelve el objeto dto de una clase personalizada:");
		List<PersonDto> personsDto = repository.findAllObjectPersonDto();
		personsDto.forEach(System.out::println);
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
