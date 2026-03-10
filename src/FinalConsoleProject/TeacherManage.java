package FinalConsoleProject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class TeacherManage {
	AttendanceTrackerApp logger;
	Commands com;
	DBQuerriesExe db = new DBQuerriesExe();

	public void getConnectionFromDb(Commands c) {
		this.com = c;
	}

	boolean isTeacherExists(int teacherId) {

		return db.teacherExists(teacherId);

	}

	public void readTeachers() {
		db.readTeachers2();
	}

	void modifyTeacherMenu(int teacherId) {
		db.modifyTeacherMenu2(teacherId);

	}

	public void removeTeacher(int id) {
		db.deleteTeacher(id);
	}

	void viewMyClassDetails(int teacherId) {
		db.viewMyClassDetails2(teacherId);
	}

	void addLoginCount(String id) {
		db.addLoginCountDB(id);

	}

	int teacherLoginCount(String id) {
		return db.teacherLoginCountDB(id);

	}

	public void TeacherViewAllStudents(int id) {
		db.viewMyStudents(id);
	}

	public String readRoleforTS(String id, String pass) {
		return db.readRoleforts(id, pass);
	}

	public boolean changePassword(String oldPassword){
            return db.changePassword(oldPassword);
	}

}
