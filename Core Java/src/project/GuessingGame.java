package project;
import javax.swing.JFrame;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import java.awt.ComponentOrientation;
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.TextField;
import java.awt.Dimension;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Cursor;

public class GuessingGame extends JFrame  {
	private JLabel lblOutput;
	private JFormattedTextField textGuess;
	private int theNumber;
	private JLabel lblHiItsGuessig;
	private int tries=0;
	public void checkGuess()
	{
		String guessTxt = textGuess.getText();
		String message = "";
		int guess;
		
		try{
		guess = Integer.parseInt(guessTxt);
		tries++;
		/*if ((guess+10) < theNumber && (guess-10) > theNumber){
			message = guess + " is around!!! guess again..";
			lblOutput.setText(message);
		}
		else*/ if(guess < theNumber){
			message = guess + " is too low!!! guess again..";
			lblOutput.setText(message);
			}
		
		else if(guess > theNumber){
			message = guess + " is too High!!! guess again..";
			lblOutput.setText(message);
			}
		
		else{
			message = guess + "is correct. You win!!  " + tries+" trials!!!\n Let's play again!";
			lblOutput.setText(message);
			newGame();
		}
	}
		catch (Exception e) {
			lblOutput.setText("Enter only a whole no. between 1 to 100 !!!");
		}
		
		finally {	
			textGuess.requestFocus();
			textGuess.selectAll();
			}
		}
	public void newGame(){
		theNumber = (int)(Math.random()*100 + 1);
		tries=0;
	}
	
	public GuessingGame() {
		getContentPane().setBackground(new Color(255, 218, 185));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		getContentPane().setLayout(null);
		
		lblHiItsGuessig = new JLabel("Guessing Game (*_*)");
		lblHiItsGuessig.setBounds(10, 71, 414, 36);
		lblHiItsGuessig.setFont(new Font("Eras Demi ITC", Font.BOLD, 20));
		lblHiItsGuessig.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblHiItsGuessig);
		
		JButton btnGuess = new JButton("GuEess!!!");
		btnGuess.setFont(new Font("Malgun Gothic", Font.BOLD, 13));
		btnGuess.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuess.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnGuess.setForeground(new Color(0, 0, 0));
		btnGuess.setBackground(new Color(100, 149, 237));
		btnGuess.setIconTextGap(5);
		btnGuess.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnGuess.setToolTipText("click!!!");
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		btnGuess.setBounds(284, 216, 102, 23);
		getContentPane().add(btnGuess);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 182, 193));
		panel.setBounds(10, 149, 414, 36);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Enter the number between 1 and 100 :");
		lblNewJgoodiesLabel.setFont(new Font("Century", Font.ITALIC, 14));
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel.setBounds(32, 10, 267, 14);
		panel.add(lblNewJgoodiesLabel);
		
		textGuess = new JFormattedTextField();
		textGuess.setBounds(304, 9, 64, 20);
		panel.add(textGuess);
		textGuess.setBackground(new Color(127, 255, 212));
		textGuess.setToolTipText("Enter the No.");
		textGuess.setRequestFocusEnabled(true);
		textGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		textGuess.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblOutput = DefaultComponentFactory.getInstance().createLabel("Enter the no. above that u wanna GuEss.");
		lblOutput.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblOutput.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(10, 250, 414, 30);
		getContentPane().add(lblOutput);
		
		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("BuGgEd");
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.getContentPane().add(new Menu());
				f.pack();
				f.setLocation(25, 0);
				f.setSize(250,290);
				f.setVisible(true);
			}
		});
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenu.setForeground(new Color(0, 0, 0));
		btnMenu.setToolTipText("Go back to Main Menu");
		btnMenu.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnMenu.setBounds(335, 11, 89, 23);
		getContentPane().add(btnMenu);
	}

		public JLabel getLblHiItsGuessig() {
		return lblHiItsGuessig;
	}
}
