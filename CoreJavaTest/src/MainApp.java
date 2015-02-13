import java.util.HashSet;
import java.util.Set;


public class MainApp {

	//@Override
	public static void main(String[] args) {
				
			int i = 1 << 10;
			System.out.println("i="+i+" Binary="+Integer.toBinaryString(i));
			
			int j = i >> 8;
			System.out.println("j="+Integer.toBinaryString(j));
			
			int k = i >>> 8;
			System.out.println("k="+Integer.toBinaryString(k));
			
			String[] arr = {"A", "B", "C", "D", "E"};
			rightShiftArr(arr);
			
			i = 5;
			j= 12;
			j = i ^ j;
			i = j ^ i;
			j = i ^ j;
			System.out.println("SWAP i="+i+" j="+j);
			
			System.out.println("Power of 2 ................");
			i = 8 & -8;
			System.out.println("i="+i+" Binary="+Integer.toBinaryString(i));
			printStar(3);		
	}
	
	public static void rightShiftArr(String[] arr) {
		if (arr.length <= 0) return;
		
		String prev = arr[0];
		for (int i=1; i<arr.length; i++) {
			String curr = arr[i];
			arr[i] = prev;
			prev = curr;
		}
		arr[0] = prev;
		
		printArray(arr);
	}
	
	public static void printArray(String[] arr) {
		for (String s : arr) {
			System.out.println("Array Value="+s);
		}
	}
	
	public static void printStar(int n) {
		if (n == 0) {
			return;
		}
		printStar(n-1);
		for (int i=0; i<n; i++) {
			System.out.print("*");
		}
		System.out.println("");
	}
	
}
