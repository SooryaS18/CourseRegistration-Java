import java.io.Serializable;
//abstract class to form the base for User and Admin classes/objects

abstract class User implements Serializable {

//general attributes
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String username;
	private String password;


	User(String userName, String password, String firstName, String lastName) {
		this.username = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = lastName + firstName;
		this.password = firstName+firstName;
	}

	public User() {
	}

//getter and setter methods to access private variables (encapsulation)
	
	public String getusername() {
		return username;
	}
	
	public void setusername(String userName) {
		this.username = userName;
	}
	
	public String getpassword() {
		return password;
	}
	
	public void setpassword(String password) {
		this.password = password;
	}
	
	public String getfirstName() {
		return firstName;
	}
	
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getlastName() {
		return lastName;
	}
	
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	
//iterates through all courses and prints out the name
	public void getCourses() {
			for (Course C : ReadCourse.courses){
				System.out.println(C.getcourseName());
			}
	}
	
//iterates through all courses and matches coursename to return a course object (seems useful, don't know if I will implement later)
	public Course searchbyname(String CourseName) {
			for (Course C : ReadCourse.courses){
				if (C.getcourseName().equals(CourseName)){
					return C;
				}
		}
			System.out.println("Course Not Found");
			return null;

	}

//iterates through all courses and matches CourseId to return a course object (seems useful, don't know if I will implement later)
	public Course searchbyID(String ID) {
		for (Course C : ReadCourse.courses){
			if (C.getcourseId().equals(ID)){
				return C;
			}
	}
		System.out.println("Course Not Found");
		return null;

	}

//shell methods that are overridden in admin and student methods, respectively
	public void viewFullCourses() {
	}
	
	public void viewOpenCourses() {
	}
}