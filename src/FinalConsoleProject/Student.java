package FinalConsoleProject;

public class Student extends User {
	public static int studentId = 1001;
	public int Id;
	public String rollNo;
	public String username;
	public String email;
	public String devision;
	String Class;
	public int attendancecount;
	public int leavecount;
	public String role;

	Student(int Id, String username, String userEmail, String password, String Class, String devision) {
		super(userEmail, password);
		this.username = username;
		this.devision = devision;
		this.email = userEmail;
		this.Id = Id;
		this.Class = Class;
		this.role = "student";
	}

	public void showMenu() {
		System.out.println("1.View my Deatils");
		System.out.println("2.Log out");
		System.out.println("Enter your choice");
	}

	void addStudent(String name, String email, String password, int classId, int sectionId) {

	}

}
