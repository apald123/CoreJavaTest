import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GroupBy {
	
	public class Device {
		String country;
		String date;
		String category;
		String deviceId;
		
		public Device(String c, String d, String ct, String id) {
			country = c;
			date = d;
			category = ct;
			deviceId = id;
		}

		@Override
		public String toString() {
			return "Device [country=" + country + ", date=" + date
					+ ", category=" + category + ", deviceId=" + deviceId + "]";
		}
				
	}
	
	public class DevGrp {
		String country;
		String date;
		String category;
	
		public DevGrp(String c, String d, String ct) {
			country = c;
			date = d;
			category = ct;
		}

		@Override
		public String toString() {
			return "DevGrp [country=" + country + ", date=" + date
					+ ", category=" + category + "]";
		}		
		
		public boolean equals(Object o) {
			DevGrp d = (DevGrp) o;
			if (country.equals(d.country) && date.equals(d.date) && category.equals(d.category))
				return true;
			return false;
		}
		
		@Override 
		public int hashCode() {
			return country.hashCode()+date.hashCode()+category.hashCode();
		}
	}
	
	public static void main(String[] args) {
		
		GroupBy gb = new GroupBy();
		
		Map<DevGrp, List<Device>> map = new HashMap<DevGrp, List<Device>>();
		
		List<Device> list = new ArrayList<Device>();
		
		list.add(gb.new Device("USA", "1/1/2014", "CAT-A", "ABC123"));
		list.add(gb.new Device("USA", "1/1/2014", "CAT-A", "ABC456"));
		list.add(gb.new Device("UK", "2/2/2014", "CAT-B", "XYZ123"));
		list.add(gb.new Device("UK", "2/2/2014", "CAT-B", "XYZ123"));
		list.add(gb.new Device("IND", "3/3/2014", "CAT-C", "ABC789"));
		list.add(gb.new Device("IND", "3/3/2014", "CAT-D", "ABC321"));
		
		for (Device d : list) {
			
			DevGrp dg = gb.new DevGrp(d.country, d.date, d.category);
			// If map doesn't contain the key then create the new list and PUT it
			// else just get the list and add the element
			if (!map.containsKey(dg)) {
				List<Device> list2 = new ArrayList<Device>();
				list2.add(d);
				map.put(dg, list2);
			} else {
				List<Device> list2 = map.get(dg);
				list2.add(d);				
			}			
		}
		
		for (Map.Entry<DevGrp, List<Device>> e : map.entrySet()) {
			System.out.println("Key="+e.getKey().toString());
			System.out.println("Value="+e.getValue().toString());
		}
	}

}

