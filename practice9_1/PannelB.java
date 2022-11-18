package practice9_1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * 두 번째 패널
 * 파란 배경, 배치관리자 3행1열
 * hGap , vGap 각각 5로 설정
 * 3개의 버튼 컴포넌트
 * 버튼 클릭하면 도형 출력하게 함
 * @author 유다현
 */
public class PannelB extends JPanel{
	JButton button1 , button2 , button3;
	PannelA pa;
	//생성자
	public PannelB(PannelA pa){
		//pannelA를 받아옴
		this.pa = pa;
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
		
		//버튼들 모두 마우스 리스너 추가
		ActionListener listener = new MyActionListener();
//		addMouseMotionListener(new MyMouseMotinListener());
		button1.addActionListener(listener);
		button2.addActionListener(listener);
		button3.addActionListener(listener);
		
	}
	class MyActionListener implements ActionListener{
		//마우스로 버튼 클릭할 경우, 버튼에 있는 text가 label 자리에 출력되도록 함
		@Override
		public void actionPerformed(ActionEvent e) {
//			System.out.println(e.getActionCommand());
			pa.label.setText(e.getActionCommand());
		}
	}
}
