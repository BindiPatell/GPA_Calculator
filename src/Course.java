public class Course {
    private String courseName;
    private int credits;
    private Grade grade;

    // Constructor
    public Course(String courseName, int credits, Grade grade) {
        this.courseName = courseName;
        this.credits = credits;
        this.grade = grade;
    }

    // Getter methods
    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public Grade getGrade() {
        return grade;
    }
}