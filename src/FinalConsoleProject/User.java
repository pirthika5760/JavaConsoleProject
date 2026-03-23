package FinalConsoleProject;

import java.util.Scanner;

abstract public class User {
	String userEmail;
	String password;
	String role;
	AttendanceTrackerApp logger;
	DBQuerriesExe db;

	User(String userEmail, String password) {
		this.userEmail = userEmail;
		this.password = password;
	}

	User(String userEmail, String password, DBQuerriesExe db) {
		this.userEmail = userEmail;
		this.password = password;
		this.db = db;
	}



	abstract public void showMenu();
	

	public void adminTeacherMenu() {
	}

	public boolean teacherdetailsCollector() {
		return false;
	}

	public boolean detailsCollector() {
		return false;
	}

	public void adminStudentMenu() {

	}

	public boolean studentDetailsCollector() {
		return false;

	}

	public void viewStudentAttendance(String id) {
	}

	public void changePassword(String userEmail2,Scanner sc) {
		db.changePassword2(userEmail2,sc);
	}

}
