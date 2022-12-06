package finalProject;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

class FigureEditorFrame extends JFrame {
    static PanelA pa;
    PanelC pc;

    FigureEditorFrame() {
        setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창 닫으면 자동 종료

		pa = new PanelA(this);
		pc = new PanelC(this);
        add(pa, BorderLayout.CENTER);
        add(pc, BorderLayout.WEST);

        setVisible(true);

    }
}

class PanelA extends JPanel {
	FigureEditorFrame firstFrame;
    //FigureEditorFrame topFrame;
	static BufferedImage paintImage = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
    Point start, end;//클릭 위치 저장하기 위한 Point변수
    Point lastPoint;//이전 클릭 위치를 저장하기 위한 Point 변수

    //JLabel label;
    String selButton;//선택한 버튼 label저장 위한 String 변수

    ArrayList<Shape> shapes = new ArrayList<Shape>();  // 도형 arrylist 생성
    boolean bselect; // 도형 선택 여부 저장하기 위한 String 변수
    Shape selectedShape; // 선택된 도형변수를 저장하기 위한 Shape 변수
    Rectangle leftRectangle = null; // 선택된 도형의 왼쪽위 점
    Rectangle rightRectangle = null; // 선택된 도형의 오른쪽아레 점

    PanelA(FigureEditorFrame frame) { //패널A 생성
    	firstFrame = frame;
        setBackground(Color.YELLOW); //배경색 노란색

        MyMouseListener listener = new MyMouseListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
		setLayout(null);
    }

    class MyMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            start = e.getPoint(); // 처음 눌렀을 떄 마우스 좌표
            lastPoint = start; 

            //shape를 3가지로 나눈다.
            bselect = false;//선택 전이므로 false 저장

