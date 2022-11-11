package practice9_1;

import java.awt.*;

import javax.swing.*;

/**
 * 두 번째 패널
 * 파란 배경, 배치관리자 3행1열
 * hGap , vGap 각각 5로 설정
 * 3개의 버튼 컴포넌트
 * @author 유다현
 */
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
	}
}
