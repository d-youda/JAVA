package practice9_1;

import java.awt.*;
import javax.swing.*;
/**
 * 판넬들 배치하는 클래스
 * @author 유다현
 *
 */
public class FigureEditor extends JFrame{
	public FigureEditor(){
		//출력되는 pannel의 이름 설정
		setTitle("MyFrame");
		//전체 판넬 사이즈
		setSize(500,300);
		
		//큰 판넬에 작은 판넬들 부착
		add(new PannelA(),BorderLayout.CENTER);
		add(new PannelC(), BorderLayout.WEST);
		
		//panel 출력하기(보이도록!)
		setVisible(true);
		
	}
}
