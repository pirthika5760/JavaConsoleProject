package FinalConsoleProject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

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
	DBQuerriesExe DB = new DBQuerriesExe();
	AttendanceTrackerApp logger;
	Attendancemanager am = new Attendancemanager();
	User u;

//	Student(String userEmail, String password) {
//		super(userEmail, password);
//		this.role = role;
//	}
//
//	Student(String name, String userEmail, String password) {
//		super(userEmail, password);
//		this.Id = db.readId(userEmail);
//		this.username = name;
////		this.userEmail = userEmail;
////		this.password = password;
//
//	}
//
	Student(int Id, String username, String userEmail, String password, String Class, String devision) {
		super(userEmail, password);
		this.username = username;
		System.out.println(username);
		this.devision = devision;
		this.email = userEmail;
		this.Id = studentId++;
		this.Class = Class;
		this.role = "student";
//		this.email = email;
//		this.password = password;
	}
//
//	public Student(int int1, String name, String userEmail, String password) {
//		super(userEmail, password);
//
//		this.Id = int1;
//		this.username = name;
//	}

//	public Student(String sName, String userEmail, String password, String sClass, String sSection) {
//		super(userEmail, password);
//		this.username = sName;
//		this.Class = sClass;
//		this.devision = sSection;
//
//	}

	public void showMenu() {
		System.out.println("1.View my Deatils");
		System.out.println("2.Log out");
		System.out.println("Enter your choice");
	}

	void addStudent(String name, String email, String password, int classId, int sectionId) {

	}

}
