/*
**
**	CPTR440
**	Andrew Breja
**	Java Threaded Merge Sort
**
*/

package mergesort;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort 
{
	public static void main(String[] args) 
	{
        		// Variables
		int collectionSize = 0;
		int numberOfThreads = 0;

		ArrayList<Integer> masterItemCollection = new ArrayList<Integer>();
		ArrayList<Sorter> sorterList = new ArrayList<Sorter>();

        		// Get command line parameters
		if (args.length == 2)
		{
        			collectionSize = Integer.parseInt(args[0]);
        			numberOfThreads = Integer.parseInt(args[1]);
        		}
        		// Bad parameters
        		else
        		{
        			System.err.println("You really dun goofd");
        			System.exit(0);
        		}



        		// Executor service
        		ExecutorService threadScheduler = Executors.newFixedThreadPool(numberOfThreads);

        		for (int i = 0; i < numberOfThreads; i++)
        		{
        			// Create new sorter
        			Sorter mergeSorter = new Sorter(masterItemCollection, 3, 3);

        			// Keep track of the sorter
        			sorterList.add(mergeSorter);

        			// Add item to thread scheduler
        			threadScheduler.execute(mergeSorter);
        		}

        		// Tell the threadScheduler that there are no more things to process and it should shutdown
		// and stop expecting new tasks to process
	 	threadScheduler.shutdown();

	 	// Hold/pause main method (this one) execution until the threadScheduler is completed
		while (!threadScheduler.isTerminated()) {}

		// Merge items back together

	}
}
