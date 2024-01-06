import java.util.ArrayList;

public class Semester {
    private String semesterName;
    private ArrayList<Course> courses;

    // Constructor
    public Semester(String semesterName) {
        this.semesterName = semesterName;
        courses = new ArrayList<>();
    }

    // Getter method for semester name
    public String getSemesterName() {
        return semesterName;
    }

    // Add a course to the semester
    public void addCourse(Course course) {
        courses.add(course);
    }

    // Get the list of courses in the semester
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // Calculate GPA for the semester
    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Course course : courses) {
            totalPoints += course.getGrade().getGradePoint() * course.getCredits();
            totalCredits += course.getCredits();
        }

        if (totalCredits == 0) {
            return 0; // Avoid division by zero
        }

        return totalPoints / totalCredits;
    }

    // Print summary of courses
    public void printCourseSummary() {
        System.out.println("Course Summary for " + semesterName + ":");
        for (Course course : courses) {
            System.out.println("Course: " + course.getCourseName() +
                    ", Credits: " + course.getCredits() +
                    ", Grade: " + course.getGrade());
        }
    }
}
