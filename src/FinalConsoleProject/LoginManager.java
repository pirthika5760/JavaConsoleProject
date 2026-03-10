package FinalConsoleProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LoginManager {
	AttendanceTrackerApp logger;
	DBQuerriesExe db = new DBQuerriesExe();

        @SuppressWarnings("static-access")
	public User login(String userEmail, String password, String role) {
		User user = null;

		if (role.equals("admin")) {
			user = new Admin(userEmail, password);
			logger.log.info("Admin logined sucessfully");
//			user.showMenu();
			return user;
		}

		else if (role.equals("teacher")) {
			user = new Teacher(userEmail, password, role, role, role, role);
			logger.log.info("Teacher logined sucessfully");

			
			return user;
		} else if (role.equals("student")) {
			logger.log.info("Student logined sucessfully");

			user = new Student(0, userEmail, password, role, role, role);
			
			return user;

		} else {
			System.out.println("Invalidy email or password");
			logger.log.error("User enter the wrong email or password");
		}

		return user;

	}

//	public User loginforst(String userEmail, String password, String role) {
//
//	}

	public User loginforstM(String userEmail, String password, String role) {
		User user;
		if (role.equals("teacher")) {
			user = new Teacher(userEmail, password, role, role, role, role);
			return user;
		} else {

			user = new Student(0, userEmail, password, role, role, role);
			return user;

		}
	}

	public String getRole(String userEmail) {
		return db.readRole(userEmail);
	}
}
