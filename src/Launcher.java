import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(final String[] args) {
		Scanner sc = new Scanner (System.in);
		int n;
		System.out.print("Puzzle n X n, n =");
        	n=Integer.parseInt(sc.nextLine());
		
	    	SwingUtilities.invokeLater(() -> {
	        	final JFrame frame = new JFrame();
	        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	frame.setTitle("Sliding Puzzle");
	        	frame.setResizable(false);
	        	frame.add(new Main(4, 550, 30), BorderLayout.CENTER);
	      		frame.pack();
	      		frame.setLocationRelativeTo(null);
	      		frame.setVisible(true);
	    	});
	}   
}
