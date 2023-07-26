package finatTic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class FinalFrame extends JPanel {
	
	JPanel board_and_score;
	
	static boolean isX;
	
	static Board boardInstance;
	
	Display displayinstance;
	
	Score socreInstance;
	
	public FinalFrame(boolean isX)
	{
		this.isX = isX;
	}
	
	public FinalFrame()
	{
		socreInstance = new Score();
		boardInstance = new Board(isX);
		displayinstance = new Display(null);
		displayinstance.boardinstance = boardInstance;
		socreInstance.boardInstance = boardInstance;
		boardInstance.scoreInstance = socreInstance;
		boardInstance.displayInstance = displayinstance;
		 
		board_and_score = new JPanel();
		board_and_score.setPreferredSize(new Dimension(1100, 600));
		board_and_score.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 0);
		board_and_score.add(socreInstance, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 0);
		board_and_score.add(boardInstance, gbc);

		board_and_score.setBackground(Color.WHITE);
		
		setSize(new Dimension(1200,1000));
		
		setBackground(Color.WHITE);
		
		setLayout(new GridBagLayout());
		 gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(displayinstance, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(board_and_score, gbc);
		
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setSize(new Dimension(1200,900));
		
		frame.setLocationRelativeTo(null);
		
		frame.setTitle("TIc-Tac_Toe");
		
		frame.getContentPane().add(new FinalFrame());
		frame.setLocationRelativeTo(null);		
		
		frame.setVisible(true);

	}

}
