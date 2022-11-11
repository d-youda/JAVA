package practice9_1;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

/**
 * 첫 번쨰 패널
 * 노란 배경, 컴포넌트 하나
 * @author 유다현
 *
 */
public class PannelA extends JPanel{
	JLabel label;

	//panel 생성자
	public PannelA() {
		setBackground(Color.YELLOW);//배경색 설정

		//하나의 컴포넌트 : label
		label = new JLabel("여기가 그래픽 객체를 그리는 곳입니다");
		add(label);//panel에 label 추가
		//마우스 모션 리스너 추가하여 드래그를 인식할 수 있도록 함
		addMouseMotionListener(new mouse());
	}
	
	//마우스로 글씨 드래그해서 움직이는 이벤트
	class mouse implements MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			label.setLocation(x,y);
			add(label);
		}
		
		public void mousePressed(MouseEvent e) {
		}

		//MouseMOtionListener 안에 있는 method들을 사용하지 않더라도 빈 함수로라도 선언은 해주어야 함

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	

}