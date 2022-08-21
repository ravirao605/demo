package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	public static void doSomething() {
		System.out.println("Did something!");
	}
	public static void doSomethingElse() {
		System.out.println("Did something else for feature 3.0!");
	}
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		System.out.println("This is a new feature!");
		return args -> {
			Student maria = new Student(
					"Maria",
					"Jones",
					"maria.jones@amigoscode.edu",
					21
			);

			Student ahmed = new Student(
					"Ahmed",
					"Ali",
					"ahmed.ali@amigoscode.edu",
					18
			);
			// Bug fix
			System.out.println("Adding maria and ahmed as List");
			List<Student> students = new ArrayList<>();
			students.add(maria);
			students.add(ahmed);
			studentRepository.saveAll(students);

			System.out.print("Number of students: ");
			System.out.println(studentRepository.count());

			Optional<Student> student = studentRepository.findById(2L);

			student = studentRepository.findById(3L);

			student.ifPresent(System.out::println);

			System.out.println("Select all students");
			List<Student> students2 = studentRepository.findAll();
			students.forEach(System.out::println);

			System.out.println("Delete maria");
			studentRepository.deleteById(1L);

			System.out.print("Number of students: ");
			System.out.println(studentRepository.count());

			Student xxx =studentRepository.getStudentsByEmail("wrfaofgvr@divg.com");
			if(xxx==null) {
				System.out.println("The NULLLLL");
			}
		};
	}
}
