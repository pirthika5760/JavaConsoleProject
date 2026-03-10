package FinalConsoleProject;

public class Validator {
	
 String usernameRegex="^[A-Za-z ]+$";
 String stdRegex="^([1-9]|1[0-2])$";
 String secRegex="^[A-F]$";
 String subRegex="^[A-Za-z]{3,30}$";
public boolean isValidName(String name) {
	if(name==null || name.trim().isEmpty()) {
		return false;
	}
	return name.matches(usernameRegex);
}
public boolean isValidClass(String std) {
	if(std==null || std.trim().isEmpty()) {
		return false;
	}
	return std.matches(stdRegex);
}
public boolean isValidSection(String sec) {
	if(sec==null || sec.trim().isEmpty()) {
		return false;
	}
	return sec.matches(secRegex);
}
public boolean isValidSubject(String sub) {
	if(sub==null || sub.trim().isEmpty()) {
		return false;
	}
	return sub.matches(subRegex);
}
public boolean isValidMobile(String mobile) {
    return mobile != null && mobile.matches("^\\d{10}$");
}

}
