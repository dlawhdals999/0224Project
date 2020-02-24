package quiz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreFrame extends JFrame {

	JPanel Panel1 = new JPanel();
	JPanel Panel2 = new JPanel();
	JPanel Panel3 = new JPanel();

	public ScoreFrame(int an, int wr) {
		ScorePanel scorePanel = null;
		getContentPane().add(Panel1);
		Panel1.setLayout(null);
		Panel1.add(Panel2);
		Panel1.add(Panel3);
		Panel2.setBounds(0, 0, 630, 100);
		double score = an/(an+wr+0.0)*100;
		if (score > 95) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu1.png")
							.getImage());

		} else if (score > 90) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu2.png")
							.getImage());
		} else if (score > 85) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu3.png")
							.getImage());
		} else if (score > 80) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu4.png")
							.getImage());
		} else if (score > 70) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu5.png")
							.getImage());
		} else if (score > 65) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu6.png")
							.getImage());
		} else if (score > 60) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu7.png")
							.getImage());
		} else if (score > 55) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu8.png")
							.getImage());

		} else if (score > 50) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu9.png")
							.getImage());

		} else if (score > 45) {
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu10.png")
							.getImage());

		} else{
			scorePanel = new ScorePanel(
					new ImageIcon(".\\src\\images\\pengsu11.png")
							.getImage());
		}
		Panel3.add(scorePanel);
		Panel3.setBounds(0, 90, 400, 400);
		Panel3.setLocation(0, 90);
		Panel3.setSize(scorePanel.getDim());

		scorePanel.setLayout(new FlowLayout());

		JLabel scoreLabel = new JLabel();
		String label = "점수 : "+ String.format("%.2f", score);
		scoreLabel.setText(label);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setVerticalAlignment(JLabel.BOTTOM);
		scoreLabel.setFont(new Font("D2coding", Font.BOLD, 40));
		Panel2.setLayout(null);

		Panel2.add(scoreLabel);
		scoreLabel.setBounds(0, 0, 630, 100);
		scoreLabel.setOpaque(true);
		scoreLabel.setBackground(Color.white);

		setSize(new Dimension(132, 431));
		setPreferredSize(scorePanel.getDim());
		pack();
		setVisible(true);
		setResizable(false);

	}

}