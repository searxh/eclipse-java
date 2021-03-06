import java.util.Random;

public class TestHeap {
	public static void main(String [] args) {
		int n=5;
		Heap h = new Heap();
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			h.add(rnd.nextInt(12));
		}
		System.out.println(h.isAHeap(h));
		printer(h.mData);
		h.changeData(1,5);
		System.out.println(h.isAHeap(h));
		printer(h.mData);
		
		int [] nonHeap = new int[10];
		for (int i=0; i < nonHeap.length; i++) {
			nonHeap[i] = rnd.nextInt(100);
		}
		printer(nonHeap);
		printer(h.makeHeap(nonHeap));
	}
	public static void printer(int [] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]+",");
		}
		System.out.println("");
	}
}
