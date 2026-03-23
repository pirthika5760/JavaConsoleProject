package FinalConsoleProject;

public class LoginManager {
	AttendanceTrackerApp log;
	DBQuerriesExe db;

	LoginManager(DBQuerriesExe db) {
		this.db = db;
	}

	@SuppressWarnings("static-access")
	public User login(String userEmail, String password, String role) {
		User user = null;

		if (role.equals("admin")) {
			user = new Admin(userEmail, password);
			log.log.info("Admin logined sucessfully");
			// user.showMenu();
			return user;
		}

		else if (role.equals("teacher")) {
			user = new Teacher(userEmail, password, role, role, role, role);
			log.log.info("Teacher logined sucessfully");

			return user;
		} else if (role.equals("student")) {
			log.log.info("Student logined sucessfully");

			user = new Student(0, userEmail, password, role, role, role);

			return user;

		} else {
			System.out.println("Invalidy email or password");
			log.log.error("User enter the wrong email or password");
		}

		return user;

	}

	// public User loginforst(String userEmail, String password, String role) {
	//
	// }

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
