package finatTic;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Display  extends JPanel implements ActionListener 
{
	JLabel text;
	int turn;
	String winner;
	
	 static Board boardinstance;
	static String challenger;

	protected JButton playagain;
	public Display(String winner)
	{
		
		
		text = new JLabel();
		
		text.setFont(new Font("HYSWLongFangSong", Font.BOLD, 110));
			
		
		text.setText("Tic-Tac-Toe");
		
		if(boardinstance != null) {
			
		boolean isEmpty = false;
		for (JButton button:boardinstance.buttons)
		{
			if (button.getText().isEmpty())
			{
				isEmpty= true;
				break;
			}
			
		}
		if (!isEmpty) text.setText("It's A Draw");
		}
		
	
		
		playagain = new JButton(" RESET ");

		playagain.setFont(new Font("Time new Romans",Font.PLAIN,20));

		playagain.setBackground(Color.BLUE);

		playagain.setForeground(Color.WHITE);

		playagain.setSize(new Dimension(60,30));

		playagain.setFocusable(false);

		playagain.addActionListener(this);
		
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(text, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 0, 0);
		add(playagain, gbc);
		
		
		setBackground(Color.WHITE);


		
	}
	public void changeDisplay()
	{
		if (boardinstance != null)
		{
			if (boardinstance.check_winner() != 0)
			{
				if (boardinstance.check_winner()!= -10)
				{
					text.setText("X Wins The Game");
				}
				else {
					text.setText("0 Wins The Game");
				}
			
			}else {
				boolean isEmpty = false;
				for (JButton button:boardinstance.buttons)
				{
					if (button.getText().isEmpty())
					{
						isEmpty = true;
						break;
					}
				}if(!isEmpty) text.setText("It's A Draw");
				
			}
			text.getParent().repaint();
			text.getParent().revalidate();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("sdgfds");
		frame.setSize(700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new Display(null));
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		boardinstance.clear_board(boardinstance);
		
		
	}
}
