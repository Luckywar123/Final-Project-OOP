// impor class
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
//deklarasi class
public class MainFrame {
    
    public MainFrame(){
        // Mebuat obyek warna orange dari kelas Color
        
        Color orangeColor = new Color(255, 175, 175);
        Container wadah = frame.getContentPane();
        wadah.setBackground(orangeColor);
        
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
}


