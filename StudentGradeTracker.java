
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    ArrayList<Double> marks = new ArrayList<>();

    Student(int rollNo, String name, ArrayList<Double> marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks.addAll(marks);
    }

    double average() {
        if (marks.isEmpty()) return 0;
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum / marks.size();
    }

    double highest() {
        double max = marks.get(0);
        for (double mark : marks) {
            if (mark > max) max = mark;
        }
        return max;
    }

    double lowest() {
        double min = marks.get(0);
        for (double mark : marks) {
            if (mark < min) min = mark;
        }
        return min;
    }

    void display() {
        System.out.println("\nRoll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Marks: " + marks);
        System.out.printf("Average: %.2f%n", average());
        System.out.println("Highest: " + highest());
        System.out.println("Lowest: " + lowest());
    }
}

public class StudentGradeTracker {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static void addStudent() {
        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.println("Roll number already exists!");
                return;
            }
        }

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int count = sc.nextInt();

        if (count <= 0) {
            System.out.println("Invalid subject count!");
            return;
        }

        ArrayList<Double> marks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            double mark = sc.nextDouble();

            if (mark < 0 || mark > 100) {
                System.out.println("Marks must be between 0 and 100.");
                i--;
            } else {
                marks.add(mark);
            }
        }

        students.add(new Student(roll, name, marks));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.rollNo == roll) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void deleteStudent() {
        System.out.print("Enter roll number: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.rollNo == roll) {
                students.remove(s);
                System.out.println("Student deleted successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void classSummary() {
        if (students.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        double total = 0;
        double highest = -1;
        double lowest = 101;
        Student topper = null;

        for (Student s : students) {
            double avg = s.average();
            total += avg;

            if (avg > highest) {
                highest = avg;
                topper = s;
            }

            if (avg < lowest) {
                lowest = avg;
            }
        }

        System.out.printf("Class average: %.2f%n",
                total / students.size());
        System.out.printf("Highest student average: %.2f%n", highest);
        System.out.printf("Lowest student average: %.2f%n", lowest);
        System.out.println("Topper: " + topper.name);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== STUDENT GRADE TRACKER =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Class Summary");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    classSummary();
                    break;
                case 6:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}