import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User implements AdminInterface {
	private static final long serialVersionUID = 1L; 
	Scanner input = new Scanner(System.in);
	private String username; 
	private String password; 
//Admin constructor, only takes username + password
	public Admin() {
		super();
		this.setUsername("Admin");
		this.setPassword("Admin001");
	}
//add course method, accept inputs and creates new course object at the end and adds its to main course list
public Course addCourse(){
	System.out.println("Course Name: ");
	String courseName = String.valueOf(input.nextLine());
	
	System.out.println("Course ID: ");
	String courseID = String.valueOf(input.nextLine());
	
	System.out.println("Maximum Number of Students: ");
	int maxStudents = Integer.valueOf(input.nextInt());
	input.nextLine();
	
	int numStudents = 0;
	
	System.out.println("Instructor: ");
	String Instructor = String.valueOf(input.nextLine());
	
	System.out.println("Section Number: ");
	int sectNum = Integer.valueOf(input.nextInt());
	input.nextLine();
	
	System.out.println("Location: ");
	String Location = String.valueOf(input.nextLine());
	
	Course new_course = new Course(courseName, courseID, maxStudents, numStudents, Instructor, sectNum, Location);
	ReadCourse.courses.add(new_course);
	
	return new_course;
}

//delete course method, accepts CourseId, find it and deletes it from the main course list
public void delCourse(){
	System.out.println("Course ID that you want to delete: ");
	String ID = String.valueOf(input.nextLine());
	Course courseToRemove = null;
	for (Course C : ReadCourse.courses){
		if (C.getcourseId().equals(ID)) {
			courseToRemove = C;
		}
	}
	if (courseToRemove != null) {
		courseToRemove.unenroll(courseToRemove);
		ReadCourse.courses.remove(courseToRemove);
		System.out.println("Course deleted!");
	} else {
		System.out.println("Course not found");
	}
}
//add student method, requests all four inputs and makes new student object. Adds to student list and returns student for future use in program
public Student addStudent() {
	System.out.print("Please enter the Student's first name: ");
	String newFirst = String.valueOf(input.nextLine());
	System.out.print("Please enter the Student's last name: ");
	String newLast = String.valueOf(input.nextLine());
	System.out.print("Please enter the Student's username: ");
	String newUser = String.valueOf(input.nextLine());
	System.out.print("Please enter the Student's password: ");
	String newPass = String.valueOf(input.nextLine());
	Student S = new Student(newUser,newPass,newFirst,newLast);
	main2.students.add(S);
	return S;
}
//edit course method, opens a menu for admin to decide what they would like to edit
public void editCourse(Course C){
	String choice = "";
	while(choice !="R") {
//Switch case for Admin to decide what to do based on entry
		System.out.println("What would you like to edit? \n"
					 + "M : Maximum Students \n"
					 + "S : Add Students \n"
					 + "I : Instructor \n"
					 + "SN : Section Number \n"
					 + "L : Location \n"
					 + "R: Return to Menu\n");
		choice = String.valueOf(input.nextLine()).toUpperCase();
		switch (choice) {
			case "M":
				System.out.print("Enter new maximum number of students: ");
				int newMax = Integer.valueOf(input.nextInt());
				C.setmaxStudents(newMax);
				break;
				
			case "S":
				Student newStu = addStudent();
				ArrayList <Student> St1 = C.getlistStudents();
				St1.add(newStu);
				C.setlistStudents(St1);
				int St2 = C.getnumStudents();
				St2+=1;
				C.setnumStudents(St2);
				break;
				
			case "I":
				System.out.print("Please enter the new instructor name: ");
				String newIns = String.valueOf(input.nextLine());
				C.setinstructor(newIns);
				break;
				
			case "SN":
				System.out.print("Enter new Section Number: ");
				int newSN = Integer.valueOf(input.nextInt());
				C.setsectNum(newSN);
				break;
				
			case "L":
				System.out.print("Please enter new location: ");
				String newLoc = String.valueOf(input.nextLine());
				C.setlocation(newLoc);
				break;
			case "R":
				choice = "R";
				break;	
			}
	}
}
//Method to view all full courses
//Checks if current number of students is equal to max students and then prints out the course name
public void viewFullCourses() {
	for (Course C : ReadCourse.courses){
		if (C.getnumStudents()>= C.getmaxStudents()) {
			System.out.println(C.getcourseName());
		}
	}
}

//getter and setter methods for passwords and username
public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}
}