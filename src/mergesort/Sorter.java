/*
**
**	CPTR440
**	Andrew Breja
**	Java Threaded Merge Sort
**
*/

package mergesort;

import java.util.ArrayList;

public class Sorter implements Runnable
{
	private ArrayList<Integer> sortedItemsCollection;
	private ArrayList<Integer> masterItemCollection;

	public Sorter(ArrayList<Integer> givenMasterItemCollection, Integer givenNumItems, Integer givenStartIndex)
	{
		// Dependency injection
	    	this.masterItemCollection = givenMasterItemCollection;

	}

	@Override
	public void run()
	{
		// sort here
	}
}
