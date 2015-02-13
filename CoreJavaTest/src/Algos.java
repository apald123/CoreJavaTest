import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algos {

	static int[] arr = { 1, 3, 5, 6, 7, 9, 10, 13, 15, 16, 19, 22, 25, 28, 30,
			34, 37, 41, 44, 48, 50, 51, 55 };
	/*
	 * BINARY SEARCH
	 */
	public static int binarySearch(int x) {
		int low = 0;
		int high = arr.length - 1;

		while (true) {

			int idx = (low + high) / 2;

			int elem = arr[idx];
			print("low=" + low + "  high=" + high + " idx=" + idx + " elem="
					+ arr[idx]);

			if (elem == x) {
				return idx;
			}

			if (low > high) {
				return -1;
			}

			if (elem < x) {
				low = idx + 1; // upper half
			} else {
				high = idx - 1; // lower half
			}
		}
	}

	/*
	 * BINARY SEARCH RECURSIVE
	 */
	public static int binarySearchR(int[] arr1, int low, int high, int x) {

		int idx = (low + high) / 2;

		int elem = arr1[idx];
		print("low=" + low + "  high=" + high + " idx=" + idx + " elem="
				+ arr1[idx]);

		if (elem == x) {
			return idx;
		}

		if (low > high) {
			return -1;
		}

		if (elem < x) {
			low = idx + 1; // upper half
		} else {
			high = idx - 1; // lower half
		}
		return binarySearchR(arr1, low, high, x);
	}

	/*
	 * BUBBLE SORT
	 */
	public static void bubbleSort(int[] arr2) {
		
		int len = arr2.length;
		int temp;
		
		for (int i=0; i<len-1; i++) {
			for (int j=i+1; j<len; j++) {
				if (arr2[i] > arr2[j]) {
					temp = arr2[i];
					arr2[i] = arr2[j];
					arr2[j] = temp;				
				}
			}
		}
		print("Bubble Sort *******");
		print(arr2);
	}
	
	/*
	 * SELECTION SORT
	 */
	public static void selectionSort(int[] arr) {
		int len = arr.length;
		int temp;
		int min;
		int i, j;
		for (i=0; i<len-1; i++) {
			min = i;
			for (j=i+1; j<len; j++) {
				if (arr[j] < min) {
					min = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;				
		}
		
		print("Selection Sort *******");
		print(arr);
		
	}
	
	/*
	 * TRIANGLE
	 */
	public static int traingle(int n) {
		if (n==1) {
			return 1;
		}
		
		return n+traingle(n-1);
	}
	
	/*
	 * MERGE SORT
	 */
	
	
	class MyMergeSort {
	     
	    private int[] array;
	    private int[] tempMergArr;
	    private int length;
	 
	    public void sort(int inputArr[]) {
	        this.array = inputArr;
	        this.length = inputArr.length;
	        this.tempMergArr = new int[length];
	        doMergeSort(0, length - 1);
	    }
	 
	    private void doMergeSort(int lowerIndex, int higherIndex) {
	         
	        if (lowerIndex < higherIndex) {
	            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
	            System.out.println("Lower Half Sort With Lower="+lowerIndex+" Higher="+higherIndex+" Middle="+middle);
	            System.out.println("Calling doMergeSort with Lower="+lowerIndex+" Higher(middle)="+middle);
	            System.out.println("-------------------------------------------");
	            // Below step sorts the left side of the array
	            doMergeSort(lowerIndex, middle);
	            // Below step sorts the right side of the array
	            System.out.println("Higher Half Sort With Lower="+lowerIndex+" Higher="+higherIndex+" Middle="+middle);
	            System.out.println("Calling doMergeSort with Lower(middle+1)="+(middle+1)+" Higher="+higherIndex);
	            System.out.println("-------------------------------------------");
	            doMergeSort(middle + 1, higherIndex);
	            // Now merge both sides
	            System.out.println("Now Meging Parts Lower="+lowerIndex+" Higher="+higherIndex+" Middle="+middle);
	            System.out.println("-------------------------------------------");
	            mergeParts(lowerIndex, middle, higherIndex);
	        } else {
	        	System.out.println("Returning with Lower="+lowerIndex+" Higher="+higherIndex);
	        	System.out.println("-------------------------------------------");
	        }
	    }
	 
	    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
	 
	        for (int i = lowerIndex; i <= higherIndex; i++) {
	            tempMergArr[i] = array[i];
	        }
	        int i = lowerIndex;
	        int j = middle + 1;
	        int k = lowerIndex;
	        while (i <= middle && j <= higherIndex) {
	            if (tempMergArr[i] <= tempMergArr[j]) {
	                array[k] = tempMergArr[i];
	                i++;
	            } else {
	                array[k] = tempMergArr[j];
	                j++;
	            }
	            k++;
	        }
	        while (i <= middle) {
	            array[k] = tempMergArr[i];
	            k++;
	            i++;
	        }
	 
	    }
	}
	
 
	public static void print(String s) {
		System.out.println(s);
	}
	
	public static void print(int[] arr) {
		for (int i : arr) {
			print(String.valueOf(i));
		}
	}
	
	public static void main(String[] args) {
		Algos alg = new Algos();
		//int x = Integer.valueOf(args[0]);
		// int i = binarySearch(x);
		//int i = binarySearchR(arr, 0, arr.length - 1, 28);
		//System.out.println("Index=" + i + " Value=" + arr[i]);
		int arr[] = {8, 3, 5, 1, -2};
		//bubbleSort(arr);
		//selectionSort(arr);
		
		//print("Traingle for 4="+String.valueOf(traingle(4)));
		//permute("ab");
		//myPermute("", "ABCD");
		//Set s = generatePerm("ABC");
		
		/*
        int[] inputArr = {6, 4, 2, 5}; //{45,23,11,89,77,98,4,28,65,43};
        MyMergeSort mms = alg.new MyMergeSort();
        mms.sort(inputArr);
        for(int i:inputArr){
            System.out.print(i);
            System.out.print(" ");
        }
        */

        int a1[] = {4, 6, 8, 0, 0, 0, 0, 0};
        int a2[] = {1, 6, 8, 11, 13};
        alg.mergeSortedArray(a1, 3, a2, 5);
        System.out.println(Arrays.toString(a1));
	}
	
		public void mergeSortedArray(int A[], int m, int B[], int n) {
			 
	        while(m > 0 && n > 0){
	            if(A[m-1] > B[n-1]){
	                A[m+n-1] = A[m-1];
	                m--;
	            }else{
	                A[m+n-1] = B[n-1];
	                n--;
	            }
	        }
	 
	        while(n > 0){
	            A[m+n-1] = B[n-1];
	            n--;
	        }
	    }

}
