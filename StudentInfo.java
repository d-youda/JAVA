import java.io.*;
import java.util.*;
/*
 * @author 유다현
 * 파일에 저장하는 코드
 */
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
    public static void saveDataToFile(ArrayList<Student> students, String fileName)  {
    	//input은 students이고, output을 파일에 두고 출력함
    	PrintWriter outputStream = null;
    	try {
			outputStream = new PrintWriter(new FileWriter(fileName));
			
			//students에 있는 애들 file로 옮겨 적기
			for (int i=0; i < students.size(); i++) {
				Student s = students.get(i);
				outputStream.println(s);
			}
			
			//열었던 파일 닫아주기
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
    }
    public static ArrayList<Student> loadDataFromFile(String fileName) {
    	ArrayList<Student> result=null;
    	
    	return result;

    }

    public static void saveObjectToFile(ArrayList<Student> students, String fileName) {
    	
    	//객체 스트림 코드 : input은 student이고, output은 filename에 저장함.
    	ObjectOutputStream out = null;
    	try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(students);
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static ArrayList<Student> loadObjectFromFile(String fileName) {
    	ArrayList<Student> result = null;
    	ObjectInputStream in = null;
    
			try {
				in = new ObjectInputStream(new FileInputStream(fileName));
				result = (ArrayList<Student>)in.readObject();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
    	return result;
    }

	}

//super class인 Student 클래스 생성
class Student{
	//대학생과 대학원생 공통으로 해당되는 부분 정의
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
	//대학생만 해당하는 부분 정읜
	String club;
	public UnderGraduate(String studentNum, String name, String lesson, int age,String club) {
		super(studentNum,name,lesson,age);
		this.club = club;
	}
	public String toString() {
		return studentNum+"|"+name+"|"+lesson+"|"+age+"|"+club;
	}
}
class Graduate extends Student{
	//대학원생만 해당하는 부분 정읜
	String degree;
	String major;
	public Graduate(String studentNum, String name, String lesson, int age,String degree, String major) {
		super(studentNum,name,lesson,age);
		this.degree = degree;
		this.major = major;
	}
	public String toString() {
		return studentNum+"|"+name+"|"+lesson+"|"+age+"|"+"|"+degree+"|"+major;
	}
}
