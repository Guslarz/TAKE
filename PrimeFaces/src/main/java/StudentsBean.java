/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Guslarz
 */
@Named(value = "studentsBean")
@ApplicationScoped
public class StudentsBean {
    
    private static final List<String> NAMES = Arrays.asList(
            "John", "Jane", "Mary", "Michael", "James"
    );
    
    private static final List<String> SURNAMES = Arrays.asList(
            "Smith", "Doe", "Johnson", "Williams", "Brown"
    );
            
    private final List<Student> students;

    /**
     * Creates a new instance of StudentsBean
     */
    public StudentsBean() {
        students = generateStudents(30);
    }

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }
    
    private static List<Student> generateStudents(int count) {
        System.out.println("XDD");
        Random random = new Random();
        return Stream.generate(() -> generateStudent(random))
                .limit(count)
                .collect(Collectors.toList());
    }
    
    private static Student generateStudent(Random random) {
        return new Student(
                NAMES.get(random.nextInt(NAMES.size())),
                SURNAMES.get(random.nextInt(SURNAMES.size())),
                (double) Math.round((random.nextDouble() * 3 + 2) * 100) / 100
        );
    }
}
