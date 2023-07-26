package finatTic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PlayAgain extends JPanel implements ActionListener{

	protected JButton playagain;
	MiniMax_Algo algo;

	public PlayAgain()
	{

		playagain = new JButton(" RESET ");

		playagain.setFont(new Font("Time new Romans",Font.PLAIN,20));

		playagain.setBackground(Color.BLUE);

		playagain.setForeground(Color.WHITE);

		playagain.setSize(new Dimension(60,30));

		playagain.setFocusable(false);

		playagain.addActionListener(this);

		add(playagain);

		setLayout(new FlowLayout(FlowLayout.CENTER,50,50));

		setBackground(Color.WHITE);

		setSize(new Dimension(600,130));
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Container parent = playagain.getParent().getParent();
		parent.remove(1);
		parent.setLayout(new FlowLayout(FlowLayout.CENTER,400,0) );

		parent.setPreferredSize(new Dimension(700,800));
		parent.add(new Display(null));

		parent.add(new Board(true));

		parent.add(new PlayAgain());
		parent.repaint();
		parent.revalidate();

	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("sdfdssdfdsfsdfdsf");
		frame.setSize(700, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new PlayAgain());
		frame.setVisible(true);
	}

}
