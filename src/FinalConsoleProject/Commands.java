package FinalConsoleProject;

public class Commands {

	final static String WRITE_U = "insert into users(email,password,role) values (?,AES_ENCRYPT(?,'key'),?)";
	final static String WRITE_T = "insert into teacher (name,userid,class_id,section_id,subject_id) values (?,?,?,?,?)";
	final static String READ_U = "select userid from users where email=?";
	final static String READ_Role = "select role from users where email=?";
	final static String READ_Details = "select t.tid as tid, t.name as tname, u.email as temail, c.class_name as tclass, s.section_name as tsection, sub.subject_name as tsubject from teacher t join users u on t.userid = u.userid join class c on t.class_id = c.class_id join section s on t.section_id = s.section_id join subject sub on t.subject_id = sub.subject_id";
	final static String Student_detials = "select a.date,  a.status,    sub.subject_name	from attendance a join teacher t   on a.tid = t.tid join subject sub on t.subject_id = sub.subject_id where a.sid = ? order by a.date; ";

	final static String INSER_TEACHER = "insert into teacher (name, userid, class_id, section_id, subject_id) values (?, ?, ?, ?, ?)";
	final static String CLASS_ID = "select class_id from class where class_name = ?";
	final static String SECTION_ID = "select section_id from section where section_name = ?";
	final static String SUBJECT_ID = "select subject_id  from subject where subject_name=?";
	final static String UPDATE_TEACHER_BY_ID = "update teacher set name = ?, class_id = ?, section_id = ?, subject_id = ? where tid = ?";
	final static String UPDATE_TECAHER_NAME = "update teacher set name=? where tid=?";

	final static String UPDATE_TECHER_CLASS = "update teacher set class_id=? where tid=?";
	final static String UPDATETEACHERSECTION = "update teacher set section_id=? where tid=?";
	final static String UPDATE_TEACHER_SUBJECT = "update teacher set subject_id=? where tid=?";

	final static String REMOVE_TEACHER = "select userid from teacher where tid=?";

	final static String TECHER_EXIST = "select tid from teacher where tid=?";

	final static String STUDENT_EXIST = "select sid from student where sid=?";

	final static String ADD_STUDENT = "insert into student (name, userid, class_id, section_id) values (?, ?, ?, ?)";

	final static String VIEW_STUDENT = "select s.sid, s.name, u.email, c.class_name, sec.section_name from student s join users u on s.userid = u.userid join class c on s.class_id = c.class_id join section sec on s.section_id = sec.section_id";
	final static String UPDATE_STUDENT_NAME = "update student set name=? where sid=?";

	final static String UPDATE_STUDENT_CLASS = "update student set class_id=? where sid=?";
	final static String UPDATE_STUDENT_SECTION = "update student set section_id=? where sid=?";

	final static String REMOVE_STUDENT = "select userid from student where sid=?";

	final static String DELETE_STUDENT_IN_STUDENT_TABLE = "delete from student where sid=?";
	final static String DELETE_STUDENT_IN_USER_TABLE = "delete from users where userid=?";

	final static String GET_TEACHER_ID = "select tid from teacher where tid = ?";

	final static String VIEW_MY_CLASS_DETAILS_2 = "select t.tid ,t.name, c.class_name, s.section_name, sub.subject_name from teacher t join class c on t.class_id = c.class_id  join section s on t.section_id = s.section_id  join subject sub on t.subject_id = sub.subject_id  where t.tid = ?";
	final static String CHECK_USER_LIST = "select userid from users u  where u.email=? and u.password=AES_ENCRYPT(?,'key')";
	final static String CHECK_USER_ROLE = "select role from users u  where u.email=? and u.password=AES_ENCRYPT(?,'key')";

	final static String VIEW_MY_STUDENT = "select st.sid, st.name from student st join teacher t on st.class_id = t.class_id and st.section_id = t.section_id where t.tid = ?";

	final static String VIEW_ATTENDANCE_REPRT = "select st.name, a.date, a.status   from attendance a    join student st on a.sid = st.sid  where a.tid = ?";

	final static String CHANGE_PASSWORD = "update users set password =AES_encrypt(?,'key') where userid = (select userid from teacher where tid = ?)";

	final static String VIEW_MY_ATTENDANCE_REPORT = "select a.date, a.status,sub.subject_name from attendance a join teacher t on a.tid = t.tid JOIN subject sub ON t.subject_id = sub.subject_id WHERE a.sid = ?ORDER BY a.date";

