import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class StudentGradeTracker {
    public static void main(String[] args) {
        // Create a Scanner object to take input
        Scanner scanner = new Scanner(System.in);
        // ArrayList to store student grades
        ArrayList<Integer> grades = new ArrayList<>();
        
        // Input number of students
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();
        
        // Input student grades
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.print("Enter grade for student " + (i + 1) + ": ");
            int grade = scanner.nextInt();
            grades.add(grade);
        }
        
        // Calculate the average, highest, and lowest grades
        double average = calculateAverage(grades);
        int highest = Collections.max(grades);
        int lowest = Collections.min(grades);
        
        // Display the results
        System.out.println("\nGrade Statistics:");
        System.out.println("Average grade: " + average);
        System.out.println("Highest grade: " + highest);
        System.out.println("Lowest grade: " + lowest);
        
        scanner.close();
    }
    
    // Method to calculate the average grade
    public static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }
}
