package FinalConsoleProject;

import java.time.LocalDate;
import java.util.ArrayList;

class Attendancemanager {
	ArrayList<Teacher> teachers = new ArrayList<>();
	ArrayList<Student> students = new ArrayList<>();
	String isMarked;
	ArrayList<Attendancetrack> attendents = new ArrayList<>();
	DBQuerriesExe db;

	// Constructor accepting shared DBQuerriesExe - no more creating its own
	Attendancemanager(DBQuerriesExe db) {
		this.db = db;
	}

	// Now accepts class & section from caller - NO double input
	public void markAttendance(String className, String section, LocalDate date) {
		db.markAttendance(className, section, date);
	}

	boolean isAttendanceMarkedForClass(String className, String section, LocalDate date) {
		return db.isAttendanceMarkedForClass2(className, section, date);
	}

	public void searchAttendanceByDate(String className, String section, LocalDate date) {
		db.searchAttendanceByDate2(className, section, date);
	}

	public void overAllReport() {
		db.overAllReport2();
	}

	void insertConfig(int days, int holidays) {
		db.insertConfig2(days, holidays);
	}

	void listHolidaysCall() {
		db.listHolidays();
	}

	void addHoliday(String date, String name) {
		db.addHoliday2(date, name);
	}

	void removeHoliday(String date) {
		db.removeHolidayDB(date);
	}
}
