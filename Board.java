/**
 * File: CellStack.java
 * Author: Tamsin Rogers
 * Date: 2/27/20
 */
 
import java.io.*;
import java.awt.Graphics;

 public class Board
 {
 	private Cell[][] boardArray;											// initialize the 2d array of cells
 	public static final int Size = 9;										// initialize the size variable
 	
 	/* constructs the board and defines the board fields */
 	public Board()
 	{
 		this.boardArray = new Cell[Board.Size][Board.Size];					// create a new 2d array of cells
 		for(int i =0; i<Board.Size; i++)									// run through the board
 		{
 			for(int j=0; j<Board.Size; j++)
 			{
 				boardArray[i][j] = new Cell(i,j,0);							// initialize each new cell location in the grid to 0
 			}
 		}
 	}
 	
 	/* returns a string representation of the board */
 	public String toString()
 	{
 		String str = "";													// initialize the str variable
 		for(int k=0; k<9; k++)												// run through the board
 		{
 			for(int i=0; i<9; i++)											
 			{
 				str += this.boardArray[k][i].getValue();					// get the value of the cell at the given location
 				if((i+1)%3 == 0)											// separate the board into 3x3 blocks
 				{		
 					str += "\t";											// tab the string in
 				}
 			}
 			str += "\n";													// create a new line
 			if((k+1)%3 == 0)
 			{
 				str += "\n";												// create a new line
 			}				
 		}
 		return str;															// return the final cell values in the board as strings
 	}
 	
 	/* returns the number of columns in the board */
 	public int getCols()
 	{
 		return this.Size;
 	}
 	
 	/* returns the number of rows in the board */
 	public int getRows()
 	{
 		return this.Size;
 	}
 	
 	/* returns the cell located at r, c */
 	public Cell get(int r, int c)
 	{
 		return this.boardArray[r][c];
 	}
 	
 	/* returns whether the cell at r, c is locked (true) or unlocked (false) */
 	public boolean isLocked(int r, int c)
 	{
 		return this.boardArray[r][c].isLocked();
 	}
 	
 	/* returns the number of locked cells on the board */
 	public int numLocked()
 	{
 		int locked = 0;														// initialize the locked counter
 		for(int i=0; i<this.Size; i++)										// run through the board
 		{
 			for(int j=0; j<this.Size; j++)
 			{
 				if(this.boardArray[i][j].isLocked())						// if the cell at the current location on the board is locked
 				{
 					locked++;												// increment the locked counter by 1
 				}
 			}
 		}
 		return locked;														// return the final value of the locked counter
 	}
 	
 	/* returns the value of the cell located at r, c */
 	public int value(int r, int c)
 	{
 		return this.boardArray[r][c].getValue();
 	}
 	
 	/* sets the value of the set located at r, c */
 	public void set(int r, int c, int value)
 	{
 		this.boardArray[r][c].setValue(value);
 	}
 	
 	/* sets the value and the locked condition of the cell at r, c */
 	public void set(int r, int c, int value, boolean locked)
 	{
 		this.boardArray[r][c].setValue(value);
 		this.boardArray[r][c].setLocked(locked);
 	}
 	
 	/* tests if the value is valid */
 	public boolean validValue(int row, int col, int value)
 	{
 		if(value<1 || value>9)												// make sure the value is in range
 		{
 			//System.out.println("invalid value " + value);
 			return false;
 		}
 		
 		for(int i=0; i<this.Size; i++)										//checks the row and column
 		{
 			if(this.boardArray[row][i].getValue() == value && i!=col)		// if the number is already in the row
 			{
 				return false;
 			}
 			
 			if(this.boardArray[i][col].getValue() == value && i!=row)		// if the number is already in the column
 			{
 				return false;
 			}
 		}
 		
 		int boxRow = row/3;
 		boxRow = boxRow*3;
 		int boxCol = col/3;
 		boxCol = boxCol*3;
 		
 		for(int j = boxRow; j<boxRow+3; j++)								//	loop through the 3x3 box
 		{
 			for(int k = boxCol; k<boxCol+3; k++)
 			{
 				if((this.boardArray[j][k]).getValue() == value)				// if the cell at j,k is has the value
 				{
 					if(j!=row)
 					{
 						return false;
 					}
 					
 					if(k!=col)
 					{
 						return false;
 					}
 					
 					else
 					{
 						return true;
 					}
 				}
 			}
 		}
 		
 		return true;
 	} 
 	
 	/* test if the board is solved */
 	public boolean validSolution()
 	{
 		for(int i=0; i<this.Size; i++)
 		{
 			for(int j=0; j<this.Size; j++)
 			{
 				if(boardArray[i][j].getValue() == 0)
 				{
 					return false;
 				}
 				
 				if(this.validValue(i,j,boardArray[i][j].getValue()) == false)
 				{
 					return false;
 				}
 				
 				else
 				{
 					return true;
 				}
 			}
 		}
 	return true;
 	}
 	

 	/* read through the entire board using FileReaders */ 
	public boolean read(String filename) 
	{
		try 
		{
			FileReader fileReader = new FileReader(filename);				// assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
			BufferedReader bufferedReader = new BufferedReader(fileReader);	// assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
			String line = bufferedReader.readLine();						// assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
			
			int currentRow = 0;
			int col = 0;
			
			while(line != null)												// start a while loop that loops while line isn't null
			{
				
				  String[] array = line.split("[ ]+");						// assign to an array of type String the result of calling split on the line with the argument "[ ]+"
			
				  
				  for (int i=0; i<array.length; i++)
				  {
			
				  	if(Integer.parseInt(array[col]) != 0)
				  	{
				  		this.set(currentRow, col, Integer.parseInt(array[col]), true);
				  	}
				  	
				  	else
				  	{
				  		this.set(currentRow, col, Integer.parseInt(array[col]), false);
				  	}
				  	
				  	col++;
				  }
				  
				  line = bufferedReader.readLine();						// assign to line the result of calling the readLine method of your BufferedReader object.
			  		currentRow += 1;
					col = 0;
			}
				  	
			  bufferedReader.close();										// call the close method of the BufferedReader
			  return true;	
			
		}
		catch(FileNotFoundException ex) 
		{
		  System.out.println("Board.read():: unable to open file " + filename );
		}
		
		catch(IOException ex) 
		{
		  System.out.println("Board.read():: error reading file " + filename);
		}

		return false;
	  }
	  
	  public Cell findBest()
 	{
 		Cell best = new Cell();
 	
 		for(int i =0; i<Board.Size; i++)									// run through the board
 		{
 			for(int j=0; j<Board.Size; j++)
 			{
 				int thisValue = boardArray[i][j].getValue();
 				
 				if(thisValue == 0)
 				{
					for(int x=thisValue; x<=9; x++)
					{
						if(this.validValue(i,j,x) == true)
						{
							best = boardArray[i][j];
							best.setValue(x);
							return best;
						}
					}
					return null;
 				}
 			}
 		}
 		return null;
 	}
 	
 	public void draw(Graphics g, int scale)
 	{
 		for(int i=0; i<this.Size; i++)										// run through the board
 		{
 			for(int j=0; j<this.Size; j++)
 			{
 				boardArray[i][j].draw(g, j, i, 37);
 			}
 		}
 	}
	  
	  /* test the methods */
	  public static void main(String[] argv)
	  {
	  	Board testBoard = new Board();										// initialize a new board (2d array of cells)
	  	testBoard.read(argv[0]);
	  	
// 		System.out.println(testBoard);											// print the board
// 	  	System.out.println("rows: " + testBoard.getRows());					// print the number of rows in the board
// 	  	System.out.println("cols: " + testBoard.getCols());					// print the number of columns in the board
// 	  	System.out.println("get cell: " + testBoard.get(6,2));				// test the getCell method by getting the cell at 6,2
// 	  	testBoard.set(5,7,2);												// set the value of the cell located at 5, 7 to 2
// 		System.out.println(testBoard.toString());										// print the altered board
// 		System.out.println("valid value: should be true: " + testBoard.validValue(1,8,2));
// 		System.out.println("valid solution: (false, if unsolved, true if solved) " + testBoard.validSolution());
// 		
	  }
}