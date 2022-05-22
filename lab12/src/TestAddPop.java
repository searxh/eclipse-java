import java.io.*;
import java.util.Random;

public class TestAddPop {
	public static void main(String [] args) throws Exception {
		FileWriter writer = new FileWriter(new File("src/plot.csv"));
		Heap h = new Heap();
		int [] result = TestAdd(100, h);
		int [] result1 = TestPop(100, h);
		int [] result2 = TestAdd(10000, h);
		int [] result3 = TestPop(10000, h);
		int [] result4 = TestAdd(1000000, h);
		int [] result5 = TestPop(1000000, h);
		writer.append(result[0]+","+result[1]+","+result1[1]);
		writer.append(System.lineSeparator());
		writer.append(result2[0]+","+result2[1]+","+result3[1]);
		writer.append(System.lineSeparator());
		writer.append(result4[0]+","+result4[1]+","+result5[1]);
		writer.append(System.lineSeparator());
		writer.flush();
		writer.close();
	}
	public static int[] TestAdd(int n, Heap h) throws Exception {
		Random rnd = new Random();
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			int randint = rnd.nextInt();
			h.add(randint);
		}
		long end = System.currentTimeMillis();
		System.out.println("adding n = "+n);
		System.out.println("time taken: "+(end-start)+" ms");
		int [] result = {n,(int)(end-start)};
		return result;
	}
	public static int[] TestPop(int n, Heap h) throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			h.pop();
		}
		long end = System.currentTimeMillis();
		System.out.println("popping n = "+n);
		System.out.println("time taken: "+(end-start)+" ms");
		int [] result = {n,(int)(end-start)};
		return result;
	}
}
