package Student_Management_System;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
        	StudentManager studentManager = new StudentManager("students.csv", "courses.csv");

            System.out.println("Student Management System");

            while (true) {
                System.out.println("\n1. Add Student");
                System.out.println("2. Display Students");
                System.out.println("3. Update Absences");
                System.out.println("4. Update Grades");
                System.out.println("5. Display Average Grades");
                System.out.println("6. Display Total Absences");
                System.out.println("7. Display Course Grades and Absences");
                System.out.println("8. Exit");
                System.out.println("Enter your choice: ");

                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                    continue;
                }

                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        try {
                            System.out.println("Enter student name: ");
                            String name = scanner.nextLine();

                            System.out.println("Enter student ID: ");
                            String id = scanner.nextLine();

                            Student student = new Student(name, id);

                            studentManager.addStudent(student);
                        } catch (InputMismatchException | NumberFormatException e) {
                            System.err.println("Invalid input. Please enter valid data.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;

                    case 2:
                        System.out.println("Student List:");
                        studentManager.displayStudents();
                        break;

                    case 3:
                        try {
                            System.out.println("Enter student ID: ");
                            String studentId = scanner.nextLine();

                            System.out.println("Enter course name: ");
                            String courseToUpdate = scanner.nextLine();

                            System.out.println("Enter new absence count: ");
                            int newAbsenceCount = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            studentManager.updateAbsences(studentId, courseToUpdate, newAbsenceCount);
                        } catch (InputMismatchException | NumberFormatException e) {
                            System.err.println("Invalid input. Please enter valid data.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;

                    case 4:
                        try {
                            System.out.println("Enter student ID: ");
                            String studentId = scanner.nextLine();

                            System.out.println("Enter course name: ");
                            String courseToUpdate = scanner.nextLine();

                            System.out.println("Enter new grade: ");
                            double newGrade = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline

                            studentManager.updateGrades(studentId, courseToUpdate, newGrade);
                        } catch (InputMismatchException | NumberFormatException e) {
                            System.err.println("Invalid input. Please enter valid data.");
                            scanner.nextLine(); // Consume invalid input
                        }
                        break;

                    case 5:
                        studentManager.displayAverageGrades();
                        break;

                    case 6:
                        studentManager.displayTotalAbsences();
                        break;

                    case 7:
                        studentManager.displayCourseGradesAndAbsences();
                        break;

                    case 8:
                        System.out.println("Exiting program.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            }
        }
    }
}

