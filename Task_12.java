package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import Algorithms.BinarySearch;

public class Task_12 {
	public static void main(String[] args) {
	int list[] = { 2, 3, -4, 5, 1, 7};
		int sum = -7;
		System.out.println(hasPair_FromBothEnds(list, sum));
	}
	
	public boolean hasPair_BruteForce( int[] list, int sum ) {
		//O(n^2)
		for(int i = 0; i < list.length; i++) {
			for(int j = i + 1; j < list.length;j++) {
				if( list[i] + list[j] == sum) {
					return true;
				}
			}
		}
		
		return false;
	}

    public boolean hasPair_BinarySearch( int[] list, int sum ) {
    	//O(nlog(n))
    	for(int i = 0; i < list.length; i++) {
    		if( BinarySearch.binarySearch(list, sum - list[i]) > 0) {
    			return true;
    		}
    	}
		
    	
		
    	
		return false;
	}
    
    public static boolean hasPair_FromBothEnds( int[] list, int sum ) {
    	//O(n)
    	int lastIndex = list.length - 1;
    	int firstIndex = 0;
    	while( lastIndex > firstIndex) {
    		int currentSum = list[lastIndex] + list[firstIndex];
    		if(currentSum > sum) {
    			lastIndex--;
    		} else if (currentSum < sum) {
    			firstIndex++;
    		} else {
    			return true;
    		}
         }
    	return false;
    }
    
    public boolean hasPair_HashSet( int[] list, int sum ) {
    	//O(n)
    	Set<Integer> rests = new HashSet<Integer>();
    	for(int i = 0; i < list.length; i++) {
    		if(rests.contains(list[i])) {
    			return true;
    		}
    		
    		rests.add(sum - list[i]);
    	}
	
	return false;
    }
}