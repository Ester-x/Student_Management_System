package Student_Management_System;

import java.util.*;

class Student extends Person {
    public static final List<String> COURSES = Arrays.asList("OOP", "SAD", "DLD", "NME", "DM");
    public Map<String, Integer> absences;
    public Map<String, Double> grades;

    public List<String> getCourses() {
        return COURSES;
    }

    public Map<String, Integer> getAbsences() {
        return absences;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public Student(String name, String id) {
        super(name, id);
        absences = new HashMap<>();
        grades = new HashMap<>();
        for (String course : COURSES) {
            absences.put(course + ".Abs", 0);  // Updated to include ".Abs" in the key
            grades.put(course, 4.0); // Initialize all grades to 4 by default
        }
    }

    public void updateAbsences(String course, int count) {
        absences.put(course + ".Abs", count);  // Updated to include ".Abs" in the key
    }

    public void updateGrades(String course, double grade) {
        grades.put(course, grade);
    }

    @Override
    public double calculateAverageGrade() {
        if (COURSES.isEmpty()) {
            return 0.0;
        }

        double totalGrades = grades.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalGrades / COURSES.size();
    }

    public int calculateTotalAbsences() {
        return absences.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public void displayDetails() {
        System.out.println(super.toString() + ", Courses: " + COURSES +
                ", Grades: " + grades + ", Absences: " + absences +
                ", Average Grade: " + calculateAverageGrade() + ", Total Absences: " + calculateTotalAbsences());
    }
}
