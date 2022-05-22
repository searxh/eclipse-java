import static org.junit.Assert.*;

import org.junit.Test;

public class TestMaxHeap {

	@Test
	public void test() throws Exception {
		int[] x = {20,40,10,5,100,79,26,30};
		Heap h = new MaxIntHeap();
		for(int i=0;i<x.length;i++) 
			h.add(x[i]);
		assertEquals(100,h.pop());
		assertEquals(79,h.pop());
		assertEquals(40,h.pop());
		assertEquals(30,h.pop());
		assertEquals(26,h.pop());
		assertEquals(20,h.pop());
		assertEquals(10,h.pop());
		assertEquals(5,h.pop());
	}

}
