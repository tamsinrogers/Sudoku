/**
 * File: Sudoku.java
 * Author: Tamsin Rogers
 * Date: 2/27/20
 */
 
import java.io.*;
import java.util.Random;
import java.util.*; 

 public class Sudoku
 {
 	Board board;		
 	LandscapeDisplay scape;																	// initialize the board
 	
 	/* constructor that creates a new Board */
 	public Sudoku()
 	{
 		this.board = new Board();	
 		this.scape = new LandscapeDisplay(board,30);													// create a new board
 	}
 	
 	/* constructor that creates a new Board with a max value */
 	public Sudoku(int N)
 	{
 		Random ran = new Random();
 		this.board = new Board();	
 		this.scape = new LandscapeDisplay(board,30);	
 		
 		for(int i=0; i<N; i++)															// each time through the loop
 		{
 			int randomRow = ran.nextInt(9);												// generate a random row
 			int randomCol = ran.nextInt(9);												// generate a random column
 			int randomValue = ran.nextInt(9)+1;											// generate a random value 1-9
 		
			if((board.get(randomRow, randomCol).getValue()) != 0)						// if the board already has a non zero value there
			{
				i--;																	// try again
			}
		
			else 																		// if the location does not yet have a value
			{
				if(board.validValue(randomRow, randomCol, randomValue) == true)			// if it is valid												
				{
					board.set(randomRow, randomCol, randomValue, true); 				// insert value into location and specify as locked
				}
			
				else 																	// if it is not valid
				{		
					i--;																// try again								
				}
			}
 		}
 	}
 	
 	/* generates a text version of the sudoku board */
 	public String toString()
 	{
 		String str = board.toString();
 		return str;
 	}
 	
 	/* uses a stack to keep track of the solution and allows backtracking when it gets stuck */
 	public boolean solve(int delay) 
 	{
        CellStack stack = new CellStack(100);											// declare a CellStack variable and assign it a new CellStack of size 100
        int time=0;																		// declare an int variable time to hold the number of steps
		int numLocked = board.numLocked();		
												// declare an int variable numLocked and assign it the number of locked Cells on the board
        while(stack.size() < board.Size*board.Size - numLocked)							// while the size of the stack is less than Board.Size * Board.Size - numLocked
        {	
			time = time+1;		
			
			if( delay > 0 ) 
			{
				try 
				{
					Thread.sleep(delay);
				}
				
				catch(InterruptedException ex) 
				{
					System.out.println("Interrupted");
				}
				
				scape.repaint();
			}
										
			Cell best = new Cell();														// assign to a Cell variable best the result of calling board.findBestCell()
			best = board.findBest();	
			
            if(best != null)															// if best is not null
            {
                stack.push(best);														// push the Cell best onto the stack
                board.set(best.getRow(), best.getCol(), best.getValue());				// set the board to match the Cell best
			}
            
            else
            {
				boolean stuck = true;													// assign to a boolean variable stuck the value true
				
				while(stuck && (stack.size() > 0))										// while stuck and the stack size is greater than 0
				{
					Cell last;															// assign to a Cell variable last the top of the stack (stack.pop())
					last = stack.pop();
					
                    for(int v = (last.getValue()+1); v<10; v++)							// for v is assigned last's current value + 1; while less than 10; increment v
                    {
                        if(board.validValue(last.getRow(), last.getCol(), v))			// if v is a valid value as last's location
                        {
                            last.setValue(v);											// set the value of last to v
                            board.set(last.getRow(), last.getCol(), v);					// set the board to match the Cell last
                            stack.push(last);											// push last on the stack
                            stuck = false;												// assign to stuck the value false
                            break;														// break out of the for loop
                        }
                    }

					if(stuck)
					{
						board.set(last.getRow(), last.getCol(), 0);							// set the board's value at the location of last to 0
					}

					if(stack.size() == 0)													// if the stack size is equal to 0
					{
						System.out.println(time);											// print out the time
						return false;
					}
				}
			}
		}
        System.out.println("time steps: " + time);														// print out the number of steps taken
 		return true;
 	}

 	/* test the methods */
	  public static void main(String[] argv)
	  {
	  	Sudoku sudoku = new Sudoku(Integer.parseInt(argv[0]));
	  	//System.out.println("ORIGINAL BOARD:");
	  	//System.out.println(sudoku.toString());
	  	sudoku.solve(0);
	  	//System.out.println("SOLVED BOARD:");
	  	//System.out.println(sudoku);
	  	
	  	/*Sudoku sudoku10 = new Sudoku();
	  	board.read("board10.txt");
	  	System.out.println("board 10 initial values:");
	  	System.out.println(sudoku10.toString());
	  	sudoku10.solve();
	  	System.out.println("board 10 final values:");
	  	System.out.println(sudoku10.toString());*/

	  }
}