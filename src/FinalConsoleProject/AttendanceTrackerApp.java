package FinalConsoleProject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AttendanceTrackerApp {
	static Logger log = LogManager.getLogger();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Attendancemanager am = new Attendancemanager();

		DBQuerriesExe d = new DBQuerriesExe();

		LoginManager loginManager = new LoginManager();

		TeacherManage tm = new TeacherManage();

		StudentManage sm = new StudentManage();

		Main: while (true) {

			System.out.println("=================================");
			System.out.println("     ATTENDANCE MANAGEMENT        ");
			System.out.println("=================================");
			System.out.println(" 1. Admin Login");
			System.out.println(" 2. Teacher/Student Login");
			System.out.println(" 3. Exit");
			System.out.println("=================================");
			int ch;
			while (true) {
				try {
					System.out.println(" Enter your choice ");

					log.info("Attendance Management menu Dispalyed");
					ch = sc.nextInt();
					sc.nextLine();
					break;
				} catch (Exception e) {
					sc.nextLine();
					System.out.println("Invalid choice");
					log.error("User give the invalid choice");
				}
			}

			switch (ch) {
				case 1: {
					User u;
					User user = null;
					String role = null;

					Admin: while (true) {
						log.info(" User selected admin login user id:1");
						String password;
						String userEmail;
						while (true) {
							System.out.println("Enter Your email: ");
							userEmail = sc.nextLine();
							System.out.println("Enter your password: ");
							password = sc.nextLine();

							role = loginManager.getRole(userEmail);

							try {
								user = loginManager.login(userEmail, password, role);
								break;
							} catch (Exception e) {
								System.out.println("Invalid email or password");
								log.error("Admin entered the invalid email or password", e);
							}
						}
						while (true) {
							user.showMenu();
							if (d.checkUserExist(userEmail, password)) {

								log.info("Admin menu showed");
								int choice;
								while (true) {
									try {
										choice = sc.nextInt();
										sc.nextLine();
										log.info("Admin select the correct choice");
										break;
									} catch (Exception e) {
										sc.nextLine();
										System.out.println(" Invalid choice");
										System.out.println(" Enter your choice");
										log.error("Admin entered the invalid choice for Admin-main menu", e);
									}
								}

								switch (choice) {

									case 1:
										AdminTeacher: while (true) {
											int adminchoice;
											user.adminTeacherMenu();
											log.info("Admin-Teacher menu showed");
											while (true) {
												try {
													adminchoice = sc.nextInt();
													sc.nextLine();
													log.info("Admin select the correct choice in admin-teacher menu");
													break;
												} catch (Exception e) {
													sc.nextLine();
													System.out.println(" Invalid choice");
													System.out.println(" Enter your choice");
													log.error("Admin entered the invalid choice for Admin-Teacher menu",
															e);

												}
											}

											switch (adminchoice) {
												case 1: {

													user.teacherdetailsCollector();

													break;
												}
												case 2:
													tm.readTeachers();
													break;
												case 3:
													while (true) {
														System.out.println("Enter the teacher id");
														int modifyTeacherId = sc.nextInt();
														sc.nextLine();

														if (tm.isTeacherExists(modifyTeacherId)) {

															tm.modifyTeacherMenu(modifyTeacherId);
															break;
														} else {
															System.out.println("Teacher not exist. ");
															log.info("Admin entered a non existing id to modify teacher"
																	+ "Teacher id" + modifyTeacherId);
														}

													}
													break;
												case 4:
													while (true) {
														System.out.println("Enter the id of the teacher");
														int removeTeacherID = sc.nextInt();
														if (tm.isTeacherExists(removeTeacherID)) {
															tm.removeTeacher(removeTeacherID);

															log.info("Teacher removed by admin. Teacher id"
																	+ removeTeacherID);
															break;
														} else {
															System.out.println("Teacher not exist");
															log.info("admin entered the wrong id");
														}
													}
													break;

												case 5:
													log.info("Admin loged out");

													break AdminTeacher;
													
												default:
													System.out.println("Enter the valid choice");
													log.info("admin give the invalid choice in admin-teacher menu");
											}

										}

										break;

									case 2:
										AdminStudent: while (true) {
											user.adminStudentMenu();
											log.info("Admin-student menu showed");
											int adminStudentChoice;
											while (true) {
												try {
													adminStudentChoice = sc.nextInt();
													sc.nextLine();
													log.info("Admin entered the choice for admin-student menu");
													break;
												} catch (Exception e) {
													sc.nextLine();
													System.out.println(" Invalid choice");
													log.error("admin entered the invalid choice in admin-student menu",
															e);
													System.out.println(" Enter your choice");
												}
											}

											switch (adminStudentChoice) {
												case 1:
													user.studentDetailsCollector();
													// log.info("Student added successfully by admin");
													break;

												case 2:
													sm.showStudents();
													// log.info("Student details showed");
													break;
												case 3:
													while (true) {
														System.out.println("Enter the student id");
														int modifyStudentId = sc.nextInt();
														if (sm.isStudentExists(modifyStudentId)) {
															sc.nextLine();
															sm.modifyStudent(modifyStudentId);
															log.info("Student modified by admin");
															break;
														} else {
															System.out.println("Student not exist");
															log.info("student can not modified by admin");
														}

													}
													break;

												case 4:
													while (true) {
														System.out.println("Enter the id of the student");
														int removeStudentID = sc.nextInt();
														if (sm.isStudentExists(removeStudentID)) {
															sc.nextLine();
															sm.removeStudent(removeStudentID);
															log.info("Student removed by admin");
															break;
														} else {
															System.out.println("Student not exist");
															log.info("Student did not removed by admin");
														}

													}
													break;

												case 5:
													log.info("admin exit from admin-student menu");
													break AdminStudent;
												default:
													System.out.println("Enter the valid choice");
													break;

											}

										}
										break;
									case 3:
										Config: while (true) {
											System.out.println("=================================");
											System.out.println("        CONFIGURATION MENU       ");
											System.out.println("=================================");
											System.out.println(" 1. Set Total Working Days");
											System.out.println(" 2. Holidays");
											System.out.println(" 3. Back");
											System.out.println("=================================");
											while (true) {
												try {
													System.out.println(" Enter your choice : ");

													choice = sc.nextInt();
													sc.nextLine();

													break;
												} catch (Exception e) {
													sc.nextLine();
													System.out.println(" Invalid choice");
													System.out.println(" Enter your choice");
												}
											}
											log.info("Config menu showed successfully to admin");
											switch (choice) {
												case 1: {
													log.info("Admin select set working days in config menu");
													while (true) {
														System.out.println("Enter the total working days");
														int totaldays = sc.nextInt();
														sc.nextLine();
														if (totaldays > 300) {
															log.error("Admin entered the invalid total working days");
															break;
														} else {
															while (true) {
																System.out.println("Enter the total Holidays");
																int totalHolidays = sc.nextInt();
																sc.nextLine();
																if (totalHolidays > 30) {
																	log.error("Admin enterd the invalid holidays");
																	break;
																} else {
																	am.insertConfig(totaldays, totalHolidays);

																}
																break;
															}

														}
														break;

													}
													// break;
												}
													break;

												case 2:
													Inner: while (true) {
														log.info("Admin choose holiday in config menu");
														System.out.println("1.Add Holiday");
														System.out.println("2.Remove holiday");
														System.out.println("3.Back");
														log.info("Holiday menu showed to admin");
														while (true) {

															try {
																choice = sc.nextInt();
																sc.nextLine();
																log.info(
																		"Admin choosed the correct choice in holiday menu");
																break;
															} catch (Exception e) {
																sc.nextLine();
																log.error(
																		"Admin entered the invalid choice in holiday menu",
																		e);
																System.out.println(" Invalid choice");
																System.out.println(" Enter your choice");
															}
														}

														am.listHolidaysCall();

														switch (choice) {
															case 1: {

																System.out.println(
																		"Enter the date to add ex(yyyy-mm-dd)");
																String date = sc.nextLine();
																log.info("Admin entered the holiday day to add");
																System.out.println("Enter the name of the holiday");
																String name = sc.nextLine();
																log.info("Admin entered the name of te holiday to add");
																am.addHoliday(date, name);
																break;
															}
															case 2:
																System.out.println("Enter the date to remove");
																String date = sc.nextLine();
																log.info("Admin entered the holiday date to remove");
																am.removeHoliday(date);

																break;
															case 3:
																break Inner;
															default:
																System.out.println("Enter the valid choice");
																log.error(
																		"Admin entered the invalid choice in holiday menu");
														}
													}
												case 3:
													break Config;

												default:
													System.out.println("Enter the valid choice");
													log.error("Admin entered the invalid choice in config menu");

											}
										}
										break;
									case 4:
										am.overAllReport();
										log.info("Over all report showed to admin");
										break;
									case 5:
										break Admin;
									default:
										System.out.println("Enter the valid choice");
										log.error("Admin entered the invalid choice in admin-main menu");
										break;
								}
							}

						}
					}
					break;
				}

				case 2: {
					String userEmail;
					String role;
					log.info("user selected teacher /student login");

					while (true) {

						System.out.print("Enter Your Id");
						userEmail = sc.nextLine();

						System.out.println("Enter your  password");
						String password = sc.nextLine();

						role = tm.readRoleforTS(userEmail, password);
						if (role == null) {
							role = sm.readRoleforSt(userEmail, password);
						}
						if (role == null) {
							System.out.println("Invalid id or Password");

							continue;
						}

						User user = loginManager.loginforstM(userEmail, password, role);

						if (role.equalsIgnoreCase("teacher")) {

							log.info("user selected teacher login");

							if (tm.teacherLoginCount(userEmail) != 1) {
								user.changePassword(userEmail, sc);
								tm.addLoginCount(userEmail);

							}

							Teacher: while (true) {

								user = loginManager.login(userEmail, password, role);
								user.showMenu();
								int Teacherchoice;

								while (true) {
									try {
										Teacherchoice = sc.nextInt();
										sc.nextLine();
										log.info("Teacher choose the correct choice in teacher menu"
												+ d.readId(userEmail));
										break;
									} catch (Exception e) {
										sc.nextLine();
										System.out.println(" Invalid choice");
										System.out.println(" Enter your choice");
										log.error("Teacher choose the correct invalid choicein teacher menu menu"
												+ d.readId(userEmail), e);

									}
								}

								switch (Teacherchoice) {

									case 1:
										tm.viewMyClassDetails(Integer.valueOf(userEmail));
										break;

									case 2:
										tm.TeacherViewAllStudents(Integer.valueOf(userEmail));
										break;

									case 3: {
										System.out.println("Enter the class:");
										String std = sc.nextLine();

										System.out.println("Enter the section:");
										String sec = sc.nextLine();

										LocalDate today = LocalDate.now();

										if (am.isAttendanceMarkedForClass(std, sec, today)) {
											System.out.println("Attendance already marked for today!");
											break;
										}

										am.markAttendance(today);
										break;
									}

									case 4:
										System.out.println("Enter Class: ");
										String cls = sc.nextLine();

										System.out.println("Enter Section: ");
										String sec = sc.nextLine();

										System.out.println("Enter Date (yyyy-mm-dd): ");
										String dt = sc.nextLine();
										LocalDate date = LocalDate.parse(dt);

										am.searchAttendanceByDate(cls, sec, date);
										break;

									case 5:
									    System.out.println("Enter your old password:");
										String oldPassword=sc.nextLine();

										tm.changePassword(oldPassword);
										break Teacher;
								}
							}
							break;
						}

						else {

							if (sm.studentLoginCount(userEmail) != 1) {
								user.changePassword(userEmail, sc);
								sm.addLoginCountToStudent();
							}

							Student: while (true) {

								user = loginManager.login(userEmail, password, role);
								user.showMenu();
								int Studentchoice;

								while (true) {
									try {
										Studentchoice = sc.nextInt();
										sc.nextLine();
										break;
									} catch (Exception e) {
										sc.nextLine();
										System.out.println(" Invalid choice");
										System.out.println(" Enter your choice");
									}
								}

								switch (Studentchoice) {
									case 1:
										sm.displayStudentDetails(userEmail);
										break;
									case 2:
										break Student;
									default:
										break;
								}
							}
							break;
						}
					}
					break;
				}
				case 3:
					break Main;
			}

		}

	}
}
