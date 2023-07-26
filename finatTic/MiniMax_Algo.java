package finatTic;

import javax.swing.JButton;
public class MiniMax_Algo
{



	protected int best_score, move,score,result;



	public int check_Win(JButton[] buttons)
	{
		String[] lines  = new String[8];
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
				return  10 ;
			} else if (lines[i].equalsIgnoreCase("OOO")) {
				return  -10;
			}
		}
		return 0;
	}
	public int minimax(JButton[] buttons, int depth, boolean isMaximizingPlayer) {
	    int result = check_Win(buttons);
	    
	    if (result != 0) {
	        return result;
	    }

	    if (isMaximizingPlayer) {
	        int bestScore = Integer.MIN_VALUE;

	        for (int i = 0; i < 9; i++) {
	            if (buttons[i].getText().isBlank()) {
	                buttons[i].setText("X");
	                int score = minimax(buttons, depth + 1, false);
	                buttons[i].setText("");
	                bestScore = Math.max(score, bestScore);
	            }
	        }

	        return bestScore-depth;
	    } else {
	        int bestScore = Integer.MAX_VALUE;

	        for (int i = 0; i < 9; i++) {
	            if (buttons[i].getText().isBlank()) {
	                buttons[i].setText("O");
	                int score = minimax(buttons, depth + 1, true);
	                buttons[i].setText("");
	                bestScore = Math.min(score, bestScore);
	            }
	        }

	        return bestScore+depth;
	    }
	}


	public int best_Move(JButton[] buttons, boolean x) {
	    int best_score = Integer.MIN_VALUE;
	    int best_move = -1;

	    for (int i = 0; i < 9; i++) {
	        if (buttons[i].getText().isBlank()) {
	            buttons[i].setText(x ? "X" : "O"); // Set the appropriate player symbol
	            JButton[] buttonsCopy = buttons.clone(); // Create a copy of the buttons array
	            int score = x ? minimax(buttonsCopy, 0, false) : minimax(buttonsCopy, 0, true); // Adjust the parameters based on the player
	            buttons[i].setText("");

	            if (score > best_score) {
	                best_score = score;
	                best_move = i;
	            }
	        }
	    }

	    return best_move;
	}
	
	public int best_move_o(JButton[] buttons)
	{
		int best_score = Integer.MAX_VALUE;
	    int best_move = -1;

	    for (int i = 0; i < 9; i++) {
	        if (buttons[i].getText().isBlank()) {
	            buttons[i].setText("O"); 
	            JButton[] buttonsCopy = buttons.clone(); 
	            int score =  minimax(buttonsCopy, 0, true); 
	            buttons[i].setText("");

	            if (score < best_score) {
	                best_score = score;
	                best_move = i;
	            }
	        }
	    }

	    return best_move;
	}
	
	



}
