/**
 * File: CellStack.java
 * Author: Tamsin Rogers
 * Date: 2/25/20
 */
 
 public class CellStack
 {
 	private Cell[] cellArray;
 	private Cell[] newArray;
 	private int top = 0;
 
 	/* initialize the stack to a default size */
 	public CellStack()
 	{
 		this.cellArray = new Cell[10];
 		this.top = 0;
 		
 	}
 	
 	/* initialize the stack to hold up to max elements */
 	public CellStack(int max)
 	{
 		this.cellArray = new Cell[(max)];
 		//(cellArray[top]).setValue(0);
 		this.top = 0;
 		
 	}
 	
 	/* if there is space, push c onto the stack */
 	public void push(Cell c)
 	{
 		if(this.top < cellArray.length)
 		{
 			cellArray[this.top] = c;
 			this.top++;
 			return;
 		}
 		
 		else
 		{
 			newArray = new Cell[(this.cellArray.length * 2)];
 			
 			for(int i=0; i<this.cellArray.length; i++)
 			{
 				newArray[i] = this.cellArray[i];
 			}
 		
 		this.cellArray = newArray;
 		this.cellArray[top] = c;
 		return; 
 		}
 	}
 	
 	/* remove and return the top element from the stack, return null if the stack is empty */
 	public Cell pop()
 	{
 		if (this.top != 0)
 		{
 			Cell popped = cellArray[top-1];
 			top --;
 			//System.out.println("top value: " + this.top);
 			return popped;
 		}
 		
 		else
 		{
 			return null;
 		}
 	}
 	
 	/* return the number of elements on the stack */
 	public int size()
 	{
 		return this.top;
 	}
 	
 	/* return true if the stack is empty */
 	public boolean empty()
 	{
 		if(this.top == 0)
 		{
 			return true;
 		}
 		
 		else
 		{
 			return false;
 		}
 	}
 	
 
 }