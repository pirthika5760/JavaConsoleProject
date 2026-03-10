package FinalConsoleProject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentManage {
	Attendancemanager am = new Attendancemanager();
	Commands com;
	DBQuerriesExe db = new DBQuerriesExe();

	public void getConnectionFromDb(Commands c) {
		this.com = c;
	}

	AttendanceTrackerApp logger;

	void showStudents() {
		db.viewStudents();

	}
	public String readRoleforSt(String id, String pass) {
		return db.readRoleforsts(id, pass);
	}
	public void modifyStudent(int id) {

		db.modifyStudentMenu(id);

	}

	public void removeStudent(int id) {
		db.deleteStudent(id);

	}

	boolean isStudentExists(int sId) {

		return db.studentExists(sId);
	}

	void addLoginCountToStudent() {
		db.addLoginCountToStudent2();
	}

	int studentLoginCount(String id) {
		return db.studentLoginCount2(id);

	}

	public void displayStudentDetails(String userEmail) {
		db.viewStudentAttendance(userEmail);

	}

}
