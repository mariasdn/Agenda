package ui;


import java.util.ArrayList;

public class Schedule {

    private ArrayList<Course> courseList;

    //REQUIRES: nothing
    //MODIFIES: this, weekDays
    //EFFECTS:  constructs a schedule with a course list that has set courses in it
    public Schedule (){
        courseList = new ArrayList<>();

        Boolean[] b = {true, false, true, false, false};

        Course courseA = new Course("COMM 290", 8,2, b);

        Course courseB = new Course("COMM 205",10,2, b);
        courseB.setWeekDays (false,false,true,false,true);

        Course courseC = new Course("COMM 295", 9,2, b);
        courseC.setWeekDays (false,true,false,true,false);

        courseList.add(courseA);
        courseList.add(courseB);
        courseList.add(courseC);

    }

    //REQUIRES: valid instance of course
    //MODIFIES: the list courseList
    //EFFECTS:  adds the course to the list of courses
    public void add(Course course) {
        courseList.add(course);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS:  prints out name, start and end time of each course in the list of courses
    public void printCourses() {
        for (Course c : courseList) {
            System.out.println("--------------------");
            System.out.println(c.getName() + ":");
            System.out.println("Starts at: " + c.getStartTime());
            System.out.println("Ends at: " + c.getEndTime());
        }
    }

    //REQUIRES: an array list of courses
    //MODIFIES: nothing
    //EFFECTS:  prints out name, start and end time of each course in the parameter list
    public void printCourses(ArrayList<Course> courses) {
        for (Course c : courses) {
            System.out.println("--------------------");
            System.out.println(c.getName() + ":");
            System.out.println("Starts at: " + c.getStartTime());
            System.out.println("Ends at: " + c.getEndTime());
        }
    }

    //REQUIRES: string that is only one of "m", "t", "w", "th" or "f"
    //MODIFIES: nothing
    //EFFECTS:  prints out all courses with lectures on the day corresponding to the string parameter
    public void printCoursesOnDay (String day) {
        if (day.equals("m")) {
            printCourses(findCoursesOnDay(0));
        }
        if (day.equals("t")) {
            printCourses(findCoursesOnDay(1));
        }
        if (day.equals("w")) {
            printCourses(findCoursesOnDay(2));
        }
        if (day.equals("th")) {
            printCourses(findCoursesOnDay(3));
        }
        if (day.equals("f")) {
            printCourses(findCoursesOnDay(4));
        }
    }

    //REQUIRES: string that is only one of "m", "t", "w", "th" or "f"
    //MODIFIES: nothing
    //EFFECTS:  creates and returns a list of courses that have lectures
    //          on the day corresponding to the string parameter
    public ArrayList<Course> findCoursesOnDay(int day) {
        ArrayList<Course> coursesThatDay = new ArrayList<>();
        for (Course c : courseList) {
            if (c.isCourseOnThisDay(day)){
                coursesThatDay.add(c);
            }
        }
        return coursesThatDay;
    }
}
