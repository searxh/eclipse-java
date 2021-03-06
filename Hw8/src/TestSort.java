import java.util.Random;
public class TestSort {
	public static void main(String [] args) throws Exception {

		int n=32000000;
		
		CDLinkedList l1 = new CDLinkedList();
		Random rnd = new Random(1234567);
		for (int i=0; i < n; i++) {
			l1.insert(rnd.nextInt(n),new DListIterator(l1.header));
		}
		//printer(l1); //prints unsorted
		System.out.println("sorting...");
		long startTime = System.nanoTime();
		l1.sort();
		long endTime = System.nanoTime();
		//printer(l1); //prints sorted
		System.out.println("time taken: "+((endTime-startTime)/1e9)+" seconds");
	}
	public static void printer(CDLinkedList l) throws Exception {
		DListIterator itr = new DListIterator(l.header.nextNode);
		System.out.println("CDLinkedList: ");
		while (itr.currentNode != l.header) {
			System.out.println(itr.currentNode.data+",");
			itr.next();
		}
		System.out.println("");
	}
}
