package FinalConsoleProject;
public class Attendancetrack {
    public Student student;
    public String date;
    public boolean presenting;
    String Class;
    String section;
    String isMarked;
    int atId;
    String sName;
    String status;

    public Attendancetrack(Student student, String date, boolean presenting) {
        this.student = student;
        this.date = date;
        this.presenting = presenting;
    }

    public Attendancetrack(String sName,String Class, String section, String date,String status, String ismarked) {
        this.Class = Class;
        this.date = date;
        this.presenting = presenting;
        this.section = section;
        this.isMarked = ismarked;
        this.sName=sName;
        this.status=status;

    }
}