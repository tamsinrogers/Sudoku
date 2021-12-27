/**
 * File: Cell.java
 * Author: Tamsin Rogers
 * Date: 2/25/20
 */
 
 import java.awt.Graphics;
 
 public class Cell
 {
 	private int row;
 	private int col;
 	private int value;
 	private boolean locked;
 	
 	/* initializes all values to 0 or false */
 	public Cell()
 	{
 		row = 0;
 		col = 0;
 		value = 0;
 		locked = false;
 	}
 	
 	/* initialize the row, column, and value fields to the given parameter values and set the locked field to false */
 	public Cell(int row, int col, int value)
 	{
 		this.row = row;
 		this.col = col;
 		this.value = value;
 		locked = false;
 	}
 	
 	/* initialize all of the Cell fields given the parameters */
 	public Cell(int row, int col, int value, boolean locked)
 	{
 		this.row = row;
 		this.col = col;
 		this.value = value;
 		this.locked = false;
 	}
 	
 	/* return the Cell's row index */
 	public int getRow()
 	{
 		return this.row;
 	}
 	
 	/* return the Cell's column index */
 	public int getCol()
 	{	
 		return this.col;
 	}
 	
 	/* return the Cell's value */
 	public int getValue()
 	{
 		return this.value;
 	}
 	
 	/* set the Cell's value */
 	public void setValue(int newval)
 	{
 		this.value = newval;
 		
 	}
 	
 	/* return the value of the locked field */
 	public boolean isLocked()
 	{
 		return this.locked;
 	}
 	
 	/* set the Cell's locked field to the new value */
 	public void setLocked(boolean lock)
 	{
 		locked = lock;
 	}
 	
 	public Cell clone()
 	{
 		int newRow = this.row;
 		int newCol = this.col;
 		int newValue = this.value;
 		boolean newLocked = this.locked;
 		
 		Cell newcell = new Cell(newRow, newCol, newValue, newLocked);
 		
 		return newcell;
 	}
 	
 	public String toString()
 	{
 		return "value: " + this.value + " position: " + this.row + this.col + " locked: " + this.locked;
 	}
 	
 	public void draw(Graphics g, int x0, int y0, int scale)
 	{
 		char[] array;
 		array = new char[2];					// declare a 2 element array
 		array[0] = (char)('0' + this.value);	
 		array[1] = 0;
 		g.drawChars(array, 0, 1, (x0*scale), (y0*scale));
 	}
 }