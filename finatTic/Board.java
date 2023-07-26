package finatTic;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.TimerTask;

import javax.swing.*;



public class Board extends JPanel implements ActionListener
{
	 JButton[] buttons;


	protected int  winButton, count;
	private String[] lines;
	 String ai, challenger, winner;
	 
	 Score scoreInstance;
	 Display displayInstance;

	MiniMax_Algo algo;
	
	public Board()
	{
	}


	public Board(boolean x)
	{

		algo = new MiniMax_Algo();
		
		challenger= x? "X":"O";
		
		ai = challenger.equals("X")? "O":"X";
		
		buttons = new JButton[9];

		lines = new String[8];

		winner = null;
		
		count = challenger.equals("O")? -1 : 1;
 
		setLayout(new GridLayout(3,3,0,0));

		setPreferredSize(new Dimension(650,470));
		setMinimumSize(new Dimension(650,470));

		for (int i = 0; i < buttons.length; i++)
		{
			buttons[i] = new JButton("");
			buttons[i].setFocusable(false);
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setFont(new Font("HYSWLongFangSong", Font.BOLD, 180));
			buttons[i].addActionListener(this);
			add(buttons[i]);
		}
		if(challenger.equals("O")) {

		ai_move();
		} 
	}
	public void ai_move()
	{
		count *= -1;
		MiniMax_Algo algo = new MiniMax_Algo();
		int bestmove = ai.equals("X")?algo.best_Move(buttons,true): algo.best_move_o(buttons);
		buttons[bestmove].setForeground(Color.RED);
		buttons[bestmove].setText(ai);
	}
	public void increment_score(String computer)
	{
		String score;
		
		if (computer.equalsIgnoreCase("computer")) {
			scoreInstance.computer_score++;
			
			 score = scoreInstance.ai_score.getText();
			score = score.substring(0, score.length()-1) + scoreInstance.computer_score;
			scoreInstance.ai_score.setText(score);
			
			scoreInstance.ai_score.setText(score);
		} else 
		{
			scoreInstance.challneger_score++;
			score = scoreInstance.human_score.getText();
			 score = score.substring(0, score.length()-1) + scoreInstance.challneger_score;
			 scoreInstance.human_score.setText(score);
			 
		}
	}
	public void clear_board( Board boardInstance)
	{
		
		for (int i = 0; i < 9; i++)
		{
			
			boardInstance.buttons[i].setText("");
			boardInstance.buttons[i].setBackground(Color.WHITE);
			boardInstance.buttons[i].addActionListener(this);
		}
		if(challenger.equals("O")) {
		ai_move();
		count = 1;
		}
		
		scoreInstance.getParent().revalidate();
		displayInstance.text.setText("Tic-Tac-Toe");
		displayInstance.repaint();
	}

	public int check_winner()
	{

		lines[0] = buttons[0].getText() + buttons[1].getText() + buttons[2].getText();
		lines[1] = buttons[3].getText() + buttons[4].getText() + buttons[5].getText();
		lines[2] = buttons[6].getText() + buttons[7].getText() + buttons[8].getText();
		lines[3] = buttons[0].getText() + buttons[3].getText() + buttons[6].getText();
		lines[4] = buttons[0].getText() + buttons[4].getText() + buttons[8].getText();
		lines[5] = buttons[2].getText() + buttons[5].getText() + buttons[8].getText();
		lines[6] = buttons[2].getText() + buttons[4].getText() + buttons[6].getText();
		lines[7] = buttons[1].getText() + buttons[4].getText() + buttons[7].getText();
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].equalsIgnoreCase("XXX")) {
				winner = "X";
				winButton = i;
				displayInstance.text.setText("X Won The Game!");
				displayInstance.repaint();
				return  10;
			} else if (lines[i].equalsIgnoreCase("OOO")) {
				displayInstance.text.setText("O Won The Game!");
				displayInstance.repaint();
				winButton = i;
				winner = "O";
				return  -10;
			}
		}
		boolean isEmpty = false;
		for (JButton button:buttons)
		{
			if (button.getText().isEmpty())
			{
				isEmpty = true;
				break;
			}
		}if(!isEmpty) displayInstance.text.setText("It's A Draw!");
		return 0;	

	}
	public void win_buttons(int winner)
	{


		stop_game();

		if (winner == 0){
			for (int j = 0; j < 3; j++) {
				// change the color of the winning buttons
				buttons[j].setBackground(Color.YELLOW); 
			}
		} else if (winner == 1 ) {
			for (int j = 3; j < 6; j ++ ) {
				buttons[j].setBackground(Color.YELLOW);
			}
		} else if (winner == 2 ) {
			for (int j = 6; j < 9; j ++ ) {
				buttons[j].setBackground(Color.YELLOW);
			}
		} else if (winner == 3 ) {
			for (int j = 0; j < 7; j +=3 ) {
				buttons[j].setBackground(Color.YELLOW);
			}
		} else if (winner == 4 ) {
			for (int j = 0; j < 9; j +=4 ) {
				buttons[j].setBackground(Color.YELLOW);
			}
		} else if (winner == 5 ) {
			for (int j = 2; j < 9; j +=3 ) {
				buttons[j].setBackground(Color.YELLOW);
			}
		} else if (winner == 6 ) {
			for (int j = 2; j <= 6; j +=2 ) {
				buttons[j].setBackground(Color.YELLOW);
			}
		} else if (winner == 7 ) {
			for (int j = 1; j <= 7; j +=3 ) {
				buttons[j].setBackground(Color.YELLOW);
			}
		}
	}
	public void stop_game()
	{
		for (int i = 0; i < 9; i++)
		{
			buttons[i].removeActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 9; i++) {
			if (e.getSource() == buttons[i] && count > 0) {
				if (buttons[i].getText().isEmpty()) {
					buttons[i].setText(challenger);
					buttons[i].setForeground(Color.GREEN);
					count *= -1;
					
					break;
				}
			}
		}
		int winner = check_winner();
		if (winner != 0)
		{
			win_buttons(winButton);
			new Display(challenger);
			
			increment_score("player");
		

			getParent().revalidate();
			getParent().repaint();;
			return;
		}
		

		if (count < 0)
		{
			try {
			ai_move();
			}catch(Exception e1) {}
	

			winner = check_winner();
			if (winner != 0)
			{

				win_buttons(winButton);
				new Display(ai);  //could be creating an instance of a new display instead of the one 
				//you already have
				
				increment_score("computer");

			}
		}

	}
	

}