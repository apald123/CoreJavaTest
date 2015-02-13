import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileRead {
	
	public static void readWrite(String filename, String delim) throws IOException {
		File file = new File(filename);
		BufferedReader buf = new BufferedReader(new FileReader(file));
		String line;	
		List<String> bigList = new ArrayList<String>();
		Map<String, List<String>> map = new HashMap<>();
		while ((line = buf.readLine()) != null) {
			String[] arr = line.split(delim);			
			bigList.addAll(Arrays.asList(arr));
		}
	}

}
