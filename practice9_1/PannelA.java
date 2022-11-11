package practice9_1;

import java.awt.Color;

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
	}
}