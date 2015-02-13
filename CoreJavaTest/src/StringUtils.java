import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;


public class StringUtils {
	
	/*
	 * IS SUBSTRING
	 */
	
	public static boolean isSubstring(String p, String c) {
		boolean offsetFound = false;
		int offset = 0;
		
		for (int i=0; i<p.length(); i++) {
			for (int j=0; j<c.length(); j++) {
				if (p.charAt(i) == c.charAt(j)) {
					offsetFound = true;
					offset = i+j;
					if (offset+c.length() > p.length())
						return false;
				}
				if (offsetFound) {				
					if (p.charAt(i+j) != c.charAt(j)) {
						return false;
					}
				} else
					break;
			}
			if (offsetFound)
				return true;
		}
		return false;
	}

	public static void reverse1(String str) {
		/*
		String[] splt = str.split(" ");
		int len = splt.length;
		String tmp;
		for (int i=0; i<splt.length; i++) {
			tmp = splt[i];
			splt[i] = splt[len-i];
			splt[len-i] = tmp;
		}
		*/
		int loops = str.length()/2;
		int len = str.length()-1;
		char tmp;
		StringBuilder sb = new StringBuilder(str);
		for (int i=0; i<loops;  i++) {
			tmp = sb.charAt(i);
			sb.setCharAt(i, str.charAt(len-i));
			sb.setCharAt(len-i, tmp);
		}
		System.out.println("Inter="+sb);
		
		int j = 0;
		int word = 0;
		for (int i=0; i<len; i++) {
			if (sb.charAt(i) == ' ') {
				word = i-j;
				for (int k=j; k<i/2;  k++) {
					tmp = sb.charAt(k);
					sb.setCharAt(k, str.charAt(word-k));
					sb.setCharAt(word-k, tmp);
				}
				j = i+1;
			}
		}
		
		System.out.println("Final="+sb);
		return;
	}
	
	public static String reverse2(String str) {
		if (str.length() <= 1) 
			return str;
		
		char c = str.charAt(0);
		
		str = reverse2(str.substring(1));
		
		str = str + c;
		
		return str;
	}
	
	public static List<String> tokenize(String filename, String delim) throws IOException {
		
		File file = new File(filename);
		BufferedReader buf = new BufferedReader(new FileReader(file));
		String line;	
		List<String> bigList = new ArrayList<String>();
		while ((line = buf.readLine()) != null) {
			String[] arr = line.split(delim);			
			bigList.addAll(Arrays.asList(arr));
		}
		return bigList;
		//for (String token : sb.tokenize)
	}
	
	public static String reverse3(String str) {
		char c = ' ';		
		Stack<Character> stk = new Stack<>();
		Deque<Character> deq = new ArrayDeque<>(); 
		for (int i=0; i<str.length(); i++)
		{
			c = str.charAt(i);
			stk.push(c);
			deq.push(c);
		}
	
		System.out.println("Stack="+stk);
		StringBuilder sb = new StringBuilder();
		while (!stk.isEmpty()) {
			sb.append(stk.pop());
		}
			
		return sb.toString();
	}
	
	public static void test(String filename) throws Exception {
		String s = "This is USA";
		String s2 = StringUtils.reverse3(s);
		System.out.println("S2="+s2);
		
		List<String> list = tokenize(filename, "\t");
		System.out.println("**********File Contents*********");
		System.out.println(list.toString());
		
	}
	
	public static void main(String[] arr) throws Exception {
		//StringUtils.test(arr[0]);
		boolean isSub = StringUtils.isSubstring("abcdefg", "1a") ;
		System.out.println("IsSub="+isSub);
		
		String str = "How Are You";
		str = StringUtils.reverse2(str);
		System.out.println("Reverse 2="+str);
	}
}
