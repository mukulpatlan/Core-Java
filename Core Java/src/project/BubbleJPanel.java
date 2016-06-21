package project;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JList;
import java.awt.Font;
import java.awt.Cursor;

public class BubbleJPanel extends JPanel {

	private ArrayList<Bubble> bubbleList = new ArrayList<Bubble>();
	private int size = 30;
	private Timer time;
	private final int DELAY = 30;
	private JButton btnClear,btnUpdate;
	private JTextField txtFrame,txtDot;
	private JLabel labelframe,labeldot;
	private JCheckBox cbox,cboxburst,cboxpause;
	private JButton btnMenu;
	
	public BubbleJPanel(){
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(218, 112, 214));
				
		labeldot = new JLabel("Dot Size: ");
		labeldot.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		panel.add(labeldot);
		
		txtDot = new JTextField ("30");
		txtDot.setFont(new Font("Malgun Gothic", Font.PLAIN, 10));
		txtDot.setColumns(3);
		txtDot.setHorizontalAlignment(SwingConstants.CENTER);
		txtDot.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				txtDot.requestFocus();
				txtDot.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}	
		});
		panel.add(txtDot);
		
		labelframe = new JLabel("Frame speed(fps): ");
		labelframe.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		panel.add(labelframe);
		
		txtFrame = new JTextField("30");
		txtFrame.setFont(new Font("Malgun Gothic", Font.PLAIN, 10));
		txtFrame.setColumns(3);
		txtFrame.setHorizontalAlignment(SwingConstants.CENTER);
		txtFrame.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				txtFrame.requestFocus();
				txtFrame.selectAll();
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}	
		});
		panel.add(txtFrame);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int fSize=Integer.parseInt(txtDot.getText());
				int fps = Integer.parseInt(txtFrame.getText());
				size = fSize;
				time.setDelay(1000/fps);
				
				repaint();
			}
		});
		panel.add(btnUpdate);
		
		cbox = new JCheckBox("Group Bubbles");
		cbox.setBackground(new Color(240, 230, 140));
		cbox.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		panel.add(cbox);
		
		cboxburst = new JCheckBox("Bursty Bubbles");
		cboxburst.setBackground(new Color(176, 224, 230));
		cboxburst.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		panel.add(cboxburst);
		
		cboxpause = new JCheckBox("Pause Bubbles");
		cboxpause.setBackground(new Color(154, 205, 50));
		cboxpause.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		cboxpause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(cboxpause.isSelected())
					time.stop();
				else
					time.start();
			}
		});
		panel.add(cboxpause);
		
		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
		btnClear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				bubbleList = new ArrayList<Bubble>();
				repaint();
			}
		});
		panel.add(btnClear);
		
		add(panel);
		
		btnMenu = new JButton("Menu");
		btnMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenu.setFont(new Font("Malgun Gothic", Font.BOLD, 12));
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
		btnMenu.setToolTipText("Go back to Main Menu");
		panel.add(btnMenu);
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		time = new Timer(DELAY, new BubbleListener()); 
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(1300,600));
		
		time.start();
	}
	
	public void paintComponent(Graphics page){
		super.paintComponent(page);
				
		for(Bubble bubble: bubbleList){
			page.setColor(bubble.color);
			page.fillOval(bubble.x - bubble.size/2, 
							bubble.y - bubble.size/2, 
							bubble.size, bubble.size);
		}
		
		//page.setColor(Color.RED);
		//page.drawString("Count: " + bubbleList.size(), 5, 15);
		
	}
	
	public class BubbleListener implements MouseListener,MouseMotionListener,MouseWheelListener,ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(cboxburst.isSelected())
				time.stop();
			bubbleList.add(new Bubble(e.getX(), e.getY(), size));
			repaint();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(cboxburst.isSelected())
				time.start();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		bubbleList.add(new Bubble(e.getX(), e.getY(), size));
					
		if(cbox.isSelected())
		{
			bubbleList.get(bubbleList.size()-1).xspeed = bubbleList.get(bubbleList.size()-2).xspeed;
			bubbleList.get(bubbleList.size()-1).yspeed = bubbleList.get(bubbleList.size()-2).yspeed;
		}
		repaint();
		
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
					
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			size += e.getWheelRotation();
			txtDot.setText("" + size);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			for(Bubble bubble:bubbleList)
			{
				bubble.update();
			}
			repaint();
		}

	
	}
	
	
	private class Bubble {
		public int x;
		public int y;
		public int size;
		public Color color;
		public int xspeed;
		public int yspeed;
		private final int MAX_SPEED = 5;
		
		public Bubble(int newX, int newY, int newSize){
			x = newX;
			y = newY;
			size = newSize;
			color = new Color((float)Math.random(),
								(float)Math.random(),
								(float)Math.random(),
								(float)Math.random());
			xspeed = (int)(Math.random()*MAX_SPEED*2-MAX_SPEED);
			yspeed = (int)(Math.random()*MAX_SPEED*2-MAX_SPEED);
		
			if(xspeed==0 && yspeed ==0)
			{
				xspeed = 1;
				yspeed = 1;
			}
		}
		
	public void update()
	{
		x += xspeed;
		y += yspeed;
		
		if(x < 0 || x > getWidth() )
			xspeed = -xspeed;
		if(y < 0 || y > getHeight() )
			yspeed = -yspeed;
	}
	}
}
