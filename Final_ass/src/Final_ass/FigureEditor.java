package Final_ass;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import Final_ass.Rectangle;
import Final_ass.Shape;
import practice9_1.PannelA;
import practice9_1.PannelB;

public class FigureEditor extends JFrame{
	PanelA pa;
	//기본 프레임 만들기
	public FigureEditor(){
		//출력되는 panel의 이름 설정
		setTitle("MyFrame");
		//전체 판넬 사이즈
		setSize(500,300);

		pa = new PanelA();
		//큰 판넬에 작은 판넬들 부착
		add(pa,BorderLayout.CENTER);
		add(new PanelC(pa), BorderLayout.WEST);
		
		//panel 출력하기(보이도록!)
		setVisible(true);

	}
    
}
	//노란 바탕의 첫 번째 판넬
 class PanelA extends JPanel{
	JLabel label;
	//도형 그릴 때 사용할 Point class 변수들
	Point start,end;

	ArrayList<Shape> shapes = new ArrayList<Shape>();
	//panel 생성자
	public PanelA() {
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
    
    //왼쪽 메뉴바 판넬
    class PanelB extends JPanel{
    	//각 버튼마다 다른 일을 하기 떄문에 따로 생성
    	JButton button1 , button2 , button3;
    	//panelA위에 PanelB에서 실행하는 그래픽 그리기 위해 PanelA를 받음
    	PanelA pa;
        PanelB(PanelA pa){
        	this.pa = pa;//PanelA 객체
        	//배경
            setBackground(Color.BLUE);
            setLayout(new GridLayout(3,1,5,5));
            //버튼들 받기
            button1 = new JButton("사각");
            button2 = new JButton("직선");
            button3 = new JButton("타원");
            add(button1);
            add(button2);
            add(button3);
            
    		//버튼들 모두 마우스 리스너 추가
    		ActionListener listener = new MyActionListener();
//    		addMouseMotionListener(new MyMouseMotinListener());
    		button1.addActionListener(listener);
    		button2.addActionListener(listener);
    		button3.addActionListener(listener);
        }
        class MyActionListener implements ActionListener{
    		//마우스로 버튼 클릭할 경우, 버튼에 있는 text가 label 자리에 출력되도록 함
    		@Override
    		public void actionPerformed(ActionEvent e) {
//    			System.out.println(e.getActionCommand());
    			pa.label.setText(e.getActionCommand());
    		}
    	}
    }
    
class PanelC extends JPanel{
    public PanelC(PanelA pa){
    		//판넬B 포함,
    		//판넬A에 판넬B에서의 값을 출력시키기 떄문에 판넬A를 판넬B에 넣어줌
    		add(new PanelB(pa));
    	}
    }