import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class CovidData {
	Hashtable<String,Integer> data;
	public CovidData(String filename) {
		data = new Hashtable<String,Integer>();
		try {
			Scanner in = new Scanner(new File("src/"+filename));
			// Add your code here
			while (in.hasNext()) {
				String [] str = in.nextLine().split(",");
				data.put(str[0], Integer.parseInt(str[1]));
			}						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	public int find(String d) {
		// Add your code here
		if (data.containsKey(d)) {
			return data.get(d);
		} else {
			return -1;
		}
	}
}
