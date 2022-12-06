package finalProject;
import java.awt.*;
import java.io.Serializable;

abstract class Shape implements Serializable {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public Shape(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;
    }

    abstract public void draw(Graphics g);
    abstract public boolean contains(Point p);
}

class Rectangle extends Shape{ // 사각 클래스 생성

    public Rectangle (int x, int y, int width, int height) {
        super(x,y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        if(p.x >= x && p.y >= y && p.x <= x+width && p.y <= y+height)
            return true;//포함한다고 뱉기
        else 
        	return false;
    }
}

class Oval extends Shape{ // 타원 클래스 생성

    public Oval (int x, int y, int width, int height) {
        super(x,y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.drawOval(x, y, width, height);
    }

    @Override
    public boolean contains(Point p) {
        float a = width / 2;
        float b = height / 2;
        float x1 = x + a;
        float y1 = y + b;
        float x = p.x;
        float y = p.y;


        if((x-x1)*(x-x1)/(a*a) + (y-y1)*(y-y1)/(b*b) <=1 )
            return true;
        else 
        	return false;
    }
}

class Line extends Shape{ // 직선 클래스 생성

    public Line (int x, int y, int width, int height) {
        super(x,y, width, height);
    }
    @Override
    public void draw(Graphics g) {
        g.drawLine(x,y,width,height);
    }

    @Override
    public boolean contains(Point p) {
        float x1 = x;
        float y1 = y;
        float x2 = width;
        float y2 = height;
        int y = p.y;
        float x = p.x;
        float max = x2;
        float min = x1;
        if(x1>x2) {
            max = x1;
            min = x2;
        }
        //x는 x1 과 x2 사이의 범위에 있어야함.
        if(y >= (int)(((y2-y1)/(x2-x1))*(x-x1)+y1)-2 && y <= (int)(((y2-y1)/(x2-x1))*(x-x1)+y1)+2 && x >= min && x<= max)
            return true;
        else 
        	return false;
    }
}
