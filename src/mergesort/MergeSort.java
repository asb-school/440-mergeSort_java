/*
**
**	CPTR440
**	Andrew Breja
**	Java Threaded Merge Sort
**
*/

package mergesort;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MergeSort 
{
        public static Vector<Integer> masterItemCollection = new Vector<Integer>();

        // Function to generate random numbers
        public static void generateIntegers(int givenCollectionSize)
        {
                // Create a random generager
                Random randomNumberGenerator = new Random();

                // For each 
                for (int i = 0; i < givenCollectionSize; i++)
                {
                        // Generate numbers between 0 and 1000
                        masterItemCollection.add(randomNumberGenerator.nextInt(1000));
                }
        }


        public static void main(String[] args) 
        {
                // Variables
                int collectionSize = 0;
                int numberOfThreads = 0;
                int lowestNumber = 0;
                int completed = 0;
                boolean notSorted = true;
                int numberOfSorters = 0;
                
                ArrayList<Integer> sortedItemList = new ArrayList<Integer>();
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
                        System.err.println("USAGE: arg1: collection size, arg2: number of threads");
                        System.exit(0);
                }
                
                // Generate random numbers in the master item collection
                generateIntegers(collectionSize);
                
                // Debug
                System.out.println("Unsorted list:");
                
                for (Integer currentItem : masterItemCollection)
                {
                    System.out.println(currentItem);
                }

                // Executor service
                ExecutorService threadScheduler = Executors.newFixedThreadPool(numberOfThreads);

                for (int iteratorIndex = 0; iteratorIndex < numberOfThreads; iteratorIndex++)
                {
                        // Create new sorter
                        Sorter mergeSorter = new Sorter(masterItemCollection, collectionSize, numberOfThreads, iteratorIndex);

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

                // Get amount of sorted lists
                numberOfSorters = sorterList.size();

                // Merge individual items into a giant sorted list
                while (notSorted)
                {
                        // Reset lowest number
                        lowestNumber = 9999;

                        // For each list
                        for (int sorterId = 0; sorterId < numberOfSorters; sorterId++)
                        {
                                // Is item from list a lower number than the current lowest number
                                if (currentSorter.getItem() < lowestNumber)
                                {
                                        // Set the new value as lowest number
                                        lowestNumber = currentSorter.getItem();
                                }                                
                        }

                        // Save lowest number
                        if (lowestNumber != 9999)
                        {
                                // Get the 
                                sortedItemList.add(lowestNumber);
                        }
                        else
                        {
                                        // Increment sorter index
                                        currentSorter.incrementIndex();

                }

                // Debug
                System.out.println("Sorted list:");
                for (Integer currentItem : sortedItemList)
                {
                        System.out.println(currentItem);
                }
        }
}
