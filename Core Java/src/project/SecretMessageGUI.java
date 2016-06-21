package project;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;

public class SecretMessageGUI extends JPanel {
	private JTextArea txtIn;
	private JFormattedTextField txtKey;
	private JButton btnEncoderdecoder;
	private JTextArea txtOut;
	private JSlider slider;
	private int theRandomKey;
	private char spaceValue;
	private JButton btnMenu;
	
	
	public String encode(String message, int k)
	{
		String out = "";
		char key = (char) k;
		for (int x =0; x< message.length(); x++)
		{
			char in = message.charAt(x);
			
			if(in >= 'A' && in <= 'Z')
			{
				in += key;
				if(in > 'Z')
					in -= 26;
				if(in < 'A')
					in += 26;
				}
			if(in >= 'a' && in <='z')
			{
				in += key;
				if(in > 'z')
					in -= 26;
				if(in < 'a')
					in += 26;
				}
			if(in >= '0' && in <= '9')
			{
				in += (k % 10);
				if(in > '9')
					in -= 10;
				if(in < '0')
					in += 10;
			}
			out += in;
		}
		return out;
	}
	
	
	public SecretMessageGUI() {
		setBackground(new Color(152, 251, 152));
		setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setWrapStyleWord(true);
		txtIn.setToolTipText("Enter the Text!!");
		txtIn.setLineWrap(true);
		txtIn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtIn.requestFocus();
				txtIn.selectAll();
			}
		});
		txtIn.setText("ENTER the TEXT YOU WISH TO ENCRYPT!!! and then enter the KEy between 1 to 26!!");
		txtIn.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		txtIn.setBounds(10, 55, 430, 84);
		add(txtIn);
		
		JFormattedTextField txtKey = new JFormattedTextField();
		txtKey.setText("7");
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setToolTipText("enter the KEy between 1 to 26!!");
		txtKey.setBackground(new Color(255, 127, 80));
		txtKey.setBounds(357, 150, 32, 20);
		add(txtKey);
		
		btnEncoderdecoder = new JButton("ENCODE/DECODE");
		btnEncoderdecoder.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnEncoderdecoder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEncoderdecoder.setToolTipText("click here!!!");
		btnEncoderdecoder.setBackground(new Color(240, 230, 140));
		btnEncoderdecoder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String message = txtIn.getText();
			int key = Integer.parseInt(txtKey.getText());
			String output = encode(message,key);
			txtOut.setText(output);
			}
		});
		btnEncoderdecoder.setBounds(267, 181, 145, 20);
		add(btnEncoderdecoder);
		
		txtOut = new JTextArea();
		txtOut.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtOut.setWrapStyleWord(true);
		txtOut.setToolTipText("Copy this..");
		txtOut.setLineWrap(true);
		txtOut.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e){
				txtOut.requestFocus();
				txtOut.selectAll();
			}
		});
		txtOut.setText("eNCODED tEXt!!!");
		txtOut.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		txtOut.setForeground(new Color(64, 224, 208));
		txtOut.setBounds(10, 212, 430, 77);
		add(txtOut);
		setPreferredSize(new Dimension(450,300));
		
		slider = new JSlider();
		slider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setToolTipText("slide here!!!");
		slider.setBackground(new Color(175, 238, 238));
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setValue(7);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			txtKey.setText("" + slider.getValue() );
			String message = txtIn.getText();
			int key = Integer.parseInt(txtKey.getText());
			String output = encode(message,key);
			txtOut.setText(output);			
			}
		});
		slider.setBounds(10, 150, 200, 39);
		add(slider);
		
		JButton btnRandomKey = new JButton("RANDOM KEY");
		btnRandomKey.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnRandomKey.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRandomKey.setToolTipText("click here!!!");
		btnRandomKey.setBackground(new Color(255, 182, 193));
		btnRandomKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theRandomKey = (int)(Math.random()*25+1);
				txtKey.setValue(theRandomKey);
			}
		});
		btnRandomKey.setBounds(220, 150, 127, 23);
		add(btnRandomKey);
		
		btnMenu = new JButton("Menu");
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenu.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnMenu.setToolTipText("Go back to Main Menu");
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
		btnMenu.setBounds(341, 11, 89, 21);
		
		add(btnMenu);
	}


}
