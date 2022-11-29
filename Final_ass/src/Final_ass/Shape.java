package Final_project;
import java.awt.Graphics;
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

class Oval extends Shape{
	int width;
	int height;
	public Oval(int x, int y ,int width, int height) {
		super(x,y);
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.drawOval(x, y, width, height);
	}
}

class Line extends Shape{
	int endx;
	int endy;
	public Line(int x1, int y1, int x2, int y2) {
		super(x1,y1);
		this.endx = x2;
		this.endy = y2;
	}
	public void draw(Graphics g) {
		g.drawLine(x, y, endx, endy);
	}
}
