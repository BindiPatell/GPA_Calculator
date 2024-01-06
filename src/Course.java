public class Course {

    private String courseName;
    private int credits;
    private Grade grade;
    private boolean weighted; // New field

    public Course(String courseName, int credits, Grade grade, boolean weighted) {
        this.courseName = courseName;
        this.credits = credits;
        this.grade = grade;
        this.weighted = weighted; // Set weighted field
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public Grade getGrade() {
        return grade;
    }

    public boolean isWeighted() {
        return weighted;
    }

}
