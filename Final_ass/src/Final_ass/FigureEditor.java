package Final_project;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FigureEditor extends JFrame{
    PanelA pa;
	static char cmd = 'N'; //도형 구분하는 cmd?
	//기본 프레임 만들기
	public FigureEditor(){
		setTitle("MyFrame"); //출력되는 panel의 이름 설정
		setSize(500,300); //전체 판넬 사이즈
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 닫으면 자동 종료

		pa = new PanelA();
		//큰 판넬에 작은 판넬들 부착
		add(pa,BorderLayout.CENTER);
		add(new PanelC(pa), BorderLayout.LINE_START);
		
		//panel 출력하기(보이도록!)
		setVisible(true);
	}
	class MyListner implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="사각") {
				cmd = 'R';
			}else if(e.getActionCommand()=="직선") {
				cmd = 'L';
			}else if(e.getActionCommand()=="타원") {
				cmd = 'E';
			}else {
				cmd = 'N';
				
		}
	}
	
    
}
	//노란 바탕의 첫 번째 판넬
	class PanelA extends JPanel{
			JLabel label;
			//도형 그릴 때 사용할 Point class
			Point start,end;

			//도형 감지하기 위한 변수들
			static Shape checkShape;
			int check = 0;
		
			ArrayList<Shape> shapes = new ArrayList<Shape>(); //각 도형들 저장할 공간
			ArrayList<Shape> miniBox = new ArrayList<Shape>(); //도형 클릭 시 나올 작은박스를 위한 ArrayList
	
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

			//....? 이거 뭔 함수인지 물어보기
			void clickShape(int x, int y) {
				if(shapes !=null) {
					for(Shape s : shapes) {
						if(s instanceof Line && FigureEditor.cmd == 'N') {
							Line l = (Line)s;
							if((Math.min(l.x,l.endx)<=x && x<=Math.max(l.x,l.endx)) && (Math.min(l.y,l.endy)<=y && y<=Math.max(l.y,l.endy))) {
								check = 1;
								checkShape = s;
								return;
							}
						}
						else if(FigureEditor.cmd=='N' && s instanceof Rectangle) {
							Rectangle r = (Rectangle)s;
							if((r.x<=x && x<=r.x+r.width) && r.y<=y && y<=r.y+r.height) {
							check = 1;
							checkShape = s;
							return;
							}	
						}
						else if(FigureEditor.cmd=='N' && s instanceof Oval) {
							Oval o = (Oval)s;
							if((o.x<=x && x<=o.x+o.width) && o.y<=y && y<=o.y+o.height) {
								check = 1;
								checkShape = s;
								return;
					
							}
						}
			
					}
				}
				check = -1;
			}
			
			void drawBox(MouseEvent e) {
				Shape s1, s2;
				clickShape(e.getX(),e.getY());
				switch(check) {
				case 1:{
					miniBox.removeAll(miniBox);
					if(checkShape instanceof Rectangle) {
						Rectangle r = (Rectangle)checkShape;
						s1 = new Rectangle((r.x) - 2, (r.y) - 2, 4, 4);
						s2 = new Rectangle((r.x) + r.width - 2, (r.y) + r.height - 2, 4, 4);
						miniBox.add(s1);
						miniBox.add(s2);
						repaint();
						check = 0;
						break;
					}else if (checkShape instanceof Line) {
						Line l = (Line) checkShape;
						s1 = new Rectangle((l.x) - 2, (l.y) - 2, 4, 4);
						s2 = new Rectangle((l.endx) - 2, (l.endy) - 2, 4, 4);
						miniBox.add(s1);
						miniBox.add(s2);
						repaint();
						check = 0;
						break;
					}else if(checkShape instanceof Oval) {
						Oval o = (Oval) checkShape;
						s1 = new Rectangle((o.x) - 2, (o.y) - 2, 4, 4);
						s2 = new Rectangle((o.x) + o.width - 2, (o.y) + o.height - 2, 4, 4);
						miniBox.add(s1);
						miniBox.add(s2);
						repaint();
						check = 0;
						break;
					}
				}
				case -1:
				{
					if(FigureEditor.cmd == 'N')
					miniBox.removeAll(miniBox);
					check = 0;
					repaint();
					break;
				}
				default:
				{
					repaint();
					break;
				}
				}
			}
			//여러 개의 마우스 리스너를 넣기 위해 만든 클래스 
			class MyMouseListener extends MouseAdapter{
				Shape a;
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
						shapes.add(new Oval(x,y,width,height));
					else if(label.getText().equals("직선"))
						shapes.add(new Line(start.x,start.y,end.x,end.y));
					else if(label.getText().equals("복사"))
					{
						//복사할 코드
					}
						
					else if(label.getText().equals("삭제")){
						//삭제에 사용할 코드
					}
						
					
					
		
				}

				@Override
				public void mouseDragged(MouseEvent e) {
					super.mouseDragged(e);
					end = e.getPoint();
					repaint();//마우스 드래그 할 때마다 도형 그리기
					a = miniBox.get(miniBox.size()-1);//...?
				}
				@Override
				public void mousePressed(MouseEvent e) {
					start = e.getPoint();
				}
		
				//MouseMOtionListener 안에 있는 method들을 사용하지 않더라도 빈 함수로라도 선언은 해주어야 함
		
				@Override
				public void mouseMoved(MouseEvent e) {
				}
				public void mouseCliked(MouseEvent e){
					//클릭하면 복사
					//클릭하면 삭제
					//클릭하면 박스 그리기
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
    	JButton button1 , button2 , button3, button4, button5, button6 , button7;
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
            button4 = new JButton("복사");
            button5 = new JButton("삭제");
            button6 = new JButton("저장");
            button7 = new JButton("불러오기");
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
    		//마우스로 버튼 클릭할 경우, 버튼에 있는 text 인식하기
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
