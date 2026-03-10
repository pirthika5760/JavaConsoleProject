package FinalConsoleProject;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Attendancemanager {
	ArrayList<Teacher> teachers = new ArrayList<>();
	ArrayList<Student> students = new ArrayList<>();
	String isMarked;
	ArrayList<Attendancetrack> attendents = new ArrayList<>();
	Admin a;
	Scanner sc = new Scanner(System.in);
	Commands c = new Commands();
	DBQuerriesExe db = new DBQuerriesExe();
	AttendanceTrackerApp logger;
	User u;
	Student s;
	Teacher t;
	Commands com;
	TeacherManage tm = new TeacherManage();

	public void getConnectionFromDb(Commands c) {
		this.com = c;

	}

	public void markAttendance(LocalDate date) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Class  ");
		String className = sc.nextLine();

		System.out.println("Enter Section ");
		String section = sc.nextLine();

		ArrayList<Student> students = db.getStudents(className, section);
		if (students.size() > 0) {
			for (Student s : students) {

				if (db.isAttendanceMarked(s.Id, date)) {
					System.out.println("attendance already marked for " + s.username);
					continue;
				}

				System.out.println("Mark attendance for " + s.username + " (P/A): ");
				String status = sc.nextLine().toUpperCase();

				db.insertAttendance( db.readId(s.userEmail),date, status);
				System.out.println("Attendance completed");
				logger.log.info("attendance marked successfully for a student");


			}

		} else {
			System.out.println("no students found");
			logger.log.info("no student list showed to mark attendance");
		}
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

	void PasswordChange(Teacher t) {
		db.changePassword2(t.userEmail,sc);
	}

}
