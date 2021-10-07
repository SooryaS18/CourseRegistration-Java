public interface AdminInterface {
// Methods for the admin to utilize in the interface
	public Course addCourse();
	public void delCourse();
	public Student addStudent();
	public void editCourse(Course C);
	public void getCourses();
	public void viewFullCourses();
	public void viewOpenCourses();
}

