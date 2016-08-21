package fr.orion78.numberCrosswordHelper;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ViewMain extends JFrame {
	private static final long serialVersionUID = 7663630440914296149L;
	
	private static final DocumentListener al = new DocumentListener() {
		@Override
		public void insertUpdate(DocumentEvent e) {func();}
		@Override
		public void removeUpdate(DocumentEvent e) {func();}
		@Override
		public void changedUpdate(DocumentEvent e) {func();}
		
		private void func() {
			int nb, cells;
			try{
				nb = Integer.parseInt(tf1.getText());
				cells = Integer.parseInt(tf2.getText());
				
				if(nb < 1 || cells < 1){
					throw new NumberFormatException();
				}
			} catch(NumberFormatException ex){
				jta.setText("");
				return;
			}

			List<int[]> l = Solver.solve(nb, cells);
			
			if(l.size() == 0){
				jta.setText("None");
			} else {
				StringBuilder sb = new StringBuilder();
				for(int[] elem : l){
					sb.append(Arrays.toString(elem));
					sb.append("\n");
				}
				jta.setText(sb.toString());
			}
		}
	};
	private static final JTextField tf1 = new JTextField(10);
	private static final JTextField tf2 = new JTextField(10);
	private static final JTextArea jta = new JTextArea(30,0);
	private static final ViewMain view = new ViewMain();

	public ViewMain() {
		super("Helper");

		Font f = new Font(null, Font.PLAIN, 24);
        tf1.setFont(f);
		tf2.setFont(f);
		jta.setFont(f);
        
		JPanel pane = new JPanel(new GridBagLayout());
		this.setContentPane(pane);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,20,10,20);
		
		
		JLabel label = new JLabel("Number : ");
		label.setFont(f);
		pane.add(label, c);
		
		c.gridx = 1;
		tf1.getDocument().addDocumentListener(al);
		pane.add(tf1, c);
		
		c.gridx = 2;
		label = new JLabel("Cells : ");
		label.setFont(f);
		pane.add(label, c);
		
		c.gridx = 3;
		tf2.getDocument().addDocumentListener(al);
		pane.add(tf2, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 10;
		jta.setEditable(false);
		pane.add(jta,c);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
	}
	
	public static void main(String[] args) {
		view.setVisible(true);
	}
}
