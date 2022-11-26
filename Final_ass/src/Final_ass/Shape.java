package Final_ass;
import java.awt.Graphics;

//캡슐화 위한 Shape 객체 생성-> 출력될 도형들의 클래스!
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

class Line extends Shape{
	int x2;
	int y2;
	public Line(int x1, int y1, int x2, int y2) {
		super(x1,y1);
		this.x2 = x2;
		this.y2 = y2;
	}
	public void draw(Graphics g) {
		g.drawLine(x, y, x2, y2);
	}
}