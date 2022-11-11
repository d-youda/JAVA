package practice9_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import practice9_1.PannelB.MyActionListener;
/**
 * 판넬들 배치하는 클래스
 * 내부 클래스로 패널들 저장해서 값 출력함
 * @author 유다현
 *
 */
public class FigureEditor extends JFrame{
	PannelA pa;//클래스 내 어디서든 접근 가능!
	public FigureEditor(){
		//출력되는 pannel의 이름 설정
		setTitle("MyFrame");
		//전체 판넬 사이즈
		setSize(500,300);
		
		//큰 판넬에 작은 판넬들 부착
		pa = new PannelA();
		add(pa,BorderLayout.CENTER);
		add(new PannelC(), BorderLayout.WEST);
		
		//panel 출력하기(보이도록!)
		setVisible(true);
		
	}
	public class PannelA extends JPanel{
		JLabel label; //global하게 어느 클래스에서도 객체 사용 가능
		
		//panel 생성자
		public PannelA() {
			setBackground(Color.YELLOW);//배경색 설정
			
			//하나의 컴포넌트 : label
			label = new JLabel("여기가 그래픽 객체를 그리는 곳입니다");
			add(label);//panel에 label 추가
		}
	}
	
	public class PannelB extends JPanel{
		JButton button1 , button2 , button3;
		//생성자
		public PannelB(){
			//파란 배경
			setBackground(Color.BLUE);
			
			//버튼 레이아웃 설정
			setLayout(new GridLayout(3,1,5,5));
			
			//버튼 설정
			button1 = new JButton("사각");
			button2 = new JButton("직선");
			button3 = new JButton("타원");
			
			//버튼 추가
			add(button1);
			add(button2);
			add(button3);
			
			ActionListener listener = new MyActionListener();
			button1.addActionListener(listener);
			button2.addActionListener(listener);
			button3.addActionListener(listener);
		}
		class MyActionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println(e.getActionCommand());
				pa.label.setText(e.getActionCommand());
				
				
			}
			
		}
	}
	
	public class PannelC extends JPanel{
		public PannelC(){
			//판넬B 포함
			add(new PannelB());
		}
	}

		
}
