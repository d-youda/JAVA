package practice9_1;
import java.awt.*;

//같은 일 하는 클래스를 간결하게 선언하기 위한 Shape 클래스
abstract class Shape {
	int x;
	int y;
	
	public Shape(int x, int y) {
		
		this.x = x;
		this.y = y;
	}
	abstract public void draw(Graphics g);

}


class Rectangle extends Shape{
	int width;
	int height;
	public Rectangle(int x,int y, int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}
	@Override
	public void draw(Graphics g) {
		g.drawRect(x,y,width , height);
	}
}

class Eclipse extends Shape{
	int width;
	int height;
	public Eclipse(int x, int y ,int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.drawOval(x, y, width, height);
	}
}