	final static String isATTENdanceMARKED = "select 1 from attendance where sid = ? and date = ?";

	final static String GET_STUDENT = " select s.sid, s.name, u.email, u.password from student s join users u on s.userid = u.userid join class c on s.class_id = c.class_id join section sec on  s.section_id = sec.section_id where c.class_name = ? and sec.section_name = ?";

	final static String INSERT_ATTENDANCE = "insert into attendance (sid, date, status,ismarked) values (?, ?, ?,'done')";

	final static String is_ATTENDANCE_MARKED_FOR_CLASS = "select 1 from attendance a join student s on a.sid = s.sid join class c on s.class_id = c.class_id join section sec on s.section_id = sec.section_id	where  c.class_name = ?	and sec.section_name = ? and a.date = ?	limit 1";
	final static String SEARCH_ATTENDANCE_BY_DATE = " select s.sid, s.name, a.status from attendance a  join student s on a.sid = s.sid  join  class c on s.class_id = c.class_id  join section sec on s.section_id = sec.section_id  where c.class_name = ?  and sec.section_name = ?  and  a.date = ?";
	final static String LAST = "select date, status  from attendance  where sid = ? and date = ? ";

	final static String CONFIG = "update config set totalWorkingDays =?,totalHolidays=? where id=1";
	final static String HOLIDAY = "select h_date from holiday";
	final static String remove_HOLIDAY = "delete from holiday where h_date=?";
	final static String ADD_HOLIDAY = "insert into holiday (h_date,h_name) values (?,?)";
	final static String DETAILS_OF_STUDENT = "select s.sid ,s.name ,c.class_name,st.section_name,sum(a.status='P') as attendancecount , sum(a.status='A') as leavecount from student s join class c on s.class_id=c.class_id join section st on st.section_id=s.section_id left join attendance a on a.sid=s.sid where s.sid=? group by s.sid ,s.name ,c.class_name,st.section_name ";

	final static String Role_OF_ST = "select u.role from users u join teacher t on u.userid=t.userid  where t.tid=? and u.password=AES_ENCRYPT(?,'key')";
	final static String Role_OF_sts = "select u.role from users u join student s on u.userid=s.userid  where s.sid=? and u.password=AES_ENCRYPT(?,'key')";
	final static String over_ALL_REPORT = "select c.class_name, sec.section_name, sum(a.status='P') as present,sum(a.status='A') as absent ,con.totalWorkingDays from student s join class c on s.class_id=c.class_id join section sec on s.section_id=sec.section_id join attendance a on s.sid=a.sid join config con on con.id=1 group by c.class_name,sec.section_name,con.totalWorkingDays";

	final static String S_COUNT = "select count from student where sid=?";
	final static String T_Count = "select count from teacher where tid=?";

	final static String ADD_LOGIN_COUNT_STUDENT = "update student set count=1 where sid=?";
	final static String ADD_COUNT_TO_TEACHER = "update teacher set count=1 where tid=?";

	final static String TOTAL_WORKING_DAYS = "select totalWorkingDays from config where id=1";

	// Unified login: get role by email + password
	final static String LOGIN_BY_EMAIL = "select userid, role from users where email=? and password=AES_ENCRYPT(?,'key')";

	// Get teacher tid from user email
	final static String GET_TID_BY_EMAIL = "select t.tid from teacher t join users u on t.userid=u.userid where u.email=?";

	// Get student sid from user email
	final static String GET_SID_BY_EMAIL = "select s.sid from student s join users u on s.userid=u.userid where u.email=?";

	// Teacher login count by email
	final static String T_COUNT_BY_EMAIL = "select t.count from teacher t join users u on t.userid=u.userid where u.email=?";

	// Student login count by email
	final static String S_COUNT_BY_EMAIL = "select s.count from student s join users u on s.userid=u.userid where u.email=?";

	// Add login count by email
	final static String ADD_COUNT_TEACHER_BY_EMAIL = "update teacher t join users u on t.userid=u.userid set t.count=1 where u.email=?";
	final static String ADD_COUNT_STUDENT_BY_EMAIL = "update student s join users u on s.userid=u.userid set s.count=1 where u.email=?";

	// Change password by email (for first-time login)
	final static String CHANGE_PASSWORD_BY_EMAIL = "update users set password=AES_ENCRYPT(?,'key') where email=?";

	// Change password with old password verification (for teacher menu)
	final static String CHANGE_PASSWORD_VERIFIED = "update users set password=AES_ENCRYPT(?,'key') where email=? and password=AES_ENCRYPT(?,'key')";
}   
