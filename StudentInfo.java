import java.util.*;

public class StudentInfo {
	public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new UnderGraduate("100","Lee","사물인터넷",21,"테니스"));
        students.add(new UnderGraduate("101","Kim","지능시스템",21,"요가"));
        students.add(new UnderGraduate("102","Park","사이버보안",24,"댄스"));
        students.add(new UnderGraduate("103","Kim","ICT융합엔터테인먼트",21,"야구"));
        students.add(new Graduate("G100","Jung","정보시스템",26,"석사","인공지능"));
        students.add(new Graduate("G101","Lee","정보시스템",26,"석사","보안"));
        students.add(new Graduate("G200","Han","정보컴퓨터공학",28,"박사","임베디드시스템"));

        saveDataToFile(students, "output.txt");
        System.out.println(loadDataFromFile("output.txt"));

        saveObjectToFile(students, "output.dat");
        System.out.println(loadObjectFromFile("output.dat"));

    }
    public static void saveDataToFile(ArrayList<Student> students, String fileName)  {...}
    public static ArrayList<Student> loadDataFromFile(String fileName) {...}

    public static void saveObjectToFile(ArrayList<Student> students, String fileName) {...}
    public static ArrayList<Student> loadObjectFromFile(String fileName) {...}
	}
}

class Student{
	String studentNum;
	String name;
	String lesson;
	int age;
	
	public Student(String studentNum, String name, String lesson, int age){
		this.studentNum = studentNum;
		this.name = name;
		this.lesson = lesson;
		this.age = age;		
	}
}

class UnderGraduate extends Student {
	String club;
	public UnderGraduate(String studentNum, String name, String lesson, int age,String club) {
		super(studentNum,name,lesson,age);
		this.club = club;
	}
}
class Graduate extends Student{
	String degree;
	String major;
	public Graduate(String studentNum, String name, String lesson, int age,String degree, String major) {
		super(studentNum,name,lesson,age);
		this.degree = degree;
		this.major = major;
	}
}
