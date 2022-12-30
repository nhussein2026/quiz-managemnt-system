
public class Student {
    private String stdName;
    private int stdId;
    private String stdPassword;
    private String stdFaculty;
    private String stdDepartment;
    
    //here I will initialize the instructor
    public Student(String stdName, int stdId, String stdPassword, String stdFaculty, String stdDepartment) {
        this.stdName = stdName;
        this.stdId = stdId;
        this.stdPassword = stdPassword;
        this.stdFaculty = stdFaculty;
        this.stdDepartment = stdDepartment;
    }
    
    //Creating the setters  & getters

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdname) {
        this.stdName = stdname;
    }

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public String getStdPassword() {
        return stdPassword;
    }

    public void setStdPassword(String stdPassword) {
        this.stdPassword = stdPassword;
    }

    public String getStdFaculty() {
        return stdFaculty;
    }

    public void setStdFaculty(String stdFaculty) {
        this.stdFaculty = stdFaculty;
    }

    public String getStdDepartment() {
        return stdDepartment;
    }

    public void setStdDepartment(String stdDepartment) {
        this.stdDepartment = stdDepartment;
    }
    
    
}
