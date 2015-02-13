import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


public class BadTreeSet {
	
	class Item {
		public String name;
		public int age;	
				
		public Item(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}

		@Override
		public boolean equals(Object i) {
			return (name.equals(((Item)i).name));
		}

		@Override
		public String toString() {
			return "Item [name=" + name + ", age=" + age + "]";
		}				
	}
	
	class BadComp implements Comparator<Item> {
		
		@Override
		public int compare(Item i1, Item i2) {
			return (i1.age - i2.age);
		}
	}
	
	public static void main(String args[]) {
		
		BadTreeSet bad = new BadTreeSet();
		
		BadTreeSet.Item i1 = bad.new Item("Atul", 10);
		BadTreeSet.Item i2 = bad.new Item("Test", 10);
		BadTreeSet.Item i3 = bad.new Item("Atul", 20);
		
		System.out.println("i1.equals(i2)="+i1.equals(i2));
		System.out.println("i1.equals(i2)="+i1.equals(i3));
		
		Set<BadTreeSet.Item> set = new TreeSet<>(bad.new BadComp());
		set.add(i1);
		set.add(i2);
		set.add(i3);
		
		System.out.println("Set="+set);
		
	}

}
