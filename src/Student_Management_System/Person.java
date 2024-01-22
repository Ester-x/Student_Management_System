package Student_Management_System;

abstract class Person {
    private String name;
    private String id;

    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public abstract double calculateAverageGrade();

    public abstract void displayDetails();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name;
    }
}
