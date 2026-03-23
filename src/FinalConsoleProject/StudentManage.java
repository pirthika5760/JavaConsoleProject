package FinalConsoleProject;

public class StudentManage {
	DBQuerriesExe db;

	// Constructor accepting shared DBQuerriesExe
	StudentManage(DBQuerriesExe db) {
		this.db = db;
	}

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

	// Fixed: now requires student id parameter
	void addLoginCountToStudent(String id) {
		db.addLoginCountToStudent2(id);
	}

	int studentLoginCount(String id) {
		return db.studentLoginCount2(id);
	}

	public void displayStudentDetails(String userEmail) {
		db.viewStudentAttendance(userEmail);
	}

	public int getStudentIdByEmail(String email) {
		return db.getStudentIdByEmail(email);
	}

	public int studentLoginCountByEmail(String email) {
		return db.studentLoginCountByEmail(email);
	}

	public void addStudentLoginCountByEmail(String email) {
		db.addStudentLoginCountByEmail(email);
	}

	public void displayStudentDetailsByEmail(String email) {
		int sid = db.getStudentIdByEmail(email);
		if (sid != -1) {
			db.viewStudentAttendance(String.valueOf(sid));
		} else {
			System.out.println("Student not found");
		}
	}

}
