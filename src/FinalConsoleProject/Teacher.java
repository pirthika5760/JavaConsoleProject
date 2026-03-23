package FinalConsoleProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Teacher extends User {
	public static int teacherId = 101;
	public int Id;
	public String Class;
	public String subject;
	String name;
	String section;
	String role;
	String dateOfBirth;
	String mobileNumber;

	public ArrayList<Student> students = new ArrayList<>();
	ArrayList<Teacher> teachers = new ArrayList<>();
	public ArrayList<ClassAssign> assigns = new ArrayList<>();

//	public Teacher(String userEmail, String password) {
//		super(userEmail, password);
//		this.role = "teacher";
//
//	}
//
//	public Teacher(String name, String userEmail, String password) {
//		super(userEmail, password);
//		this.Id = db.readId(userEmail);
////        this.Class = Class;
////        this.subject = subject;
//		this.name = name;
////        this.section = section;
//		this.role = "teacher";
//		this.userEmail = userEmail;
//		this.password = password;
//	}
//
//	public Teacher(int tid, String name, String userEmail, String Class, String section, String subject,
//			String password) {
//		super(userEmail, password);
//		this.Id = tid;
//		this.name = name;
//		this.Class = Class;
//		this.section = section;
//		this.subject = subject;
//		this.userEmail = userEmail;
//		this.password = password;
//
//	}

	public Teacher(String tname, String userEmail, String sClass, String sSection, String sSubject, String password) {
		super(userEmail, password);
		this.name = tname;
		this.Class = sClass;
		this.section = sSection;
		this.subject = sSubject;
	}

	public String getRole() {
		return role;
	}

	public void displayTeacherDetails() {
		System.out.println("Id " + " - " + Id + " | Name " + name + "| Email: " + userEmail + " | Class: " + Class
				+ " | Section" + section);
	}

	public void addStudent(Student s) {
		students.add(s);
	}

	public boolean detailsCollector() {
		return false;
	}

//	void assignTeacherToClass(ClassAssign ca, Teacher t) {
//		assigns.add(ca);
//		s.saveAssignDetails(ca, t);
//	}


	

	public ArrayList<ClassAssign> loadAssignDetails() {

		assigns.clear();
		File f = new File("teacher.txt");
		if (!f.exists()) {
			System.out.println("No teacher file found yet.");
			return assigns;
		}

		try {
			FileReader fr = new FileReader("assigns.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				String[] l = line.split(",");
				if (l.length >= 4) {
					ClassAssign ca = new ClassAssign(l[1], l[2], l[3]);
					ca.asId = Integer.parseInt(l[0]);
					assigns.add(ca);

				}
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			System.out.println("Error loading teachers: " + e.getMessage());
		}
		return assigns;

	}

//	void displayMyClassDetails(String Class, String section, String subject) {
//		System.out.println("Class :" + Class + "Section :" + section + "Subject :" + subject);
//	}
//
//	void attendanceMenu() {
//		System.out.println("\n1.mark Attendance By roll No\n2.Mark full Attendance");
//	}
//	
//	void attendanceReportMenu() {
//		System.out.println("\n1.Search by Date\n2.Exit");
//
//	}
//
	void viewMyClassDetails(int teacherId) {
		if (db != null) {
			db.viewMyClassDetails2(teacherId);
		}
	}
	
	public void showMenu() {
		System.out.println("=================================");
		System.out.println("        TEACHER MENU       ");
		System.out.println("=================================");
		System.out.println(" 1. View My Class Details");
		System.out.println(" 2. View Students");
		System.out.println(" 3. Mark Attendance");
		System.out.println(" 4. View Attendance Report");
		System.out.println(" 5. Change Password");
		System.out.println(" 6. Logout");
		System.out.println("=================================");
		System.out.println(" Enter your choice : ");

	}

}