			//Iterator 선언으로 반복문 사용
            Iterator iterator = shapes.iterator();
            while(iterator.hasNext()) {
                Object obj = iterator.next();//iterator 저장할 Object 객체 생성
                if(obj instanceof Rectangle) {
                    Rectangle rect = (Rectangle)obj; // obj를 Rec객체로 변경해주기

					//사각형 선택했을 경우,
                    if(rect.contains(start)) {
                        //control point 만들기
                        leftRectangle = new Rectangle(rect.x-2,rect.y-2,4,4); // 도형 선택시 나타나는 컨트롤 포인트
                        rightRectangle = new Rectangle(rect.x + rect.width -2,rect.y + rect.height -2,4,4);
                        bselect = true; // 도형이 선택되었다는 의미
                        selectedShape = rect; // 현재 선택된 도형은 사각형
                        //isPressed = true;
                        repaint();
                        //System.out.println("안에있음");
                    }

                }

				//타원 선택했을 경우,
                else if(obj instanceof Oval) {
                    Oval oval = (Oval) obj;
                    if(oval.contains(start)) {
                        //타원 선택
                        //control point 만들기
                        leftRectangle = new Rectangle(oval.x-2,oval.y-2,4,4);
                        rightRectangle = new Rectangle(oval.x + oval.width -2,oval.y + oval.height -2,4,4);
                        bselect = true;
                        selectedShape = oval;
                        repaint();
                    }
                }

				//직선 선택했을 경우,
                else if(obj instanceof Line){
                    Line line = (Line) obj;
                    //x는 x1 과 x2 사이의 범위에 있어야함.
                    if(line.contains(start)) {
                        //control point 만들기
                        leftRectangle = new Rectangle(line.x-2,line.y-2,4,4);
                        rightRectangle = new Rectangle(line.width -2, line.height -2,4,4);
                        bselect = true;
                        selectedShape = line;
                        repaint();
                    }
                }

            }


        }
        @Override
        public void mouseDragged(MouseEvent e) {
            //System.out.println(ctrlP1);
            end = e.getPoint();
            repaint(); // 패널의 그리기 요청 주목

            float gapX = end.x - lastPoint.x;
            float gapY = end.y - lastPoint.y;


            //도형이동
            if (selectedShape == null) 
				return;//선택된 도형 없으면 끝내기
            if (selectedShape instanceof Rectangle) {
                Rectangle rect = (Rectangle) selectedShape;
                rect.x += gapX;
                rect.y += gapY;

                leftRectangle.x += gapX;
                leftRectangle.y += gapY;
                rightRectangle.x += gapX;
                rightRectangle.y += gapY;

            } else if (selectedShape instanceof Oval) {
                Oval oval = (Oval) selectedShape;
                oval.x += gapX;
                oval.y += gapY;

                leftRectangle.x += gapX;
                leftRectangle.y += gapY;
                rightRectangle.x += gapX;
                rightRectangle.y += gapY;

            } else if (selectedShape instanceof Line) {
                Line line = (Line) selectedShape;
                line.x += gapX;
                line.width += gapX;
                line.y += gapY;
                line.height += gapY;

                leftRectangle.x += gapX;
                leftRectangle.y += gapY;
                rightRectangle.x += gapX;
                rightRectangle.y += gapY;
            }
            lastPoint = end;
            repaint();
        }
	void drawBox(MouseEvent e,int x,int y,int width,int height){
		//만약 도형을 클릭한 적이 없다면
        if(bselect == false) {
            leftRectangle = null;
            rightRectangle = null;
            bselect = false;
            selectedShape = null;
            repaint();
        }

        if(selButton.equals("사각")) { //사각 버튼을 눌렀을때
            //만약 선택된 도형이 있다면 그리지 않기.
            if(selectedShape != null)
                return;
                //그냥 클릭만 했다면 그리지 않기
                if(width == 0 || height == 0 ) {
                    return;
                }
                Shape rect = new Rectangle(x,y,width,height);
                shapes.add(rect);
            }
    	else if(selButton.equals("타원")) { //타원 버튼을 눌렀을떄
                //만약 선택된 도형이 있다면 그리지 않기.
                if(selectedShape != null) {
                    return;
                }
                //그냥 클릭만 했다면 그리지 않기
                if(width == 0 || height == 0 ) {
                    return;
                }
                Shape oval = new Oval(x,y,width,height);
                shapes.add(oval);
            }
        else if(selButton.equals("직선")) { //직선 버튼을 눌렀을떄
                //만약 선택된 도형이 있다면 그리지 않기.
                if(selectedShape != null) {
                    return;
                }
                //그냥 클릭만 했다면 그리지 않기
                if(width == 0 || height == 0 ) {
                    return;
                }
                Shape line = new Line(start.x, start.y, end.x, end.y);
                shapes.add(line);
            }
		}

		void copy(MouseEvent e,int x, int y,int width , int height){
			if (selButton.equals("복사")) {
                if(selectedShape==null) 
					return;

                //만약 선택도형을 이동시킨거라면 복사 x
                if(start.x != end.x && start.y != end.y) 
					return;

                if (selectedShape instanceof Rectangle) {
                    Rectangle rect = (Rectangle) selectedShape;
                    Rectangle newRect = new Rectangle(rect.x + 10, rect.y + 10, rect.width, rect.height);
                    shapes.add(newRect);
                    repaint();
                } else if (selectedShape instanceof Oval) {
                    Oval oval = (Oval) selectedShape;
                    Oval newOval = new Oval(oval.x + 10, oval.y + 10, oval.width, oval.height);
                    shapes.add(newOval);
                    repaint();

                } else if (selectedShape instanceof Line) {
                    Line line = (Line) selectedShape;
                    Line newLine = new Line(line.x + 10, line.y, line.width + 10, line.height);
                    shapes.add(newLine);
                    repaint();
                }
                selectedShape = null;
            }
		}
		//삭제하는 함수
		void del(MouseEvent e, int x, int y, int height , int width){
			if (selButton.equals("삭제")) {
                if(selectedShape==null) return;
                shapes.remove(selectedShape);
                //control point 까지 삭제
                bselect = false;
                leftRectangle = null;
                rightRectangle = null;
                repaint();
                selectedShape = null;
            }
		}
		

		//불러오는 함수
		void reback(MouseEvent e){
			if (selButton.equals("불러오기")) {
                //Shape shapes=null;

                FileInputStream fis= null;
                try {
                    fis = new FileInputStream("shapes.acc");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                ObjectInputStream ois= null;
                try {
                    ois = new ObjectInputStream(fis);
                    shapes=(ArrayList<Shape>) ois.readObject();
                    ois.close();
                    System.out.println("불러오기 완료");
                    repaint();
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
		}

        @Override
        public void mouseReleased(MouseEvent e) {
            end = e.getPoint();

            int x = Math.min(start.x, end.x);
            int y = Math.min(start.y, end.y);
            int width = Math.abs(start.x - end.x);
            int height = Math.abs(start.y - end.y);

			drawBox(e,x,y,width,height);
			copy(e,x,y,width,height);
			del(e,x,y,width,height);
			
			reback(e);
            repaint();
        }
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(paintImage, 0, 0, null);
        
        if(bselect) {
            leftRectangle.draw(g);
            rightRectangle.draw(g);
        }

        if(start == null) // 타원이 만들어지지 않았음
            return;
        g.setColor(Color.BLUE); // 파란색 선택
        int x = Math.min(start.x, end.x);
        int y = Math.min(start.y, end.y);
        int width = Math.abs(start.x - end.x);
        int height = Math.abs(start.y - end.y);

        Iterator<Shape> iterator = shapes.iterator();
        while(iterator.hasNext()) {
            Object obj = iterator.next();
            if(obj instanceof Rectangle) {
                Rectangle rect = (Rectangle)obj;
                rect.draw(g);
            }
            else if(obj instanceof Oval) {
                Oval oval = (Oval) obj;
                oval.draw(g);
            }
            else if(obj instanceof Line){
                Line line = (Line) obj;
                line.draw(g);
            }
        }

        if(width == 0 || height == 0 ) return;
        if(selectedShape !=null)  {
            return;
        }
        switch(selButton) {
            case "사각":
                g.drawRect(x, y, width, height); // 사각형 그리기
                break;
            case "타원":
                g.drawOval(x, y, width, height); // 타원 그리기
                break;

            case "직선":
                g.drawLine(start.x, start.y, end.x, end.y); // 직선 그리기
                break;
        }
    }
    public void updatePaint() {
    	Graphics g = paintImage.createGraphics();
    	g.dispose();
    	repaint();
    }
    
    public static void save() {					//이미지로 저장하는 함수
    	JFileChooser fileChooser = new JFileChooser();		//파일탐색기 실행
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images","png");		//파일탐색기 실행시에 png파일만 보이게 필터링
		fileChooser.setFileFilter(filter);
		
		int ret = fileChooser.showSaveDialog(null);

		if(ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "파일이 저장되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String path = fileChooser.getSelectedFile().toString();
			ImageIO.write(paintImage, "PNG", new File(fileChooser.getSelectedFile().toPath() + ".png"));		//파일을 저장할 때 뒤에 .png확장자를 붙임
		} catch (IOException ox) {
			// TODO: handle exception
			ox.printStackTrace();
		}
    	
    }
    
    public static void load() {				//이미지를 불러오는 함수
    	JFileChooser fileChooser = new JFileChooser();		//파일탐색기 실행
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images","png");		//파일탐색기 실행시에 png파일만 보이게 필터링
		fileChooser.setFileFilter(filter);
		
		int ret = fileChooser.showOpenDialog(null);

		if(ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "파일이 로드되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			String path = fileChooser.getSelectedFile().toString();
			paintImage = ImageIO.read(new File(fileChooser.getSelectedFile().toPath() + ".png"));		
		} catch (IOException ox) {
			// TODO: handle exception
			ox.printStackTrace();
		}
    	
    }


	
}

class PanelB extends JPanel {
	public static Object button6;
	FigureEditorFrame firstFrame;

    PanelB(FigureEditorFrame frame) {
    	this.firstFrame = frame;
    	setBackground(Color.BLUE);
        setLayout(new GridLayout(7,1,5,5));

		JButton button1 , button2 , button3 , button4, button5 , button6 , button7;
		MyActionListener listner = new MyActionListener();
        button1 = new JButton("사각");
		button2 = new JButton("직선");
		button3 = new JButton("타원");
		button4 = new JButton("복사");
		button5 = new JButton("삭제");
		button6 = new JButton("저장");
		button7 = new JButton("불러오기");

        button1.addActionListener(listner);
		button2.addActionListener(listner);
		button3.addActionListener(listner);
		button4.addActionListener(listner);
		button5.addActionListener(listner);
		button6.addActionListener(listner);
		button7.addActionListener(listner);

        add(button1);
        add(button2);      
        add(button3);       
        add(button4);  
        add(button5);
		add(button6);
        add(button7);
        
        
        
        
        
    }
    

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCmd = e.getActionCommand();
            firstFrame.pa.selButton = actionCmd; 
            if(e.getActionCommand().equals("저장"))				//저장버튼을 눌렀을 때
            	PanelA.save();									//저장 함수 실행
            else if(e.getActionCommand().equals("불러오기"))		//불러오기 버튼을 눌렀을 때
            	PanelA.load();									//로드 함수 실행
  
            
        }

    }
}

class PanelC extends JPanel {
	PanelC(FigureEditorFrame frame) {
		//PanelB를 포함
    	//판넬A에서 판넬B의 값을 사출력시키기 때문에 판넬A에 판넬B를 넣어준다.
        add(new PanelB(frame));
    }
}

