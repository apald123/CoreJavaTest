import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Recursion {

	public static void permute(String s) {
		StringBuilder sb = new StringBuilder(s);
		doPermute("", s);
	}
	
	private static void doPermute(String prefix, String str) {
		
		int n = str.length();
	    if (n == 0) System.out.println(prefix);
	    else {
	    	
	        for (int i = 0; i < n; i++) {	        	
	        	doPermute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	        	print("AFTER RETURN Prefix="+prefix+" String="+str+" i="+i);
	        }
	    }
	}

	public static List permute1(String s) {
		StringBuilder sb = new StringBuilder(s);
		return doPermute1("", s);
	}
	
	private static List doPermute1(String prefix, String str) {
		
		List<String> list = new ArrayList<String>();
		int n = str.length();
	    if (n == 0) {
	    	System.out.println(prefix);
	    	list.add(prefix);
	    	return list;
	    }
	    else {
	        for (int i = 0; i < n; i++) {
	        	print("Prefix="+prefix+" String="+str+" i="+i);
	        	list.addAll(doPermute1(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n)));
	        }
	    }
	    return list;
	}

	public static void print(String s) {
		System.out.println(s);
	}
	
	public static void print(int[] arr) {
		for (int i : arr) {
			print(String.valueOf(i));
		}
	}
	
	public static void myPermute(String prefix, String s) {
		int len = s.length();
		if (len <= 0) {
			print(prefix);
			return;
		}
		print("Prefix="+prefix+" String="+s+" Length="+len);
		for (int i=0; i<len; i++) {
			String new_prefix = prefix + s.charAt(i); // chop the every letter from string
			String new_s = s.substring(0, i) + s.substring(i+1, len);
			
			print("New Prefix="+new_prefix+" New String="+new_s+" Loop Index="+i);
			myPermute(new_prefix, new_s);
		}
	}
	
	/*
	 * Let's use input abc as an example.

	Start off with just the last element (c) in a set (["c"]), then add the second last element (b) 
	to its front, end and every possible positions in the middle, making it ["bc", "cb"] and then in 
	the same manner it will add the next element from the back (a) to each string in the set making it:
	"a" + "bc" = ["abc", "bac", "bca"]  and  "a" + "cb" = ["acb" ,"cab", "cba"] 

	Thus entire permutation:
	["abc", "bac", "bca","acb" ,"cab", "cba"]

	 */
	
	public static Set<String> generatePerm(String input)
	{
	    Set<String> set = new HashSet<String>();
	    if (input == "")
	        return set;

	    Character a = input.charAt(0);
	    
	    print("Input="+input+" a="+a);

	    if (input.length() > 1)
	    {
	        input = input.substring(1);

	        Set<String> permSet = generatePerm(input);

	        System.out.println("PermSet="+permSet);
	        System.out.println("Set (Before)="+set);

	        for (String x : permSet)
	        {
	            for (int i = 0; i <= x.length(); i++)
	            {
	                set.add(x.substring(0, i) + a + x.substring(i));
	            }
	        }
	        System.out.println("Set (After)="+set);
	    }
	    else
	    {
	        set.add(a + "");
	    }
	    return set;
	}

	/*
	 * *
	 */
	public static ArrayList<String> permutations(String s) {
	    ArrayList<String> out = new ArrayList<String>();
	    if (s.length() == 1) {
	        out.add(s);
	        return out;
	    }
	    char first = s.charAt(0);
	    String rest = s.substring(1);
	    
	    List<String> prevOut = permutations(rest);
	    for (String permutation : prevOut) {
	        out.addAll(insertAtAllPositions(first, permutation));
	    }
	    return out;
	}
	public static ArrayList<String> insertAtAllPositions(char ch, String s) {
	    ArrayList<String> out = new ArrayList<String>();
	    for (int i = 0; i <= s.length(); ++i) {
	        String inserted = s.substring(0, i) + ch + s.substring(i);
	        out.add(inserted);
	    }
	    return out;
	}
	
	public static Set<String> permutations1(String s) {
		Set<String> out = new HashSet<String>();
	    if (s.length() == 1) {
	        out.add(s);
	        return out;
	    }
	    char first = s.charAt(0);
	    String rest = s.substring(1);
	    
	    Set<String> prevOut = permutations1(rest);
	    for (String permutation : prevOut) {
	        out.addAll(insertAtAllPositions(first, permutation));
	    }
	    return out;
	}
	public static Set<String> insertAtAllPositions1(char ch, String s) {
		Set<String> out = new HashSet<String>();
	    for (int i = 0; i <= s.length(); ++i) {
	        String inserted = s.substring(0, i) + ch + s.substring(i);
	        out.add(inserted);
	    }
	    return out;
	}
	
	final static Map<Character, String> map = new HashMap<>();
	static {
		map.put('2', "ABC");
		map.put('3', "DEF");
		map.put('4', "GHI");
	}
	 
	public static List phonePermR(String phone) {
		List<String> list1 = new ArrayList<>();
		char digit = phone.charAt(0);
		
		if (phone.length() <= 1) {
			String str = map.get(digit);
			for (int i=0; i<str.length(); i++) {
				list1.add(str.substring(i,i+1));				
			}
		} else {
			
			String phone1 = phone.substring(1);
			
			List<String> list2 = phonePermR(phone1);
			System.out.println("List2="+list2);
			
			String str = map.get(digit);
			for (int i=0; i<str.length(); i++) {
				for (String s : list2) {
					list1.add(str.charAt(i)+s);
				}
			}
		}
		
		return list1;
	}
	
	public static List phonePerm(String num) {
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		list1.add("");
			
		for (int i=0; i<num.length(); i++) {
			
			for (String s : list1) {
				String str = map.get(num.charAt(i));
				
				for (int j=0; j<str.length(); j++) {
					list2.add(s+str.charAt(j))	;						
				}
			}
			list1 = list2;
			list2 = new ArrayList<String>();
		}
		
		return list1;
	}

	public static void main(String[] args) {
		
		permute("ABCD");
		/*
		Set<String> set = Recursion.generatePerm("abcd");
		System.out.println("Set="+set);
		
		List<String> list = Recursion.permute1("ABCD");
		System.out.println("List="+list);
		*/
		List list = phonePermR("234");
		//System.out.println("List="+list);
	}

	/*
	public static Set<String> generatePerm(String input)
	{
	    Set<String> set = new TreeSet<String>();
	    if (input == "")
	        return set;

	    Character a = input.charAt(0);

	    if (input.length() > 1)
	    {
	        input = input.substring(1);

	        Set<String> permSet = generatePerm(input);

	        for (String x : permSet)
	        {
	            for (int i = 0; i <= x.length(); i++)
	            {
	                set.add(x.substring(0, i) + a + x.substring(i));
	            }
	        }
	    }
	    else
	    {
	        set.add(a + "");
	    }
	    return set;
	}
	*/
}
