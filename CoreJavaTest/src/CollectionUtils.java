import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class CollectionUtils {
		
	
	/*
	 * Build Queue with push_rear(), pop_front(), find_min() in O(1)
	 */
	
	class MyQueue {
		private Queue<Integer> dataQ;
		private Deque<Integer> minQ;
		
		public MyQueue() {
			dataQ = new LinkedList<>();
			minQ = new LinkedList<>();
		}
		
		public void push_rear(int i) {
			dataQ.add(i);
			while (!minQ.isEmpty() && minQ.peekLast() >= i) {
				minQ.removeLast();
			}
			minQ.add(i);			
		}
		
		public int pop_front() {
			int value = 0;
			if (!dataQ.isEmpty()) {
				value = dataQ.remove();
			}
			if (!minQ.isEmpty() && minQ.peekFirst() == value) {
				minQ.removeFirst();
			}
			return value;
		}
		
		public int min() {
			return (!minQ.isEmpty() ? minQ.peekFirst() : -999);
		}
	}
	
	public static int oddManOut(int[] arr) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		int j = 0;
		List<Integer> list;
		for (int i : arr) {
			//map.put(i, j++);
			list = map.get(i);
			if (list != null) {
				list.add(j);
			} else {
				list = new ArrayList<Integer>();
				list.add(j);
				map.put(i, list);
			}
			j++;
		}
		
		for (Map.Entry<Integer, List<Integer>> e : map.entrySet()) {
			System.out.println("Key="+e.getKey()+" Value="+e.getValue());
			list = e.getValue();
			if (list.size() == 1) {
				return e.getKey();
			}
		}
		return -1;
	}
	
	public static int oddManOut1(int[] arr) {
		Set<Integer> set = new HashSet<>();
		int sum = 0;
		for (int i : arr) {
			sum ^= i;
		}
		System.out.println("Odd Man Out 1="+sum);
		return sum;
	}

	public static int oddManOut2(int[] arr) {
		Set<Integer> set = new HashSet<>();
		int sum = 0;
		for (int i : arr) {
			if (set.contains(i)) {
				sum = sum - i;
			} else {
				set.add(i);
				sum = sum + i;
			}				
		}
		System.out.println("Odd Man Out 2="+sum);
		return sum;
	}

	public static <T> void findDups1(List<T> list) {
		final List<T> duplist = new ArrayList<>();
		Set<T> set = new HashSet<T>() {
			@Override
			public boolean add(T o) {
				if (!super.add(o)) {
					duplist.add(o);
					return false;
				}
				return true;
			}
		};
		
		for (T t : list) {
			set.add(t);
		}
		//boolean b = list.removeAll(set);
		System.out.println("Dups="+duplist.toString());
		
	}
	
	
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(5);
		list.add(3);
		list.add(1);
		list.add(6);
		list.add(7);
		list.add(3);
		//findDups1(list);
		
		int[] arr = {1,1,2,2,3,4,4,5,5};
		//oddManOut1(arr);
		//oddManOut2(arr);

		CollectionUtils util = new CollectionUtils();
		MyQueue q = util.new MyQueue();
		
		q.push_rear(6);
		System.out.println("Min="+q.min());
		q.push_rear(5);
		q.push_rear(24);
		System.out.println("Min="+q.min());
		q.push_rear(50);
		System.out.println("Min="+q.min());
		q.push_rear(12);
		System.out.println("Min="+q.min());
		q.push_rear(1);
		System.out.println("Min="+q.min());
	}

}
