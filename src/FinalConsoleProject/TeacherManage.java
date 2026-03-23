package FinalConsoleProject;

public class TeacherManage {
	AttendanceTrackerApp logger;
	DBQuerriesExe db;

	// Constructor accepting shared DBQuerriesExe
	TeacherManage(DBQuerriesExe db) {
		this.db = db;
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

	// Fixed: now requires oldPassword, newPassword, and teacherId
	public boolean changePassword(String oldPassword, String newPassword, int teacherId) {
		return db.changePassword(oldPassword, newPassword, teacherId);
	}

	// NEW: Email-based methods
	public int getTeacherIdByEmail(String email) {
		return db.getTeacherIdByEmail(email);
	}

	public int teacherLoginCountByEmail(String email) {
		return db.teacherLoginCountByEmail(email);
	}

	public void addTeacherLoginCountByEmail(String email) {
		db.addTeacherLoginCountByEmail(email);
	}

	public boolean changePasswordByEmail(String email, String oldPassword, String newPassword) {
		return db.changePasswordVerified(email, oldPassword, newPassword);
	}

}
