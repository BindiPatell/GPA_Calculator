import java.util.*;

public class GPACalculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Semester> semestersMap = new HashMap<>();

        while (true) {
            System.out.print("Enter semester name (or 'done' to finish): ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String semesterName = scanner.nextLine();

            if (semesterName.equalsIgnoreCase("done")) {
                break;
            }

            // Validate that semester name is not empty and doesn't already exist
            if (semesterName.trim().isEmpty()) {
                System.out.println("Semester name cannot be empty. Please enter a valid semester name.");
                continue;
            }

            if (semestersMap.containsKey(semesterName)) {
                System.out.println("Semester '" + semesterName + "' already exists. Please enter a different name.");
                continue;
            }

            // Create a semester
            Semester semester = new Semester(semesterName);

            while (true) {
                System.out.print("Enter course name (or 'done' to finish): ");
                String courseName = scanner.nextLine();

                if (courseName.equalsIgnoreCase("done")) {
                    break;
                }

                int credits;
                Grade grade;

                // Validate credits input
                while (true) {
                    System.out.print("Enter credits for the course: ");
                    if (scanner.hasNextInt()) {
                        credits = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid numeric value for credits.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                }

                // Validate grade input using Grade enum
                while (true) {
                    System.out.print("Enter grade for the course (A, B, C, D, F): ");
                    try {
                        grade = Grade.valueOf(scanner.nextLine().toUpperCase());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input. Please enter a valid grade (A, B, C, D, F).");
                    }
                }

                // Updated weighted course logic
                System.out.print("Is this a weighted course? (y/n): ");
                boolean weighted = scanner.nextLine().equalsIgnoreCase("y");

                // Create a course and add it to the semester
                Course course = new Course(courseName, credits, grade, weighted);
                semester.addCourse(course);
            }

            // Print summary of courses for the semester
            semester.printCourseSummary();

            // Calculate and display GPA for the semester
            double gpa = semester.calculateGPA();
            System.out.println("Your GPA for " + semester.getSemesterName() + " is: " + gpa);

            // Add the semester to the map of semesters
            semestersMap.put(semesterName, semester);
        }

        if (semestersMap.isEmpty()) {
            System.out.println("No semesters entered. GPA calculation not possible.");
        } else {
            // Calculate and display overall GPA across all semesters
            double overallGPA = calculateOverallGPA(semestersMap.values());
            System.out.println("Your overall GPA is: " + overallGPA);
        }
    }

    /**
     * Calculates the overall GPA across all semesters.
     *
     * @param semesters The collection of semesters for which to calculate the overall GPA.
     * @return The overall GPA calculated across all semesters.
     */
    private static double calculateOverallGPA(Iterable<Semester> semesters) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Semester semester : semesters) {
            totalPoints += semester.calculateGPA() * getTotalCredits(semester);
            totalCredits += getTotalCredits(semester);
        }

        if (totalCredits == 0) {
            return 0; // Avoid division by zero
        }

        return totalPoints / totalCredits;
    }

    // Helper method to calculate total credits for a semester
    private static int getTotalCredits(Semester semester) {
        int totalCredits = 0;

        for (Course course : semester.getCourses()) {
            totalCredits += course.getCredits();
        }

        return totalCredits;
    }
}
