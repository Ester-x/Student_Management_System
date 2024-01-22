package Student_Management_System;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;


class StudentManager {
    private List<Student> students;
    private Path filePath;
    private Path courseAveragesAndAbsencesFilePath;  // Declare as a class variable

    public StudentManager(String fileName, String courseAveragesAndAbsencesFileName) {
        students = new ArrayList<>();
        filePath = Paths.get(fileName);
        courseAveragesAndAbsencesFilePath = Paths.get(courseAveragesAndAbsencesFileName);  // Initialize in the constructor
        loadStudents();
    }

    private void loadStudents() {
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            } else {
                List<String> lines = Files.readAllLines(filePath);

                // Skip the first line (headers)
                if (lines.size() > 0) {
                    lines = lines.subList(1, lines.size());
                }

                for (int lineNumber = 2; lineNumber <= lines.size(); lineNumber++) {
                    String line = lines.get(lineNumber - 1);
                    String[] parts = line.split(",");
                    
                    if (parts.length >= 15) {  // Ensure that there are at least 15 elements in the array
                        String name = parts[0].trim();
                        String id = parts[1].trim();
                        Student student = new Student(name, id);

                        for (int i = 2; i < parts.length; i += 2) {
                            if (i + 1 < parts.length) {
                                String course = parts[i].trim();
                                int absences = 0;
                                double grade = 0.0;

                                try {
                                    absences = Integer.parseInt(parts[i + 1].trim());
                                    grade = Double.parseDouble(parts[i + 2].trim());
                                } catch (NumberFormatException e) {
                                    System.err.println("Error parsing absences or grades at line " + lineNumber + ": " + e.getMessage());
                                    System.err.println("Line content: " + line);
                                    return; // Skip this line to prevent adding an incomplete student
                                }

                                student.updateAbsences(course, absences);
                                student.updateGrades(course, grade);
                            }
                        }
                        students.add(student);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    private void saveStudents() {
        List<String> lines = new ArrayList<>();
        // Add CSV file headers
        lines.add("Name,Student ID," +
                String.join(",", Student.COURSES.stream().map(course -> course + ", " + course + ".Abs").collect(Collectors.toList())) +
                ",Average Grade,Total Absences");
        for (Student student : students) {
            StringBuilder line = new StringBuilder(student.getName() + "," + student.getId());
            for (String course : Student.COURSES) {
                line.append(",").append(student.getGrades().get(course)).append(",").append(student.getAbsences().get(course + ".Abs"));
            }
            line.append(",").append(student.calculateAverageGrade()).append(",").append(student.calculateTotalAbsences());
            lines.add(line.toString());
        }
        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public void addStudent(Student student) {
        // Check if a student with the same ID already exists
        boolean studentExists = students.stream().anyMatch(s -> s.getId().equals(student.getId()));

        if (studentExists) {
            System.out.println("A student with the same ID already exists.");
        } else {
            students.add(student);
            saveStudents();
            System.out.println("Student added successfully.");
        }
    }


    public void displayStudents() {
        for (Student student : students) {
            student.displayDetails();
        }
    }

    public void updateAbsences(String studentId, String course, int count) {
        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId().equals(studentId)).findFirst();
        optionalStudent.ifPresent(student -> {
            student.updateAbsences(course, count);
            saveStudents();
        });

        if (optionalStudent.isEmpty()) {
            System.err.println("Error: Student with ID " + studentId + " not found.");
        }
    }

    public void updateGrades(String studentId, String course, double grade) {
        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId().equals(studentId)).findFirst();
        optionalStudent.ifPresent(student -> {
            student.updateGrades(course, grade);
            saveStudents();
        });

        if (optionalStudent.isEmpty()) {
            System.err.println("Error: Student with ID " + studentId + " not found.");
        }
    }

    public void displayAverageGrades() {
        for (Student student : students) {
            double averageGrade = student.calculateAverageGrade();
            System.out.println("Student ID: " + student.getId() + ", Average Grade: " + averageGrade);
        }
    }

    public void displayTotalAbsences() {
        for (Student student : students) {
            int totalAbsences = student.calculateTotalAbsences();
            System.out.println("Student ID: " + student.getId() + ", Total Absences: " + totalAbsences);
        }
    }

    public void displayCourseGradesAndAbsences() {
        for (String course : Student.COURSES) {
            double courseAverageGrade = students.stream()
                    .mapToDouble(student -> student.getGrades().getOrDefault(course, 0.0))
                    .average()
                    .orElse(0.0);

            int totalCourseAbsences = students.stream()
                    .map(student -> student.getAbsences().get(course + ".Abs"))  // Updated to handle null value
                    .filter(Objects::nonNull)  // Filter out null values
                    .mapToInt(Integer::intValue)
                    .sum();

            System.out.println("Course: " + course + ", Average Grade: " + courseAverageGrade +
                    ", Total Absences: " + totalCourseAbsences);
        }
        saveCourseAveragesAndAbsences();
        System.out.println("Course averages and absences saved to file.");

    }
    private void saveCourseAveragesAndAbsences() {
        List<String> lines = new ArrayList<>();
        // Add CSV file headers for course averages and absences
        lines.add("Course,Average Grade,Total Absences");

        for (String course : Student.COURSES) {
            double courseAverageGrade = students.stream()
                    .mapToDouble(student -> student.getGrades().getOrDefault(course, 0.0))
                    .average()
                    .orElse(0.0);

            int totalCourseAbsences = students.stream()
                    .map(student -> student.getAbsences().get(course + ".Abs"))
                    .filter(Objects::nonNull)
                    .mapToInt(Integer::intValue)
                    .sum();

            lines.add(course + "," + courseAverageGrade + "," + totalCourseAbsences);
        }

        try {
            Files.write(courseAveragesAndAbsencesFilePath, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            System.err.println("Error writing to course averages and absences file: " + e.getMessage());
        }
    }
}
