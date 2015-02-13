import java.util.LinkedHashMap;
import java.util.Map;


public class LRUCache {
	
	@SuppressWarnings("serial")
	static class Cache<K, V> extends LinkedHashMap<K, V> {
		
		public Cache(int c, float f, boolean o) {
			super(c, f, o);
		}
		
		protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
			if (size() > 5) {
				return true;
			}
			return false;
		}
	}

	private Cache<String, Object> cache = null;
	
	public LRUCache() {
		cache = new Cache<String, Object>(5, (float)0.75, true);
	}
	
	public void add(String key, Object val) {
		cache.put(key, val);
	}
	
	public void get(String key) {
		System.out.println("Key="+key+" Value="+cache.get(key));
	}
	
	public static void main(String[] args) {
		LRUCache c = new LRUCache();
		c.add("A", new Integer(1));
		c.add("B", new Integer(2));
		c.add("C", new Integer(3));
		c.add("D", new Integer(4));
		c.add("E", new Integer(5));
		System.out.println(c.cache);
		c.get("A");
		//c.get("D");
		c.add("F", new Integer(6));
		System.out.println(c.cache);
	}
	
}
