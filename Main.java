import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.*;

public class Main {

	public static int pl1y = 210;
	public static int pl2y = 210;
	public static int ps1;
	public static int ps2;
	public static int x = 487;
	public static int y = 287;
	public static int xpluss = 1;
	public static int ypluss = 1;
	public static int loop;

	public static JPanel player2;
	public static JLabel score1;
	public static JLabel score2;
	public static JPanel player1;
	public static void start() {

		player1 = new JPanel();
		player1.setBackground(Color.white);// Тут можете сменить цвет первого игрока
		player1.setBounds(0, pl1y, 17, 80);

		player2 = new JPanel();
		player2.setBackground(Color.white); // Тут второго
		player2.setBounds(970, pl2y, 17, 80);

		JPanel center = new JPanel();
		center.setBackground(Color.white);
		center.setBounds(499, 0, 2, 500);

		score1 = new JLabel();
		score1.setText(String.valueOf(ps1));
		score1.setForeground(Color.white);
		score1.setBounds(400, 0, 100, 50);
		score1.setFont(new Font("Arial", Font.BOLD, 50));

		score2 = new JLabel();
		score2.setText(String.valueOf(ps2));
		score2.setForeground(Color.white);
		score2.setBounds(570, 0, 100, 50);
		score2.setFont(new Font("Arial", Font.BOLD, 50));

		JFrame frame = new JFrame();
		frame.setTitle("Pong Game");
		frame.setVisible(true);
		frame.setSize(1000, 500);
		frame.setResizable(false);
		frame.add(player1);
		frame.add(player2);
		frame.add(score1);
		frame.add(score2);
		frame.add(center);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.black);// Тут цвет фона

		frame.addKeyListener(new KeyAdapter() { 
		@Override public void keyPressed(KeyEvent e) { 
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (pl1y < 380) {
				pl1y += 15;
				player1.setBounds(0, pl1y, 17, 80);
			}
		} 
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (pl1y > 5) {
				pl1y -= 15;
			    player1.setBounds(0, pl1y, 17, 80);
			}
			
		} 

		if (e.getKeyCode() == KeyEvent.VK_S) {
			if (pl2y < 380) {
				pl2y += 15;
				player2.setBounds(970, pl2y, 17, 80);
			}
		} 

		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (pl2y > 5) {
				pl2y -= 15;
			    player2.setBounds(970, pl2y, 17, 80);
			}
			
		} 
	} 
});

		JPanel ball = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.blue); // Тут цвет шара
				g.fillOval(x, y, 25, 25);
			}
		};
		ball.setBounds(0, 0, 100000, 100000);
		ball.setOpaque(false);
		ball.setLayout(null);

		frame.add(ball);

		Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				//System.out.println(x);
				x += xpluss;
				y += ypluss;
				ball.repaint();
				operator();
			}
		};
		timer.scheduleAtFixedRate(task, 0, 4);   //   Тут скорость движения шара
	}
	public static void main(String[] args) {
		start();
	}

	public static void operator() {

		if (y < 385) {   // Это бот. Если не нужен, то уберите. (Его невозможно выиграть)
			pl2y = y;    // Это бот
			player2.setBounds(970, pl2y, 17, 80);   // Это бот
		}   // Это бот

		if (y > 440) {
			ypluss = -1;
		}

		if (y < 1) {
			ypluss = 1;
		}

		if (x <= 8) {
			if (y >= pl1y - 4 && y <= pl1y + 80) {
				xpluss = 1;
			}
			else {
				xpluss = 1;
				ps2 += 1;
				x = 487;
				y = 287;
				score2.setText(String.valueOf(ps2));
			}
		} 

		if (x > 940) {
			if (y >= pl2y - 4 && y <= pl2y + 81) {
				xpluss = -1;
			}
			else {
				xpluss = -1;
				ps1 += 1;
				x = 487;
				y = 287;
				score1.setText(String.valueOf(ps1));
			}
		}
	}
}