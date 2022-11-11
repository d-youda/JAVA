import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EventIListenerTest extends JFrame {
	JButton btn;
	EventIListenerTest() {
        setTitle("Action 이벤트 리스너 예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        btn = new JButton("Action");
        //btn.addActionListener(new MyActionListener());
        btn.addActionListener(new ActionListener() {
        	//정의와 함께 생성-> 이름 없는 클래스
			@Override
			public void actionPerformed(ActionEvent e) {
				 //JButton b = (JButton)e.getSource();
		        if(btn.getText().equals("Action"))
		            btn.setText("액션");
		        else
		        	btn.setText("Action");
				
			}}
        );
        c.add(btn);
        setSize(250, 120);
        setVisible(true);

    }
	

    public static void main(String[] args) {
        new EventIListenerTest();
    }
    
}