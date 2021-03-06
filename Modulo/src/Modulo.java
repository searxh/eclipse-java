
public class Modulo {
	
	static int size = 11;
	static int maxCol = 5;
	public static void main(String [] args) {
		doubleH(16);
	}
	public static int hash(int x) {
		return x%size;
	}
	public static int hash2(int x) {
		return 5-(x%5);
	}
	
	
	
	public static void linear(int x) {
		System.out.println("\nLinear Probing");
		for (int i = 0; i <= maxCol; i++) {
			int val = hash(x)+i;
			System.out.println("i="+i+": "+val%size);
		}
	}
	public static void quadratic(int x) {
		System.out.println("\nQuadratic Probing");
		for (int i = 0; i <= maxCol; i++) {
			int val = hash(x)+(i*i);
			System.out.println("i="+i+": "+val%size);
		}
	}
	public static void doubleH(int x) {
		System.out.println("\nDouble Hashing");
		for (int i = 0; i <= maxCol; i++) {
			int val = hash(x)+i*hash2(x);
			System.out.println("i="+i+": "+val%size);
		}
	}
}
