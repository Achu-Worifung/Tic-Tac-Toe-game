package finatTic;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class Score extends JPanel implements ItemListener{
	
	JLabel human_score, ai_score, chose_player;
	
	int ai,human,  challneger_score, computer_score;
	
	 String challenger;
	 
	 static Board boardInstance;
	
	
	
	public JComboBox<String> position;
	
	public Score(String challenger)
	{
		this.challenger = challenger;
		
	}

	public Score()
	{
		challneger_score = 0;
		
		computer_score = 0;
		
		challenger = "O";
		ai = 0;
		
		human = 0;
		chose_player = new JLabel("chose_player: ");
		chose_player.setForeground(Color.BLACK);
		chose_player.setFont(new Font("times new romans", Font.PLAIN,15));
		position = new JComboBox<String>();

		position.addItem("O");
		position.addItem("X");

		position.setRenderer(new ComboBoxRenderer());
		
		position.addItemListener(this);
		
		human_score = new JLabel();
		
		ai_score = new JLabel();
		
		human_score = new JLabel("YOUR SCORE: " + challneger_score);
		human_score.setForeground(Color.BLACK);
		human_score.setFont(new Font("HYSWLongFangSong", Font.BOLD,40));
		
		ai_score = new JLabel("COMPUTER SCORE: " + computer_score);
		ai_score.setForeground(Color.BLACK);
		ai_score.setFont(new Font("HYSWLongFangSong", Font.BOLD,40));
		
		chose_player = new JLabel("Role: ");
		chose_player.setForeground(Color.BLACK);
		chose_player.setFont(new Font("HYSWLongFangSong", Font.BOLD,40));
		
		
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(50, 10, 0, 0);
		add(chose_player, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(50, 80, 0, 10);
		add(position, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(100, 10, 0, 10);
		add(human_score, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(100, 10, 10, 10);
		add(ai_score, gbc);
		
		setPreferredSize(new Dimension(400,600));
		setBackground(Color.WHITE);

	}
	private static class ComboBoxRenderer extends DefaultListCellRenderer {
		public ComboBoxRenderer() {
			setHorizontalAlignment(SwingConstants.LEFT);
		}


	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if (e.getStateChange() == ItemEvent.SELECTED)
		{
			computer_score--;
			String selected = (String)(e.getItem());
			if (selected.equals("X")) {
				
				
				int temp = ai;
				ai = human;
				human = temp;
				
				
				boardInstance.challenger = "X";
				boardInstance.ai = "O";
				boardInstance.clear_board(boardInstance);

			} else {
			
				int temp = ai;
				ai = human;
				human = temp;
				boardInstance.challenger = "O";
				boardInstance.ai = "X";
				boardInstance.clear_board(boardInstance);
			}
			challenger = selected;
			new Display(null).challenger = challenger;
			
			
		}
	}
	public int getAi() {
		return ai;
	}
	public void setAi() {
		this.ai = ai+1;
	}
	public int getHuman() {
		return human;
	}
	public void setHuman() {
		this.human = human+1;
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("sdgfds");
		frame.setSize(400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new Score());
		frame.setVisible(true);
	}

}
