package FinalConsoleProject;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
	public ArrayList<Teacher> teachers = new ArrayList<>();
	public ArrayList<Student> students = new ArrayList<>();
	public Attendancemanager am = new Attendancemanager();
	Scanner sc = new Scanner(System.in);
	String role;
	DBQuerriesExe Db = new DBQuerriesExe();
	Commands com;
	Connection con;
	Validator validate = new Validator();
	User u;
	Teacher t;
	Student s;
	TeacherManage tm = new TeacherManage();
	StudentManage sm = new StudentManage();

	public Admin(String userEmail, String password, Attendancemanager am) {
		super(userEmail, password);
		this.am = am;

	}

	public Admin(String userEmail, String password) {
		super(userEmail, password);
		this.role = "admin";

	}

	public String getRole() {
		return role;
	}

	public void showMenu() {

		System.out.println("=================================");
		System.out.println(" 1. Teacher");
		System.out.println(" 2. Student");
		System.out.println(" 3. Configure");
		System.out.println(" 4. ViewReport");
		System.out.println(" 5. Logout");
		System.out.println("=================================");
		System.out.print(" Enter your choice : ");
	}

	public void adminTeacherMenu() {

		System.out.println("=================================");
		System.out.println(" 1. Add Teacher");
		System.out.println(" 2. View Teachers");
		System.out.println(" 3. Modify Teacher");
		System.out.println(" 4. Remove Teacher");
		System.out.println(" 5. Exit");
		System.out.println("=================================");
		System.out.print(" Enter your choice : ");

	}

	public void adminStudentMenu() {

		System.out.println("=================================");
		System.out.println(" 1. Add Student");
		System.out.println(" 2. View Students");
		System.out.println(" 3. Modify Student");
		System.out.println(" 4. Remove Student");
		System.out.println(" 5. Exit");
		System.out.println("=================================");
		System.out.print(" Enter your choice : ");
	}

	String generateEmail(String name) {
		return name.toLowerCase().replaceAll("\\s+", "") + System.currentTimeMillis() + "@zmail.com";
	}

	String generatePassword(String date, String mobile, String name) {
		String pass = date.substring(0, 4) + name.substring(0, 3) + mobile.substring(0, 4);
		System.out.println(pass);
		return pass;

	}

	// String decreptPassword(String password) {
	// 	return String.valueOf(Double.valueOf(((Math.random() * 90000) + 10000)).hashCode());

	// }

	public void addTeacher(Teacher t) {

		db.insertUser(t.userEmail, t.password, "teacher");
		db.insertTeacher(t.name, db.classid(t.Class), db.sectionid(t.section), db.subjectid(t.subject), t.userEmail,
				t.password);

		AttendanceTrackerApp.log.info("Teacher added successfully");

	}

	public void addStudent(Student s) {
		db.insertUser(s.userEmail, s.password, "student");
		db.addStudent(s.username, s.userEmail, s.password, db.classid(s.Class), db.sectionid(s.devision));

	}

	public boolean teacherdetailsCollector() {

		String tname = "";
		String date = "";
		String mobile = "";
		String sClass = "";
		String sSection = "";
		String sSubject = "";

		while (true) {
			System.out.println("Enter teacher name");
			tname = sc.nextLine().trim();

			if (validate.isValidName(tname)) {
				logger.log.info("Admin enter the teacher name" + db.readId(userEmail));
				break;
			} else {
				System.out.println("Invalid name format");
				logger.log.info("Admin entered wrong format tecaher name" + db.readId(userEmail));
			}
		}

		System.out.println("Enter date of birth (dd-MM-yyyy)");
		date = sc.nextLine().trim();
		logger.log.info("Admin enter the date of birth of the teacher" + db.readId(userEmail));

		while (true) {
			System.out.println("Enter mobile number");
			mobile = sc.nextLine().trim();

			if (validate.isValidMobile(mobile)) {
				logger.log.info("Admin enter the mobile number for teacher" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid mobile number");
				logger.log.info("Admin entered wrong format mobile number" + db.readId(userEmail));

			}
		}

		while (true) {
			System.out.println("Enter class");
			sClass = sc.nextLine().trim().toUpperCase();

			if (validate.isValidClass(sClass)) {
				logger.log.info("Admin enter the class  for teacher" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid class");
				logger.log.info("Admin entered wrong format class for teacher" + db.readId(userEmail));

			}
		}

		while (true) {
			System.out.println("Enter section");
			sSection = sc.nextLine().trim().toUpperCase();

			if (validate.isValidSection(sSection)) {
				logger.log.info("Admin enter the section for teacher" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid section");
				logger.log.info("Admin entered wrong format section for teacher " + db.readId(userEmail));

			}
		}

		while (true) {
			System.out.println("Enter handled subject");
			sSubject = sc.nextLine().trim();

			if (validate.isValidSubject(sSubject)) {
				logger.log.info("Admin enter the subject for teacher" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid subject");
				logger.log.info("Admin entered wrong format subject" + db.readId(userEmail));

			}
		}

		Teacher t = new Teacher(tname, generateEmail(tname), sClass, sSection, sSubject,
				generatePassword(date, mobile, tname));

		System.out.println("Teacher Email : " + t.userEmail);
		addTeacher(t);

		return true;
	}

//	public boolean teacherdetailsCollector() {
//		String sClass;
//		String sSubject;
//		String sSection;
//		String tname;
//		String date;
//		String mobile;
//
//		while (true) {
//
//			System.out.println("Enter teacher name");
//			tname = sc.nextLine();
//
//			System.out.println("Enter date of birth");
//			date = sc.nextLine();
//			System.out.println("Enter mobile number");
//			mobile = sc.nextLine();
//			System.out.println("Enter Class to assign");
//
//			if (validate.isValidName(tname)) {
//
//				sClass = sc.nextLine();
//               logger.log.info("Admin entere the class to the teacher"+Db.readId(userEmail));
//				break;
//			} else {
//				System.out.println("Invalid name format");
//				logger.log.info("Admin entred the wrong name format");
//				
//			}
//		}
//		while (true) {
//		    System.out.println("Enter the class");
//
//		    if (validate.isValidClass(sClass)) {
//			    sClass = sc.nextLine().trim().toUpperCase();
//
//		        logger.log.info(
//		            "Admin entered the class for the teacher " + Db.readId(userEmail)
//		        );
//		        break;
//		    } else {
//		        System.out.println("Invalid class");
//		    }
//		}
//
//		while (true) {
//			System.out.println("Enter handled Subject");
//
//			if (validate.isValidSection(sSection)) {
//				sSubject = sc.nextLine();
//				break;
//
//			} else {
//				System.out.println("Invalid section");
//				
//			}
//		}
//
//		Teacher t = new Teacher(tname, generateEmail(tname), sClass, sSection, sSubject,
//				generatePassword(date, mobile, tname));
//		System.out.println(t.userEmail);
//		addTeacher(t);
//		return true;
//	}

	public boolean studentDetailsCollector() {

		String sName = "";
		String date = "";
		String aadhar = "";
		String mobile = "";
		String sClass = "";
		String sSection = "";

		while (true) {
			System.out.println("Enter student name");
			sName = sc.nextLine().trim();

			if (validate.isValidName(sName)) {
				logger.log.info("Admin enter the student name" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid name");
				logger.log.info("Admin entered wrong format name of studnet" + db.readId(userEmail));

			}
		}

		System.out.println("Enter date of birth (yyyy-MM-dd)");
		date = sc.nextLine().trim();
		logger.log.info("Admin enter the date of birth of the student" + db.readId(userEmail));

		while (true) {
			System.out.println("Enter mobile number");
			mobile = sc.nextLine().trim();

			if (validate.isValidMobile(mobile)) {
				logger.log.info("Admin enter the mobile number of student" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid mobile number");
				logger.log.info("Admin entered wrong format mobilenumber of the student" + db.readId(userEmail));

			}
		}

		while (true) {
			System.out.println("Enter student class");
			sClass = sc.nextLine().trim().toUpperCase();

			if (validate.isValidClass(sClass)) {
				logger.log.info("Admin enter the class of the student" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid class");
				logger.log.info("Admin entered wrong format class of the student" + db.readId(userEmail));

			}
		}

		while (true) {
			System.out.println("Enter student section");
			sSection = sc.nextLine().trim().toUpperCase();

			if (validate.isValidSection(sSection)) {
				logger.log.info("Admin enter the section of the student" + db.readId(userEmail));

				break;
			} else {
				System.out.println("Invalid section");
				logger.log.info("Admin entered wrong format setion of student" + db.readId(userEmail));

			}
		}

		Student st = new Student(0, sName, generateEmail(sName), generatePassword(date, mobile, sName), sClass,
				sSection);

		addStudent(st);
		return true;
	}
}
