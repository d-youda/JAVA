import java.awt.*;
import javax.swing.*;

public class FigureEditor extends JFrame{
    FigureEditor(){
        setTitle("My Frame");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new PanelA(),BorderLayout.CENTER);
        add(new PanelC(),BorderLayout.WEST);
        setVisible(true);

    }
    class PanelA extends JPanel {
        PanelA(){
            setBackground(Color.YELLOW);
            add(new JLabel("여기가 그래픽 객체를 그리는 곳입니다."));
        }
    }
    class PanelB extends JPanel{
        PanelB(){
            setBackground(Color.BLUE);
            setLayout(new GridLayout(3,1,5,5));
            add(new JButton("사각"));
            add(new JButton("직선"));
            add(new JButton("타원"));
        }
    }
    class PanelC extends JPanel{
        PanelC(){
            add(new PanelB());
        }
        
    }
    public static void main(String[] args) {
        new FigureEditor();
    }
}
