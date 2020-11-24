import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public Main(){
        JFrame frame = new JFrame();    //window
        
        JPanel panel = new JPanel();    //layout di windownya
        panel.setBorder(BorderFactory.createEmptyBorder(300,300,300,300));  //border layout
        panel.setLayout(new GridLayout());  //layout
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("15 Puzzle");    //judul window
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
}
