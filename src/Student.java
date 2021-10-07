import java.util.ArrayList;
import java.util.*;
import java.io.*;
//class for inheriting user and building student
public class Student extends User implements StudentInterface{

	private static final long serialVersionUID = 1L;
	
//new list created holding courses for each student
	
	private static ArrayList<Course> studentCourses = new ArrayList<>();

//student attributes
	Student(String userName, String password, String firstName, String lastName){
		super (userName, password, firstName, lastName);		
	}
	
//general method to check for duplicates in a student's course load, implemented later in the program
	
	public static boolean checkSched (Course CourseName) {
		if (studentCourses.contains(CourseName)){
			return true;
		}
		else return false;
	}
	
//register for a course through course name and section number
//first checks if course is in the course load and if course is open, and then matches course name and section number
//adds courses into student course load
	
	public void register(String CourseName, Integer SectNum) {
		try {
			Course C = searchbyname(CourseName);
			if (checkSched(C) == true) {
				System.out.println("Course already in schedule!");
			}
			else {
				if (C.getcourseName().equals(CourseName) && (C.getsectNum() == SectNum)) {
					if(C.getnumStudents() < C.getmaxStudents()) {
						C.addStudent(this);
						studentCourses.add(C);
						System.out.println("Successfully Enrolled!");
					}
					else {
						System.out.println("Course is Full");
					}
				}
			}
		} 
		catch(NullPointerException e){
			System.out.println("Course not found");
		  }
	
	}

//checks course name, and removes it from the student's enrolled courses
	public void withdraw(String CourseName) {
		try {
			Course C = searchbyname(CourseName);
			if (checkSched(C) == true){
				studentCourses.remove(C);
				System.out.println("Successfully Dropped!");
			}
			
			else {
				System.out.println("Course not found in courseload.");
			}
		} catch(NullPointerException e){
			System.out.println("Course not found.");
		}
	
	}

//iterates through list created above and prints the course name
	public void viewCourseload() {
		for (int i = 0 ; i < studentCourses.size();i++){
			System.out.println(studentCourses.get(i).getcourseName() + "\n");
		}
}

//iterates through main list of courses and prints course name if number of students is below the maximum number of students
	public void viewOpenCourses() {
		for (Course C : ReadCourse.courses){
			if (C.getnumStudents()< C.getmaxStudents()) {
				System.out.println(C.getcourseName());
			}
		}
	}

}
