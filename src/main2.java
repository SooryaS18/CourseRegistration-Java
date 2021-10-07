import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
class main2 {
//create a new list of students to be manipulated and store information while the program runs
	public static ArrayList <Student> students = new ArrayList<Student>();
		
	public static void main(String[] args) {

// import information
	deserialize(ReadCourse.courses);
//create scanner object
	Scanner input = new Scanner(System.in);

	ReadCourse.readData();

//begin user experience, ask if they would like to continue course registration
	System.out.println("Welcome, continue with course registration? Y or N");
	String Response1 = String.valueOf(input.nextLine()).toUpperCase();
//start while loop that is broken after every action is completed
	boolean choice1 = true;
	while (choice1 == true){
//break out of validation loop
		if(Response1.equals("Y")) {
			System.out.println("Continuing...");
			choice1 = false;
		}
//end program
		else if (Response1.equals("N")) {
			System.out.println("Goodbye");
			System.exit(0);
		}
//restart while loop
		else{System.out.println("Invalid, Try Again");
		System.out.println("Welcome, continue with course registration? Y or N");
		Response1 = String.valueOf(input.nextLine()).toUpperCase();
		}	
	}
// just a test student to check if student functions are working, and if student logins are working	
	Student test_Student = new Student ("JSmith","Goldfish22","John", "Smith");
	students.add(test_Student);
//same principle as above: login validation loop thats broken once a correct input is detected
	boolean choice2 = true;
	while (choice2 == true){
		System.out.println("Enter Login Details: ");
		System.out.println("Username: ");
		String Username = String.valueOf(input.nextLine()); 
		System.out.println("Password: ");
		String Password = String.valueOf(input.nextLine()); 
//no menu option to ask if admin or student. if admin credentials are detected, system automatically defaults to admin interface
		if(Username.equals("Admin") && Password.equals("Admin001")){
			System.out.println("Welcome Admin");
//new and only admin created
			Admin admin1 = new Admin();
			AdminLogin(admin1);
			choice2 = false;
//defaults to student login if admin isn't detected
		} else{
			for (Student S : students) {
				if (S.getusername().equals(Username) && S.getpassword().equals(Password)){
					System.out.println("Welcome " + S.getfirstName());
//creates local student object to use to access other classes
					Student S1 = new Student(S.getusername(), S.getpassword(), S.getfirstName(), S.getlastName());
					StudentLogin(S1);
					choice2 = false;
					break;
				}	
//if both student and admin log in fails, the loop starts again
				System.out.println("Invalid Log In, Try again: ");
			}
		}	
	}
	input.close();
}

//menus typed in advance to improve readability

//first menu to categorize options
	static String adminMenu = "What would you like to do? \n"
			+ "1 : Course Management \n"
			+ "2 : View Reports \n"
			+ "3 : Exit \n";

//course management options
	static String adminMenu1 = "What would you like to do?\n "
			+ "1 : Create a New Course \n"
			+ "2 : Delete a course \n"
			+ "3 : Edit a course \n"
			+ "4 : Display Course Information \n"
			+ "5 : Register a new Student \n"
			+ "6 : Exit \n";
	
//course report options
	static String adminMenu2 = "What report would you like to view? \n"
				+ "1 : All Courses \n"
				+ "2 : Full Courses \n"
				+ "3 : Save Full Courses \n"
				+ "4 : View Course Roster \n"
				+ "5 : View Student Courses \n"
				+ "6 : Exit \n";
//student options
	static String StudentMenu = "What would you like to do? \n"
			+ "1 : View all courses \n"
			+ "2 : View all full courses \n"
			+ "3 : View all open courses \n"
			+ "4 : Register for a course \n"
			+ "5 : Drop a course \n"
			+ "6 : View courseload \n"
			+ "7 : Exit \n";
	
//program path for admin login
//choice validation loop same as above
public static void AdminLogin(Admin admin) {
	Scanner input = new Scanner(System.in);
	boolean adminChoice1 = false;
	while (adminChoice1 == false) {
		System.out.println(adminMenu);
		Integer Choice1 = Integer.valueOf(input.nextInt());
		input.nextLine();	
		if( Choice1 == 1 || Choice1 == 2 ||  Choice1 == 3){
			switch (Choice1) {
			case (1):{
				boolean adminChoice2 = false;
				while (adminChoice2 == false) {
					System.out.println(adminMenu1);
					String Choice2 = String.valueOf(input.nextLine());
//if valid input, switch case begins, and breaks after every correct input. this leads the user to previous menu, and user can keep exiting following similar process
					if(Choice2.equals("1") ||Choice2.equals("2") || Choice2.equals("3")|| Choice2.equals("4") || Choice2.equals("5") || Choice2.equals("6")){
						switch(Choice2) {
							case("1"):{
								System.out.println("Create a new course: ");
								admin.addCourse();
								System.out.println("New course created!");
								break;
							}
							case("2"):{
								System.out.println("Delete a course: ");
								 admin.delCourse();
								 break;
							}
							case("3"):{
//collects course ID and name and allows admin to edit that course
								System.out.println("Edit a course: ");
								System.out.println("Course ID that you want to edit: ");
								String ID = String.valueOf(input.nextLine());
								Course courseToEdit = null;
								for (Course C : ReadCourse.courses){
									if (C.getcourseId().equals(ID)) {
										courseToEdit = C;
									}
								}
								if (courseToEdit != null) {
									admin.editCourse(courseToEdit);
									System.out.println("Course edited");
								} else {
									System.out.println("Course not found");
								}						 
								
								 break;
							}
							case("4"):{
//collects course name, creates course object and displays information
								System.out.println("Display course information: ");
								System.out.println("What course would you like to view: ");
								String Name = String.valueOf(input.nextLine());
								for (Course C : ReadCourse.courses){
									if (C.getcourseName().equals(Name)) {
										Course course2 = new Course(C.getcourseName(), C.getcourseId(), C.getmaxStudents(), C.getnumStudents(), C.getinstructor(), C.getsectNum(), C.getlocation());
										course2.CourseInfo(course2);
									}
								}
								 break;
							}
							case("5"):{
//registers student to main database
								System.out.println("Register new student: ");
								admin.addStudent();
								System.out.println("New Student Registered");
								break;
							}
							case("6"):{
								System.out.println("Exit\n");
								adminChoice2 = true;
							}
						}
					}
				}
				break;
			}
			
			case (2):{
				boolean adminChoice2 = false;
				while (adminChoice2 == false) {
					System.out.println(adminMenu2);
					String Choice2 = String.valueOf(input.nextLine());
					
					if(Choice2.equals("1") || Choice2.equals("2") || Choice2.equals("3")|| Choice2.equals("4") || Choice2.equals("5") || Choice2.equals("6")) {
						switch(Choice2) {
							case("1"):{
								System.out.println("All Courses: \n");
								admin.getCourses();
								System.out.println();
								break;
							}
							case("2"):{
								System.out.println("All full courses: ");
								admin.viewFullCourses();
								break;
							}
							case("3"):{
//basically makes a new courselist, iterates through the original courselist. If a course has the same number of current students as max students, the course is added to the new list.
								System.out.println("Save full courses: ");
								ArrayList <Course> full_courses = new ArrayList<Course>();
								for (Course C : ReadCourse.courses){
									if (C.getnumStudents()>= C.getmaxStudents()) {
										full_courses.add(C);
									}
								}
//local list is saved to a file
								saveFull(full_courses);
								break;
							}
//takes Course name, finds it in main class list and makes new local object, and reads out associated students
							case("4"):{
								System.out.println("View Course Roster: ");
								System.out.println("What course would you like to view: ");
								String Name = String.valueOf(input.nextLine());
								for (Course C : ReadCourse.courses){
									if (C.getcourseName().equals(Name)) {
										Course course2 = new Course(C.getcourseName(), C.getcourseId(), C.getmaxStudents(), C.getnumStudents(), C.getinstructor(), C.getsectNum(), C.getlocation());
										course2.getStudents(course2);
									}
									else {
										System.out.println("Course Not Found");
									}
								}
								break;
							}
							case("5"):{
//takes student first and last name, finds it in main student list and activates viewcourseLoad method
								System.out.println("View Courses for a Student");
								System.out.println("Enter Student First Name: ");
								String FirstName = String.valueOf(input.nextLine());
								System.out.println("Enter Student Last Name: ");
								String LastName = String.valueOf(input.nextLine());
								for(Student S : students){
									if (S.getfirstName().equals(FirstName) && (S.getlastName().equals(LastName))) {
										S.viewCourseload();
										}
									}
								}
							case("6"):{
								System.out.println("Exit \n");
								adminChoice2 = true;
								break;
							}
						}
					} 
				}
				break;
			}
			
			case (3):{
//saves and serializes any changes, and closes the program
				System.out.println("Exiting Program");
				serialize(ReadCourse.courses);
				System.exit(0);
			}
		}
		}
		else {
			System.out.println("Invalid Input, Try Again");
		}
	}
	input.close();

}
//same principle as above with creating a new student object to work on. Also sets up while loop for choice validation
public static void StudentLogin(Student S1){
	Scanner input = new Scanner(System.in);
	boolean StudentVal = false;
	while (StudentVal == false) {
		System.out.println(StudentMenu);
		String StuChoice1 = String.valueOf(input.nextLine());
		if(StuChoice1.equals("1") || StuChoice1.equals("2") || StuChoice1.equals("3")|| StuChoice1.equals("4") || StuChoice1.equals("5") || StuChoice1.equals("6") || StuChoice1.equals("7")){
			switch(StuChoice1) {
//views all courses
			case("1"):{
				System.out.println("View All Courses: ");
				S1.getCourses();
				break;
			}
//views all full courses
			case("2"):{
				System.out.println("View all full courses: ");
				S1.viewFullCourses();
				break;
			}
			
			case("3"):{
				System.out.println("View all open courses: ");
				S1.viewOpenCourses();
				break;
			}
			
			case("4"):{
//requests section number and course name and registers student to coruse
				System.out.println("Register for a course ");
				System.out.println("Enter name of Course: ");
				String Name = String.valueOf(input.nextLine());
				System.out.println("Enter course section: ");
				Integer SectNum = Integer.valueOf(input.nextInt());
				input.nextLine();
				S1.register(Name,SectNum);
				break;
			}
			case("5"):{
//withdraw from course
				System.out.println("Withdraw from a course ");
				System.out.println("Enter name of Course: ");
				String Name = String.valueOf(input.nextLine());
				Integer courseIndex = -1; 
				
				for (Course C : ReadCourse.courses){
					if (C.getcourseName().equals(Name)) {
						courseIndex = ReadCourse.courses.indexOf(C);
					}
				}
				
				if (courseIndex != -1) {
					ReadCourse.courses.get(courseIndex).dropStudent(S1);
				} else {
					System.out.println("Course could not be found");
				}
				
				S1.withdraw(Name);
				break;
			}
			case("6"):{
				System.out.println("View Courseload: ");
				S1.viewCourseload();
				break;
			}
			case("7"):{
//saves, serializes, and closes program
				System.out.println("Exiting Program");
				serialize(ReadCourse.courses);
				System.exit(0);
			}
		}
		}
		else {
			System.out.println("Invalid Input, Try Again");
		}
	}
	input.close();
}

//code to deserialize information for program to run
// Course data is imported in the ReadCourse class so this method needs to take it in as an argument

public static void deserialize(ArrayList <Course> courses) {
	try {
//creates a new file and new input object, writes from file
		FileInputStream fileInput = new FileInputStream("courses.ser");
	    ObjectInputStream objInput = new ObjectInputStream(fileInput);	    
	    courses = (ArrayList<Course>) objInput.readObject();
	    fileInput.close();
	    objInput.close();
	    
	    System.out.println("Courses Deserialized\n");
//runs 2x, both for students and courses
	    fileInput = new FileInputStream("students.ser");
	    objInput = new ObjectInputStream(fileInput);
	    students = (ArrayList<Student>) objInput.readObject();
	    fileInput.close();
	    objInput.close();
	    
	    System.out.println("Students Deserialized\n");
// if the referenced file isn't found, method automatically defaults to the original course data provided
	} catch (FileNotFoundException e) { 
		System.out.println("Importing original courselist");
		ReadCourse.readData();
		
	} catch (Exception e) {
//stack trace for debugging
		e.printStackTrace();
	}
}

//code to serialize information after program finishes
//Course data is imported in the ReadCourse class so this method needs to take it in as an argument
public static void serialize(ArrayList <Course> courses) {
	try {
//creates a new file and new input object, writes to file
		FileOutputStream fileOutputStream = new FileOutputStream("courses.ser");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(courses);
		fileOutputStream.close();
		objectOutputStream.close();
		System.out.println("Courses Serialized\n");
//done 2x for both students and courses
		fileOutputStream = new FileOutputStream("students.ser");
		objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(students);
		fileOutputStream.close();
		objectOutputStream.close();
		System.out.println("Students Serialized\n");
//catches error for debugging
	} catch (IOException e) {
		e.printStackTrace();
	}
}

//course to save files of full courses
//creates a .txt file
public static void saveFull(ArrayList <Course> Courses) {
	try {
		File file = new File("FullCourses.txt");
		if(file.createNewFile()) {
			System.out.print("Fullcourses Saved. \n");
		} else {
			System.out.print("File already exists. \n");
		}
	} catch (IOException e) {
		System.out.print("File not created \n" );
		e.printStackTrace();
	}
}




}
