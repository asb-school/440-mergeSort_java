/*
**
**	CPTR440
**	Andrew Breja
**	Java Threaded Merge Sort
**
*/

package mergesort;

import java.util.ArrayList;
import java.util.Vector;

public class Sorter implements Runnable
{
	private ArrayList<Integer> workingItemCollection;
	private Vector<Integer> masterItemCollection;

	private int numberOfItems;
	private int numberOfThreads;
	private int threadId;

	private int beginingPosition;
	private int endingPosition;


	// --------------------------------------------------------------
	// CONSTRUCTOR
	
	public Sorter(Vector<Integer> givenMasterItemCollection, Integer givenNumberOfItems, Integer givenNumberOfThreads, Integer givenThreadId)
	{
		// Dependency injection
	    	this.masterItemCollection = givenMasterItemCollection;

	    	// Set variables
	    	this.numberOfItems = givenNumberOfItems;
	    	this.numberOfThreads = givenNumberOfThreads;
	    	this.threadId = givenThreadId;

	    	// Initialize 
	    	workingItemCollection = new ArrayList<Integer>();
	}


	// --------------------------------------------------------------
	// CALCULATE POSITIONS

	private void calculatePositions()
	{
		this.beginingPosition = (this.numberOfItems / this.numberOfThreads) * this.threadId;

		this.endingPosition = this.beginingPosition + (this.numberOfItems / this.numberOfThreads);

		// The last thread gets the slack
		if ((this.threadId == this.numberOfThreads - 1) && (this.endingPosition < this.numberOfItems -1))
		{
		        this.endingPosition = this.numberOfItems - 1;
		}
	}


	// --------------------------------------------------------------
	// GET SORTED ITEMS

	public ArrayList getSortedItems()
	{
		return this.workingItemCollection;
	}


	// --------------------------------------------------------------
	// MAIN FUNCTION

	@Override
	public void run()
	{
		// Calculate beginning and ending positions
		this.calculatePositions();

		// For each - but use beginning and ending positions
		for (int iteratorIndex = this.beginingPosition; iteratorIndex <= this.endingPosition; iteratorIndex++)
		{
			// Copy to temporary local array
			this.workingItemCollection.add(this.masterItemCollection.get(iteratorIndex));
		}

		// Sort working item collection
		Collections.sort(this.workingItemCollection);
	}
}
