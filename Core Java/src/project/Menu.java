package project;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;

public class Menu extends JPanel{
	public Menu() {
		
		JButton btnNewButton = new JButton("Bubble Draw");
		btnNewButton.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame("Draw Bubbles");
				//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.getContentPane().add(new BubbleJPanel());
				f.pack();
				f.setLocation(25, 0);
				f.setVisible(true);
			}
		});
		setLayout(null);
		btnNewButton.setBounds(55, 92, 117, 23);
		add(btnNewButton);
		
		JButton btnGuessingGame = new JButton("Guessing Game");
		btnGuessingGame.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnGuessingGame.setBackground(new Color(238, 130, 238));
		btnGuessingGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuessingGame game = new GuessingGame();
				game.newGame();
				game.setSize(new Dimension(450, 320));
				game.setVisible(true);
			}
			
		});
		btnGuessingGame.setBounds(46, 141, 135, 23);
		add(btnGuessingGame);
		
		JButton btnEncoderDecoder = new JButton("Encoder / Decoder");
		btnEncoderDecoder.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnEncoderDecoder.setBackground(new Color(30, 144, 255));
		btnEncoderDecoder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Encoder / Decoder");
				//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new SecretMessageGUI());
				frame.pack();
				frame.setVisible(true);
			}
		});
		btnEncoderDecoder.setBounds(40, 189, 145, 23);
		add(btnEncoderDecoder);
		
		JLabel lblWelcomeToBugged = DefaultComponentFactory.getInstance().createLabel("Welcome To BuGgED");
		lblWelcomeToBugged.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
		lblWelcomeToBugged.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToBugged.setBounds(10, 27, 207, 23);
		add(lblWelcomeToBugged);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("BuGgEd");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new Menu());
		f.pack();
		f.setLocation(25, 0);
		f.setSize(245,290);
		f.setVisible(true);
	}
}
