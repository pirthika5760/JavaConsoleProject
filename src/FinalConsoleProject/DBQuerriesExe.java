package FinalConsoleProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;

public class DBQuerriesExe {

	Connection con = DBConnection.getInstance().getConnection();

	static Logger log = LogManager.getLogger();

	public void insertUser(String email, String password, String role) {

		try {

			PreparedStatement ps = con.prepareStatement(Commands.WRITE_U);

			ps.setString(1, email);
			ps.setString(2, password);
			ps.setString(3, role);

			ps.executeUpdate();
			log.trace("new user inserted" + readId(email));

		} catch (Exception e) {
			System.out.println("Invalid credential");
			log.error("failed to insert user ", e);
		}
	}

	public int readId(String email) {
		try {

			PreparedStatement ps = con.prepareStatement(Commands.READ_U);

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log.trace("user id get");

				return rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("There is no user exist");
			log.error("can not get user id", e);
		}
		return -1;
	}

	String readRole(String email) {
		try {

			PreparedStatement ps = con.prepareStatement(Commands.READ_Role);

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log.info("User role is readed successfully" + readId(email));
				return rs.getString(1);
			} else {
				log.trace("Invalid email or password entered by user" + readId(email));
				return "Invlaid email or password";
			}

		}

		catch (Exception e) {
			System.out.println("Invalid email or password.Try again");
			log.error("Error in read role of the user" + readId(email), e);
		}
		return null;
	}

	void insertTeacher(String Tname, int classId, int sectionId, int subjectId, String email, String password) {

		if (classId == -1 || sectionId == -1 || subjectId == -1) {
			System.out.println("Invalid class / section / subject");
			return;
		}

		try {
			String teacherSql = Commands.INSER_TEACHER;

			PreparedStatement ps = con.prepareStatement(teacherSql);
			ps.setString(1, Tname);
			ps.setInt(2, readId(email));
			ps.setInt(3, classId);
			ps.setInt(4, sectionId);
			ps.setInt(5, subjectId);
			ps.executeUpdate();
			System.out.println("Teacher added successfully");

			log.info("Teacher inserted successfully" + readId(email));

		}

		catch (Exception e) {
			System.out.println("invalid creadential");
			log.error("Teacher Inserted");
		}
	}

	void readTeachers2() {
		ArrayList<Teacher> teachers = new ArrayList<>();
		int tid = 0;
		try {

			PreparedStatement ps = con.prepareStatement(Commands.READ_Details);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				tid = rs.getInt("tid");
				String name = rs.getString("tname");
				String email = rs.getString("temail");
				String className = rs.getString("tclass");
				String section = rs.getString("tsection");
				String subject = rs.getString("tsubject");
				System.out.println("Id " + " - " + tid + " | Name " + name + "| Email: " + email + " | Class: "
						+ className + " | Section " + section + " |Subject " + subject);
				if (className == null || section == null) {
					System.out.println("Id " + " - " + tid + " | Name " + name + "| Email: " + email + " | Class: "
							+ "yet to be assign");
				}
				log.trace("Teachers information showed successfully to admin" + tid);

			}

		} catch (Exception e) {
			System.out.println("No teacher Exist");
			log.error("failed to get and show teachers information" + tid);
		}

	}

	void insertConfig2(int days, int holidays) {
		try {

			int totaldays = days;
			PreparedStatement ps = con.prepareStatement(Commands.CONFIG);
			ps.setInt(1, days);
			ps.setInt(2, holidays);
			int rs = ps.executeUpdate();

			System.out.println("Updated successfully");
			log.info("configure setup added successfully by admin user id:1");

		} catch (Exception e) {
			System.out.println("Enter correct data");
			log.error("can not insert the configureationsetup user id:1");
		}
	}

	void listHolidays() {
		try {
			PreparedStatement ps = con.prepareStatement(Commands.HOLIDAY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getDate("h_date"));
			}
			log.info("all holidays are listed");

		} catch (Exception e) {
			System.out.println("No holidays");
			log.error("can not get holidays list");
		}
	}

	void removeHolidayDB(String date) {
		try {

			PreparedStatement ps = con.prepareStatement(Commands.remove_HOLIDAY);
			ps.setDate(1, java.sql.Date.valueOf(date));
			int rs = ps.executeUpdate();

			System.out.println("removed successfully");
			log.info("holiday removed sucessfully by admin");

		} catch (Exception e) {
			System.out.println("No holiday");
			log.error("can not remove holiday by admin");
		}
	}

	void addHoliday2(String date, String name) {
		try {

			PreparedStatement ps = con.prepareStatement(Commands.ADD_HOLIDAY);
			ps.setDate(1, java.sql.Date.valueOf(date));
			ps.setString(2, name);
			int rs = ps.executeUpdate();

			System.out.println("added successfully");
			log.info("holiday added successfully in the hoiliday list by admin");

		} catch (Exception e) {
			System.out.println("No holiday");
			log.error("can not add holiday to the list to admin");
		}
	}

	int classid(String class_name) {
		int classId = 0;
		PreparedStatement pstClass;
		try {

			pstClass = con.prepareStatement(Commands.CLASS_ID);

			pstClass.setString(1, class_name);
			ResultSet rsClass = pstClass.executeQuery();
			if (rsClass.next()) {
				log.info("classid for the class name obtained ");
				return classId = rsClass.getInt("class_id");
			} else {
				System.out.println("Class not found!");

				return -1;
			}

		} catch (SQLException e) {
			System.out.println("enter correct format");
			log.error("can not obtain the id of the class");
		}
		return classId;
	}

	int sectionid(String section) {
		try {

			int sectionId = 0;
			PreparedStatement pstSection = con.prepareStatement(Commands.SECTION_ID);
			pstSection.setString(1, section);
			ResultSet rsSection = pstSection.executeQuery();
			if (rsSection.next()) {
				log.info("sectionid for the section  name obtained");

				return sectionId = rsSection.getInt("section_id");
			} else {
				System.out.println("Section not found!");
				log.error("can not obtain the id of the section");

				return -1;
			}

		} catch (Exception e) {
			System.out.println("enter correct format");

		}
		return 0;
	}

	int subjectid(String subject) {
		try {
			System.out.println(subject);

			int subjectId = 0;
			PreparedStatement pstSubject = con.prepareStatement(Commands.SUBJECT_ID);
			pstSubject.setString(1, subject);
			ResultSet rsSubject = pstSubject.executeQuery();

			if (rsSubject.next()) {
				log.info("subjectid  for the subject name obtained");

				return rsSubject.getInt("subject_id");
			} else {
				System.out.println("Subject not found!");
				log.error("can not obtain the id of the subject");

				return -1;
			}

		} catch (Exception e) {
			System.out.println("enter correct format");

		}
		return 0;
	}

	void updateTeacherById(int teacherId, String newName, int newClassId, int newSectionId, int newSubjectId) {

		if (newClassId == -1 || newSectionId == -1 || newSubjectId == -1) {
			System.out.println("Invalid class / section / subject");
			return;
		}

		String sql = Commands.UPDATE_TEACHER_BY_ID;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newName);
			ps.setInt(2, newClassId);
			ps.setInt(3, newSectionId);
			ps.setInt(4, newSubjectId);
			ps.setInt(5, teacherId);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Teacher updated successfully by admin " + teacherId);
			} else {
				System.out.println("Teacher ID not found");
			}
			log.info("teacher id upadted ");

		} catch (Exception e) {
			System.out.println("enter valid id please");
			log.error("can not upadte tecaher by id:" + teacherId);
		}
	}

	void updateTeacherName(int teacherId, String name) {
		String sql = Commands.UPDATE_TECAHER_NAME;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, teacherId);
			ps.executeUpdate();
			System.out.println("Name updated");
			log.info("teacher name updated by admin" + teacherId);

		} catch (Exception e) {
			System.out.println("enter valid id please");
			log.error("can not update teacher name" + teacherId);
		}
	}

	void updateTeacherClass(int teacherId, int classId) {

		if (classId == -1) {
			System.out.println("Invalid class");
			return;
		}

		String sql = Commands.UPDATE_TECHER_CLASS;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, classId);
			ps.setInt(2, teacherId);
			ps.executeUpdate();
			System.out.println("Class updated");
			log.info("teacher class updated" + teacherId);

		} catch (Exception e) {
			System.out.println("enter valid id please");
			log.error("can not update teacher class" + teacherId);

		}
	}

	void updateTeacherSection(int teacherId, int sectionId) {

		if (sectionId == -1) {
			System.out.println("Invalid section");
			return;
		}

		String sql = Commands.UPDATETEACHERSECTION;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sectionId);
			ps.setInt(2, teacherId);
			ps.executeUpdate();
			System.out.println("Section updated");
			log.info("teacher class section updated by admin" + teacherId);

		} catch (Exception e) {
			System.out.println("enter valid id please");
			log.error("can not update teacher section by admin" + teacherId);

		}
	}

	void updateTeacherSubject(int teacherId, int subjectId) {

		if (subjectId == -1) {
			System.out.println("Invalid subject");
			return;
		}

		String sql = Commands.UPDATE_TEACHER_SUBJECT;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, subjectId);
			ps.setInt(2, teacherId);
			ps.executeUpdate();
			System.out.println("Subject updated");
			log.info("teacher subject updated by admin" + teacherId);

		} catch (Exception e) {
			System.out.println("enter valid id please");
			log.error("can not update teacher subject" + teacherId);

		}
	}

	void deleteTeacher(int teacherId) {
		int userId = -1;

		try {

			try {
				String getUserSql = Commands.REMOVE_TEACHER;
				PreparedStatement ps = con.prepareStatement(getUserSql);

				ps.setInt(1, teacherId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					userId = rs.getInt("userid");
				} else {
					System.out.println("Teacher not found" + teacherId);
					return;
				}
			} catch (Exception e) {
				System.out.println("enter valid id please" + teacherId);
			}

			try {

				String delTeacherSql = "DELETE FROM teacher WHERE tid=?";
				PreparedStatement ps = con.prepareStatement(delTeacherSql);
				ps.setInt(1, teacherId);
				ps.executeUpdate();
				log.info("teacher removed from tecaher table " + teacherId);

			} catch (Exception e) {
				System.out.println("enter valid id please");
				log.error("can not remove teacher from tecaher table" + teacherId);

			}

			try {
				String delUserSql = "DELETE FROM users WHERE userid=?";
				PreparedStatement ps = con.prepareStatement(delUserSql);
				ps.setInt(1, userId);
				ps.executeUpdate();
				System.out.println("Teacher removed successfully");
				log.info("teacher removed from user table " + teacherId);

			} catch (Exception e) {
				System.out.println("enter valid id please");
				log.error("can not remove teacher from user table teacher id:" + teacherId);

			}

		} catch (Exception e) {
			// try {
			// if (com.con != null)
			// com.con.rollback();
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// }
			e.printStackTrace();
		}
	}

	boolean teacherExists(int teacherId) {

		String sql = Commands.TECHER_EXIST;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, teacherId);
			ResultSet rs = ps.executeQuery();
			log.info("teacher exist checked sucessfully teacher id:" + teacherId);
			return rs.next();

		} catch (Exception e) {
			System.out.println("enter valid id please");
			log.error("error in get teacher id to cheak exist teacher id" + teacherId);
		}
		return false;
	}

	boolean studentExists(int sId) {

		String sql = Commands.STUDENT_EXIST;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sId);
			ResultSet rs = ps.executeQuery();
			log.info("student exist checked sucessfully student id" + sId);

			return rs.next();

		} catch (Exception e) {
			System.out.println("enter valid id please");
			log.error("error in get student id to cheak exist student id:" + sId);

		}
		return false;
	}

	void addStudent(String name, String email, String password, int classId, int sectionId) {

		if (classId == -1 || sectionId == -1) {
			System.out.println("Invalid class / section");
			return;
		}

		try

		{

			String studentSql = Commands.ADD_STUDENT;
			PreparedStatement ps = con.prepareStatement(studentSql);

			ps.setString(1, name);
			ps.setInt(2, readId(email));
			ps.setInt(3, classId);
			ps.setInt(4, sectionId);
			ps.executeUpdate();

			System.out.println("Student added successfully");
			log.info("student inserted by admin student id:" + readId(email));

		} catch (Exception e) {

			System.out.println("Invalid credentials");
			log.error("can not insert student. student id:" + readId(email));
		}
	}

	void viewStudents() {

		String sql = Commands.VIEW_STUDENT;

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				log.info("Student details showed ");
				System.out.println(rs.getInt("sid") + " | " + rs.getString("name") + " | " + rs.getString("email")
						+ " | " + rs.getString("class_name") + " | " + rs.getString("section_name"));
			}

		} catch (Exception e) {
			System.out.println("Error in get stuents list");
			log.error("can not get student details to show admin");
		}
	}

	void modifyStudentMenu(int sid) {

		Scanner sc = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\n1.Change Name");
			System.out.println("2.Change Class");
			System.out.println("3.Change Section");
			System.out.println("4.Exit");

			choice = sc.nextInt();

			switch (choice) {
				case 1:
					System.out.println("New name: ");
					updateStudentName(sid, sc.next());
					break;

				case 2:
					System.out.println("New class: ");
					updateStudentClass(sid, classid(sc.next()));
					break;

				case 3:
					System.out.println("New section: ");
					updateStudentSection(sid, sectionid(sc.next()));
					break;
			}

		} while (choice != 4);
	}

	void modifyTeacherMenu2(int teacherId) {

		Scanner sc = new Scanner(System.in);
		int choice;

		while (true) {
			System.out.println("\nModify Teacher");
			System.out.println("1. Change Name");
			System.out.println("2. Change Class");
			System.out.println("3. Change Section");
			System.out.println("4. Change Subject");
			System.out.println("5. Exit");
			log.info("Modify teacher menu showed");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
				case 1:
					System.out.println("Enter new name: ");
					sc.nextLine();
					String name = sc.nextLine();
					log.info("Admin entered the new name to Teacher" + "admin id 1" + "modify teacher id" + teacherId);
					;
					updateTeacherName(teacherId, name);
					break;

				case 2:
					System.out.println("Enter new class: ");
					int classId = sc.nextInt();
					sc.nextLine();
					log.info("Admin entered the new class to Teacher" + "admin id 1" + "modify teacher id" + teacherId);
					;

					updateTeacherClass(teacherId, classId);
					break;

				case 3:
					System.out.println("Enter new section: ");
					String section = sc.nextLine();
					int sectionId = sectionid(section);
					log.info("Admin entered the new section to Teacher" + "admin id 1" + "modify teacher id"
							+ teacherId);
					;

					updateTeacherSection(teacherId, sectionId);
					break;

				case 4:
					System.out.println("Enter new subject: ");
					String subject = sc.nextLine();
					int subjectId = subjectid(subject);
					log.info("Admin entered the new subject to Teacher" + "admin id 1" + "modify teacher id"
							+ teacherId);
					;

					updateTeacherSubject(teacherId, subjectId);
					break;

				case 5:
					System.out.println("Exit modify menu");
					log.info("Admin exit from modify menu admin id 1");
					;

					return;
				// break;

				default:
					System.out.println("Invalid choice");
					log.error("Admin entered the invalid choice in modify teacher menu" + "admin id 1"
							+ "modify teacher id"
							+ teacherId);
					;

			}

		}
	}

	void updateStudentName(int sid, String name) {
		String sql = Commands.UPDATE_STUDENT_NAME;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, sid);
			ps.executeUpdate();
			System.out.println("Name updated student id :" + sid);
		} catch (Exception e) {
			System.out.println("enter valid id please student id:" + sid);
		}
	}

	void updateStudentClass(int sid, int classId) {
		if (classId == -1)
			return;
		String sql = Commands.UPDATE_STUDENT_CLASS;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, classId);
			ps.setInt(2, sid);
			ps.executeUpdate();
			System.out.println("Class updated student id:" + sid);
		} catch (Exception e) {
			System.out.println("enter valid id please");
		}
	}

	void updateStudentSection(int sid, int sectionId) {
		if (sectionId == -1)
			return;
		String sql = Commands.UPDATE_STUDENT_SECTION;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sectionId);
			ps.setInt(2, sid);
			ps.executeUpdate();
			System.out.println("Section updated student id:" + sid);
		} catch (Exception e) {
			System.out.println("enter valid id please");
		}
	}

	void deleteStudent(int sid) {

		try {

			int userId = -1;

			try {
				String getSql = Commands.REMOVE_STUDENT;

				PreparedStatement ps = con.prepareStatement(getSql);
				ps.setInt(1, sid);
				ResultSet rs = ps.executeQuery();
				if (rs.next())
					userId = rs.getInt("userid");
				else {
					System.out.println("Student not found student id:" + sid);
					return;
				}
			} catch (Exception e) {
				System.out.println("enter valid id please");
			}

			try {
				PreparedStatement ps = con.prepareStatement(Commands.DELETE_STUDENT_IN_STUDENT_TABLE);
				ps.setInt(1, sid);
				ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("enter valid id please");

			}

			try {
				PreparedStatement ps = con.prepareStatement(Commands.DELETE_STUDENT_IN_USER_TABLE);
				ps.setInt(1, userId);
				ps.executeUpdate();
				System.out.println("Student removed");

			} catch (Exception e) {
				System.out.println("enter valid id please");
			}

		} catch (Exception e) {

			System.out.println("enter valid id please");
		}
	}

	void viewMyClassDetails2(int teacherId) {
		int tid = 0;
		try {
			String qry = Commands.GET_TEACHER_ID;

			PreparedStatement ps = con.prepareStatement(qry);

			ps.setInt(1, teacherId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tid = rs.getInt("tid");
			} else {
				System.out.println("No teacher found for this userid teacher id" + teacherId);
				return;
			}
		} catch (Exception e) {

			System.out.println("Error in view class details teacher id:" + teacherId);

		}

		try {
			String sql = Commands.VIEW_MY_CLASS_DETAILS_2;

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, tid);
			ResultSet rs = ps.executeQuery();
			System.out.println("2");

			while (rs.next()) {
				String cls = rs.getString("class_name");
				System.out.println("Name  : " + rs.getString("name"));
				System.out.println("ID :" + rs.getInt("tid"));
				System.out.println("Class   : " + rs.getString("class_name"));
				System.out.println("Section : " + rs.getString("section_name"));
				System.out.println("Subject : " + rs.getString("subject_name"));
				log.info("Teacher details showed successfully" + teacherId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean checkUserExist(String email, String password) {

		try {
			String sql = Commands.CHECK_USER_LIST;

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, password);
			// ps.setString(3, role);
			ResultSet rs = ps.executeQuery();
			log.info("user exist checked" + readId(email));

			return rs.next();

		} catch (Exception e) {
			System.out.println("invalid credential user id:" + readId(email));
		}
		return false;

	}

	String checkUserRole(String email, String password) {

		try {
			String sql = Commands.CHECK_USER_ROLE;

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String role = rs.getString("role");
				return role;
			}
			log.info("user role checked user id:" + readId(email));

		} catch (Exception e) {
			System.out.println("invalid credential");
			log.error("error in get user to check role. user id:" + readId(email));
		}
		return null;

	}

	void viewMyStudents(int teacherId) {
		int tid = 0;
		try {
			String qry = Commands.GET_TEACHER_ID;

			PreparedStatement ps = con.prepareStatement(qry);

			ps.setInt(1, teacherId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tid = rs.getInt("tid");
			} else {
				System.out.println("No teacher found for this userid :" + teacherId);
				return;
			}
		} catch (Exception e) {
			System.out.println("invalid credential teacher id:" + teacherId);

		}
		int sids = 0;

		try {
			String sql = Commands.VIEW_MY_STUDENT;

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, tid);
			ResultSet rs = ps.executeQuery();

			System.out.println("\nStudents List:");
			while (rs.next()) {
				System.out.println(rs.getInt("sid") + " - " + rs.getString("name"));
				sids = (rs.getInt("sid"));

				log.info("student of the teacher showed successfully student id" + rs.getInt("sid") + "teacher id:"
						+ teacherId);

			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("error in get student details to show teacher student id :" + sids + "teacher id:" + teacherId);
		}
	}

	void viewAttendanceReport(int teacherId) {

		String sql = Commands.VIEW_ATTENDANCE_REPRT;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, teacherId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("name") + " | " + rs.getDate("date") + " | " + rs.getString("status"));
			}
			log.info("attendance report showed suceesfully");

		} catch (Exception e) {
			System.out.println("invalid id");
			log.error("error in shoe attendance report");
		}
	}

	void changePassword2(String teacherId, Scanner sc) {

		System.out.print("Enter new password(ex:123456: ");
		String newPass = sc.nextLine();

		String sql = Commands.CHANGE_PASSWORD;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			int id = Integer.valueOf(teacherId);
			ps.setString(1, newPass);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("Password changed");
			log.info("password fro teacher changed");

		} catch (Exception e) {
			System.out.println("Enter the correct syntaxed password");
			log.error("can not change password to teacher");
		}
	}

	void viewMyAttendanceReport(int studentId) {

		String sql = Commands.VIEW_MY_ATTENDANCE_REPORT;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ResultSet rs = ps.executeQuery();

			System.out.println("\nDate       | Subject | Status");
			System.out.println("--------------------------------");

			while (rs.next()) {
				System.out.println(
						rs.getDate("date") + " | " + rs.getString("subject_name") + " | " + rs.getString("status"));
			}
			log.info("");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	boolean isAttendanceMarked(int sid, LocalDate date) {

		String sql = Commands.isATTENdanceMARKED;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sid);
			ps.setDate(2, java.sql.Date.valueOf(date));

			ResultSet rs = ps.executeQuery();
			log.info("attendance mark checked");
			return rs.next();

		} catch (Exception e) {
			System.out.println("invalid id");
			log.error("can not get the attendance status for the student");
		}
		return false;
	}

	ArrayList<Student> getStudents(String className, String section) {

		ArrayList<Student> listS = new ArrayList<>();

		String sql = Commands.GET_STUDENT;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, className);
			ps.setString(2, section);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int sid = rs.getInt("sid");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");

				listS.add(new Student(sid, name, email, password, className, section));
			}
			log.info("students of a class added successfully in the array");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("can not get all student from the class");
		}
		return listS;
	}

	void insertAttendance(int sid, LocalDate date, String status) {

		String sql = Commands.INSERT_ATTENDANCE;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sid);
			ps.setDate(2, java.sql.Date.valueOf(date));
			ps.setString(3, status);

			ps.executeUpdate();
			log.info("attendance updated successfully ");

		} catch (Exception e) {
			System.out.println("invalid id");
			log.info("can not insert attendance status");

		}
	}

	public void markAttendance(LocalDate date) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Class ");
		String className = sc.nextLine();

		System.out.print("Enter Section ");
		String section = sc.nextLine();

		ArrayList<Student> students = getStudents(className, section);
		System.out.println("Students found: " + students.size());

		for (Student s : students) {

			if (isAttendanceMarked(s.Id, date)) {
				System.out.println("Attendance already marked for " + s.username);
				continue;
			}

			System.out.print("Mark attendance for " + s.username + " (P/A): ");
			String status = sc.nextLine().toUpperCase();

			insertAttendance(s.Id, date, status);
		}

		System.out.println("Attendance completed");
		log.info("attendance marked successfully for a student");
	}

	boolean isAttendanceMarkedForClass2(String className, String section, LocalDate date) {

		String sql = Commands.is_ATTENDANCE_MARKED_FOR_CLASS;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, className);
			ps.setString(2, section);
			ps.setDate(3, java.sql.Date.valueOf(date));

			ResultSet rs = ps.executeQuery();
			log.info("attendance for  the class checked");
			return rs.next();

		} catch (Exception e) {
			System.out.println("invalid credential");
			log.error("can not get the attendance status for the class");
		}
		return false;
	}

	public void searchAttendanceByDate2(String className, String section, LocalDate date) {

		String sql = Commands.SEARCH_ATTENDANCE_BY_DATE;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, className);
			ps.setString(2, section);
			ps.setDate(3, Date.valueOf(date));

			ResultSet rs = ps.executeQuery();

			System.out.println("\nAttendance Report : " + date);
			System.out.println("--------------------------------");

			boolean found = false;

			while (rs.next()) {
				found = true;
				System.out.println(rs.getInt("sid") + " | " + rs.getString("name") + " | " + rs.getString("status"));
			}

			if (!found) {
				System.out.println("No attendance found for this date");
			}
			log.info("attendance report by date is showed successfully");

		} catch (Exception e) {
			System.out.println("invalid credential");
			log.error("can not get attendance report by date");
		}
	}

	public void overAllReport2() {
		try {
			PreparedStatement ps = con.prepareStatement(Commands.over_ALL_REPORT);
			ResultSet rs = ps.executeQuery();
			System.out.println("fi");
			while (rs.next()) {
				System.out.println("hi");

				int present = rs.getInt("present");
				int totalDays = rs.getInt("totalWorkingDays");
				double percentage = 0;
				String classname = rs.getString("class_name");
				String sectionname = rs.getString("section_name");

				if (totalDays > 0) {
					percentage = (present * 100.0) / totalDays;
				}

				System.out.println("clasname" + classname + "section name" + sectionname + "percentage" + percentage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean changePassword(String oldPassword) {
		String sql = Commands.UPDATE_PASSWORD;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, oldPassword);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("password changed succesfully");
				log.info("password changed succesfully");
				return true;
			} else {
				System.out.println("can not change password");
			}

		} catch (Exception e) {
			System.out.println("invalid password");
			log.error("can not change the password");
		}
		return false;
	}

	public void viewMyAttendance(int sid, LocalDate date) {

		String sql = Commands.LAST;

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sid);
			ps.setDate(2, java.sql.Date.valueOf(date));

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				System.out.println("Date: " + rs.getDate("date") + " Status: " + rs.getString("status"));
			} else {
				System.out.println("Attendance not marked for this date");
			}
			log.info("get attendance of the student succesfully");

		} catch (Exception e) {
			System.out.println("invalid id");
			log.error("can not get the student attendance");
		}
	}

	public void viewStudentAttendance(String id) {
		int totalWorkingDays = 0;
		ResultSet rs;
		ResultSet rs1;
		PreparedStatement ps1;
		try {
			PreparedStatement ps = con.prepareStatement(Commands.DETAILS_OF_STUDENT);
			int ids = Integer.valueOf(id);
			ps.setInt(1, ids);
			rs = ps.executeQuery();
			ps1 = con.prepareStatement(Commands.TOTAL_WORKING_DAYS);

			rs1 = ps1.executeQuery();
			if (rs1.next()) {
				totalWorkingDays = rs1.getInt(1);
			}

			if (rs.next()) {
				System.out.println("Student ID : " + rs.getInt("sid"));
				System.out.println("Name       : " + rs.getString("name"));
				System.out.println("Class      : " + rs.getString("class_name"));
				System.out.println("Section      : " + rs.getString("section_name"));

				System.out.println("Present    : " + rs.getInt("attendancecount"));
				System.out.println("Absent     : " + rs.getInt("leavecount"));
				System.out.println(
						"Attendance Percentage :" + rs.getInt("attendancecount") / totalWorkingDays * 100 + "%");

				System.out.println("----------------------------------");
			}

			log.info("get student attendance details successfully");

		} catch (Exception e) {
			System.out.println("invalid id");
			log.info("can not get student attendance details ");

		}
	}

	public String readRoleforts(String id, String pass) {
		try {

			PreparedStatement ps = con.prepareStatement(Commands.Role_OF_ST);

			ps.setInt(1, Integer.valueOf(id));
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log.info("role of the user(teacher) getted");

				return rs.getString(1);
			} else {
				log.info("Invlaid Id or password  given to teacher role selection");

				return null;
			}

		}

		catch (Exception e) {
			System.out.println("Invalid ID or password.Try  again");
			log.info("can not get the role of the user(teacher)");

		}
		return null;
	}

	public String readRoleforsts(String id, String pass) {
		try {
			PreparedStatement ps = con.prepareStatement(Commands.Role_OF_sts);
			ps.setInt(1, Integer.valueOf(id));
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				log.info("role of the user(student) getted");
				return rs.getString("role");
			} else {
				log.info("invalid id or password for the user(student) ");

				return null;
			}

		}

		catch (Exception e) {
			System.out.println("Invalid ID or password.Try again");
			log.error(" can not get the role of the user(student) ", e);

		}
		return null;
	}

	void addLoginCountDB(String id) {
		String qry = Commands.ADD_COUNT_TO_TEACHER;
		try {
			int intId = Integer.valueOf(id);
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setInt(1, intId);
			ps.executeUpdate();
			log.info("login count to teacher added tecaher id:" + id);

		} catch (Exception e) {
			System.out.println("Invalid itd");
			log.error("can not set login count to teacher. tecaher id:" + id);

		}

	}

	void addLoginCountToStudent2() {
		String qry = Commands.ADD_LOGIN_COUNT_STUDENT;
		try {
			PreparedStatement ps = con.prepareStatement(qry);
			ResultSet rs = ps.executeQuery();
			log.info("login count to student added");

		} catch (Exception e) {
			System.out.println("Invalid iyd");
			log.error("can not set login count to student");
		}

	}

	int teacherLoginCountDB(String id) {
		String qry = Commands.T_Count;
		try {
			PreparedStatement ps = con.prepareStatement(qry);
			int ids = Integer.valueOf(id);
			ps.setInt(1, ids);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			log.info("check teacher first login" + id);

		} catch (Exception e) {
			System.out.println("invalid id");
			log.error("can not get teacher login count from table" + id);

		}
		return 0;

	}

	int studentLoginCount2(String id) {
		try {
			PreparedStatement ps = con.prepareStatement(Commands.S_COUNT);
			int ids = Integer.valueOf(id);
			ps.setInt(1, ids);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			log.info("check student first login student id:" + id);
		} catch (Exception e) {
			System.out.println("invalid id");
			log.error("can not get student login count from table student id:" + id);
		}
		return 0;

	}
}
