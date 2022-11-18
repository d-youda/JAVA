package practice9_1;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * 첫 번쨰 패널
 * 노란 배경, 마우스 리스너들 추가하여 화면에 도형 출력시킴
 * @author 유다현
 */
public class PannelA extends JPanel{
	JLabel label;
	//도형 그릴 때 사용할 Point class 변수들
	Point start,end;

	ArrayList<Shape> shapes = new ArrayList<Shape>();
	//panel 생성자
	public PannelA() {
		setBackground(Color.YELLOW);//배경색 설정

		//하나의 컴포넌트 : label
		label = new JLabel("여기가 그래픽 객체를 그리는 곳입니다");
		add(label);//panel에 label 추가
		//리스너 여러 개 만들기 위해 MyMouseListener 크래스 따로 선언 후 넣어줌
		MyMouseListener listener = new MyMouseListener();
		
		//리스너들 등록
		addMouseListener(listener);
		addMouseMotionListener(listener);
		setLayout(null);
		label.setSize(50,20);
		label.setLocation(30,30);
		//저장된 도형들 계속 출력되어 있게 하기
		for(int i=0 ; i<shapes.size() ; i++) {
			shapes.get(i);
		}
	}
	
	//여러 개의 마우스 리스너를 넣기 위해 만든 클래스 
	class MyMouseListener extends MouseAdapter{
		
		@Override
		public void mouseReleased(MouseEvent e) {
			super.mouseReleased(e);
			//마우스 끌 때마다 end에 마우스 위치 갱신됨
			end = e.getPoint();
			
			//그리기 시작하는 점과 갱신되는 점 중 작은 값을 그리기 시작위치로 둠
			int x = Math.min(start.x, end.x);
			int y = Math.min(start.y, end.y);
			
			//폭과 너비는 빼서 절댓값 받아, 음수가 들어오지 않도록 함
			int width = Math.abs(start.x - end.x);
			int height = Math.abs(start.y - end.y);
			
			//누른 버튼 이름에 맞게 출력된 도형들 shapes list에 add되도록 함
			if(label.getText().equals("사각")) 
				shapes.add(new Rectangle(x,y,width,height));
			
			else if(label.getText().equals("타원"))
				shapes.add(new Eclipse(x,y,width,height));
			else if(label.getText().equals("직선"))
				shapes.add(new Line(start.x,start.y,end.x,end.y));
			

		}
		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			end = e.getPoint();
			repaint();//마우스 드래그 할 때마다 도형 그리기
		}
		@Override
		public void mousePressed(MouseEvent e) {
			start = e.getPoint();
		}

		//MouseMOtionListener 안에 있는 method들을 사용하지 않더라도 빈 함수로라도 선언은 해주어야 함

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	//paintComponent 불러오기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//시작안하면 가만히 있음
		if(start==null)
			return;
		g.setColor(Color.BLUE);
		
		//그리기 시작하는 점과 갱신되는 점 중 작은 값을 그리기 시작위치로 둠
		int x = Math.min(start.x, end.x);
		int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);
        
        //화면에 도형들 그리기
        if (label.getText().equals("타원")) {
        	g.drawOval(x, y, width, height); 
        }
        	
        else if (label.getText().equals("사각"))
        	g.drawRect(x, y, width, height);
        else if (label.getText().equals("직선"))
        	g.drawLine(start.x,start.y, end.x, end.y);

        //Shapes안에 있는 도형들 계속 출력되어 있도록 설정
        for(Shape shape: shapes) {
			shape.draw(g);
//			g.dispose();
		}
	}
}

