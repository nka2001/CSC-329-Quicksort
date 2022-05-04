/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.m10quicksort;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author gerstl
 */
public class QuickSort<T extends Comparable<T>> implements SortTestable<T> {

    ArrayList<T> theData = new ArrayList<T>();

    @Override
    public void append(T newItem) {
        theData.add(newItem);
    }

    /**
     * use the quicksort algorithm to sort the array between firstIndex and
     * lastIndex (inclusive)
     *
     * @param theData The array
     * @param firstIndex The first index of the portion of the array to sort
     * @param lastIndex The last index of the portion of the array to sort
     */
    private void quickSort(T theData[], int firstIndex, int lastIndex) {

        if (firstIndex < lastIndex) {
            
            int partiton = partition(theData, firstIndex, lastIndex);
            quickSort(theData, firstIndex, partiton - 1);
            quickSort(theData, partiton + 1, lastIndex);
        }

    } // quickSort()

 private int partition(T theData[], int firstIndex, int lastIndex)
    {
        T pivot = theData[firstIndex];
        int firstBig = firstIndex;
        for (int i = firstIndex; i <= lastIndex; i++)
        {
            if(pivot.compareTo(theData[i]) >= 0 )
            {
                swap(theData, firstBig, i);
                firstBig++;
                
                
            } 
        } 
       
        swap(theData, firstBig - 1,firstIndex);
       
        
        
        return firstBig-1;//what to return ;
        
    }

    private void swap(T theData[], int i, int j) {
        T temp = theData[i];
        theData[i] = theData[j];
        theData[j] = temp;
    }

    private void median(T theData[], int begin, int end) {
        
        int middle = (begin + end) / 2;
        
        if(theData[begin].compareTo(theData[middle]) < 0 && theData[middle].compareTo(theData[end]) < 0){
            // b < m < e -> swap b and m
            swap(theData, begin, middle);
        } 
        else if(theData[begin].compareTo(theData[end]) < 0 && theData[end].compareTo(theData[middle]) < 0){
            swap(theData, begin, end);
        }
        /*
        else if(theData[middle].compareTo(theData[begin]) < 0 && theData[begin].compareTo(theData[end]) < 0){
            //do nothing
        }
        else if(theData[end].compareTo(theData[begin]) < 0 && theData[begin].compareTo(theData[middle]) < 0){
            //do nothing
        }
        */
        else if(theData[middle].compareTo(theData[end]) < 0 && theData[end].compareTo(theData[begin]) < 0){
            swap(theData, begin, end);
        }
        else if(theData[end].compareTo(theData[middle]) < 0 && theData[middle].compareTo(theData[begin]) < 0){
            swap(theData, middle, begin);
        }
    }

    /**
     * Public sort() method. Calls quicksort.
     */
    public void sort() {
        T theDataAsArray[] = (T[]) theData.toArray(new Comparable[theData.size()]);
        quickSort(theDataAsArray, 0, theDataAsArray.length - 1);
        // now put the data back
        theData.clear();
        Collections.<T>addAll(theData, theDataAsArray);
    }

    public int getCount() {
        return theData.size();
    }

    @Override
    public T getItemAt(int index) {
        return theData.get(index);
    }
}
