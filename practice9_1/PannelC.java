package practice9_1;

import javax.swing.*;

/**
 * 마지막 패널
 * 패널 B를 포함하는 패널
 * @author 유다현
 *
 */
public class PannelC extends JPanel{
	public PannelC(PannelA pa){
		//판넬B 포함,
		//판넬A에 판넬B에서의 값을 출력시키기 떄문에 판넬A를 판넬B에 넣어줌
		add(new PannelB(pa));
	}
}