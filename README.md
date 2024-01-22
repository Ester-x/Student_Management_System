# Student_Management_System
I. Abstract

The Student Management System is a Java application designed to manage student information within an educational setting. The application employs fundamental concepts of object-oriented programming, file manipulation, exception handling, and generic collections. It serves as a practical demonstration of skills acquired during the "Object Oriented Programming" course.

II. Key Functionalities
1. Student Management
Add Student: Allows the user to input student details such as name and ID, creating a new student in the system.
Display Students: Presents a comprehensive list of all students, showcasing their details, grades, and absences.
Update Absences: Enables the user to modify the absence count for a specific student in a given course.
Update Grades: Permits the user to update the grades of a student in a particular course.
2. Statistical Analysis
Display Average Grades: Calculates and displays the average grades of each student.
Display Total Absences: Provides the total absences for each student.
Display Course Grades and Absences: Offers insights into course-wise statistics, including average grades and total absences.
3. File Manipulation
Load Students from File: Reads student data from a CSV file during application initialization.
Save Students to File: Persists student information to a CSV file after any modification.
4. Abstraction and Inheritance
Abstract Class (Person): Represents the common attributes and behaviors shared by all entities (students).
Concrete Class (Student): Extends the abstract class, inheriting properties and methods while providing specific implementations.
5. Exception Handling
Error Handling: Provides robust error handling for various scenarios, such as invalid input during user interactions and file-related errors.

III. Methodology/Technical Parts
1. Object-Oriented Design
Classes and Objects: Utilizes classes to model entities and create objects with distinct characteristics.
Abstraction and Encapsulation: Implements abstraction through classes and encapsulation using private member variables.
Inheritance and Polymorphism: Demonstrates inheritance with the Student class inheriting from the Person class. Polymorphism is exhibited through method overriding.
2. File Handling
File I/O: Utilizes the java.nio.file package to read and write student data to a CSV file.
Exception Handling: Implements try-catch blocks to handle exceptions related to file manipulation.
3. Generic Collections
Lists and Maps: Uses List and Map data structures to manage student and course-related data efficiently.
4. GUI and Event-Driven Programming
Console-Based Interface: Provides a text-based interface for user interactions, including input validation.
Event-Driven Input: Captures user input events using the Scanner class for a dynamic and responsive user experience.

IV. Summary
The Student Management System project successfully integrates key concepts from the "Object Oriented Programming" course, showcasing proficiency in Java programming. The application offers a practical and efficient solution for managing student information, leveraging object-oriented principles and addressing real-world challenges in an educational environment. The comprehensive functionality, error handling, and use of various Java features contribute to a robust and well-structured application.
