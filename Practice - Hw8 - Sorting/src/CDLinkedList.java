import java.util.Random;

public class CDLinkedList {
	DListNode header;
	int size;
	static final int HEADERVALUE = -9999999;

	public CDLinkedList() {
		header = new DListNode(HEADERVALUE);
		makeEmpty();// necessary, otherwise next/previous node will be null
	}

	public boolean isEmpty() {
		return header.nextNode == header;
	}

	public boolean isFull() {
		return false;
	}

	/** make the list empty. */
	public void makeEmpty() {
		header.nextNode = header;
		header.previousNode = header;
		size = 0;
	}

	// put in new data after the position of p.
	public void insert(int value, Iterator p) throws Exception {
		if (p == null || !(p instanceof DListIterator))
			throw new Exception();
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			throw new Exception();

		DListIterator p3 = new DListIterator(p2.currentNode.nextNode);
		DListNode n = new DListNode(value, p3.currentNode, p2.currentNode);
		p2.currentNode.nextNode = n;
		p3.currentNode.previousNode = n;
		size++;
	}

	// return position number of value found in the list.
	// otherwise, return -1.
	public int find(int value) throws Exception {
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				return -1;
			if (v == value)
				return index; // return the position of value.
		}
		return -1;
	}

	// return data stored at kth position.
	public int findKth(int kthPosition) throws Exception {
		if (kthPosition < 0 || kthPosition > size - 1)
			throw new Exception();// exit the method if the position is
		// beyond the first/last possible
		// position, throwing exception in the process.
		DListIterator itr = new DListIterator(header);
		int index = -1;
		while (itr.hasNext()) {
			int v = itr.next();
			index++;
			if (itr.currentNode == header)
				throw new Exception();
			if (index == kthPosition)
				return v;
		}
		throw new Exception();
	}

	// Return iterator at position before the first position that stores value.
	// If the value is not found, return null.
	public Iterator findPrevious(int value) throws Exception {
		if (isEmpty())
			return null;
		Iterator itr1 = new DListIterator(header);
		Iterator itr2 = new DListIterator(header);
		int currentData = itr2.next();
		while (currentData != value) {
			currentData = itr2.next();
			itr1.next();
			if (((DListIterator) itr2).currentNode == header)
				return null;
		}
		return itr1;
	}

	// remove content at position just after the given iterator. Skip header if
	// found.
	public void remove(Iterator p) {
		if (isEmpty())
			return;
		if (p == null || !(p instanceof DListIterator))
			return;
		DListIterator p2 = (DListIterator) p;
		if (p2.currentNode == null)
			return;
		if (p2.currentNode.nextNode == header)
			p2.currentNode = header;
		if (p2.currentNode.nextNode == null)
			return;
		DListIterator p3 = new DListIterator(p2.currentNode.nextNode.nextNode);
		p2.currentNode.nextNode = p3.currentNode;
		p3.currentNode.previousNode = p2.currentNode;
		size--;
	}

	// remove the first instance of the given data.
	public void remove(int value) throws Exception {
		Iterator p = findPrevious(value);
		if (p == null)
			return;
		remove(p);
	}

	// remove data at position p.
	// if p points to header or the list is empty, do nothing.
	public void removeAt(Iterator p) throws Exception {
		if (isEmpty() || p == null || !(p instanceof DListIterator) || ((DListIterator) p).currentNode == null
				|| ((DListIterator) p).currentNode == header)
			return;

		DListIterator p2 = (DListIterator) (findPrevious(p));
		remove(p2);

	}

	// Print each contact out, one by one.
	// To be completed by students.
	public void printList() throws Exception {
		Iterator itr = new DListIterator(header);
		while (itr.hasNext()) {
			Object data = itr.next();

			System.out.println(data);

		}
	}

	public int size() throws Exception {
		return size;
	}

	// return iterator pointing to location before position.
	public Iterator findPrevious(Iterator position) throws Exception {
		if (position == null)
			return null;
		if (!(position instanceof DListIterator))
			return null;
		if (((DListIterator) position).currentNode == null)
			return null;

		DListIterator p = ((DListIterator) position);
		DListIterator p2 = new DListIterator(p.currentNode.previousNode);
		return p2;

	}
	
	// write the sort method below
	public void sort() throws Exception { // any sorting will do BUT you must use only 'this' list. No array or any other data structures allowed!!!
		//bubbleSort();
		//bubbleSortRecursive();
		//insertionSort();
		//insertionSortRecursive();
		//selectionSort();
		//selectionSortRecursive();
		quickSort();
		//mergeSort();
		
		//printer();
	}
	//----------------------------------------------------------------------------//
	public void bubbleSort() throws Exception {
		DListIterator itr = new DListIterator(header.nextNode);
		DListIterator itr1 = new DListIterator(header.nextNode.nextNode);
		boolean isSorted = false;
		for (int i = 0; i < size-1; i++) {
			isSorted = true;
			while (itr1.currentNode != header) {
				if (itr1.currentNode.data <= itr.currentNode.data) {
					int temp = itr.currentNode.data;
					itr.currentNode.data = itr1.currentNode.data;
					itr1.currentNode.data = temp;
					isSorted = false;
				}
				itr.next();
				itr1.next();
			}
			if (isSorted) break;
			itr = new DListIterator(header.nextNode);
			itr1 = new DListIterator(header.nextNode.nextNode);
		}
	}
	public void bubbleSortRecursive() throws Exception {
		bubbleSortR(header.nextNode,0);
	}
	public void bubbleSortR(DListNode n, int sorted) throws Exception { // max n = 158
		if (sorted==size || isEmpty()) return;
		if (n==header) {
			n = header.nextNode;
		}
		if (n.data > n.nextNode.data) {
			sorted = 0;
			if (n.nextNode != header) {
				int temp = n.data;
				n.data = n.nextNode.data;
				n.nextNode.data = temp;
			}
		}
		sorted++;
		bubbleSortR(n.nextNode,sorted);
	}
	//----------------------------------------------------------------------------//
	public void insertionSort() throws Exception {
		DListIterator current = new DListIterator(header.nextNode.nextNode);
		for (int i = 0; i < size-1; i++) {
			DListIterator itr = new DListIterator(header);
			while (itr.currentNode != current.currentNode) {
				if (current.currentNode.data < itr.currentNode.nextNode.data) {
					removeAt(current);
					insert(current.currentNode.data,itr);
					break;
				}
				itr.next();
			}
			current.next();
		}
	}
	public void insertionSortRecursive() throws Exception {
		insertionSortR(header.nextNode);
	}
	public void insertionSortR(DListNode n) throws Exception { // max ~ 26k
		if (n == header) return;
		DListNode insertHere = compare(header.nextNode,n);
		if (insertHere!=null) {
			removeAt(new DListIterator(n));
			insert(n.data,new DListIterator(insertHere));
		}
		insertionSortR(n.nextNode);
	}
	public DListNode compare(DListNode l, DListNode r) throws Exception {
		if (l==r) {
			return null;
		}
		if (r.data < l.data) {
			return l.previousNode;
		}
		return compare(l.nextNode,r);
	}
	//----------------------------------------------------------------------------//
	public void selectionSort() throws Exception {
		DListIterator itr = new DListIterator(header.nextNode);
		while (itr.currentNode != header) {
			DListIterator min = findMin(itr);
			int temp = itr.currentNode.data;
			itr.currentNode.data = min.currentNode.data;
			min.currentNode.data = temp;
			itr.next();
		}
	}
	public DListIterator findMin(DListIterator start) throws Exception {
		DListIterator itr = new DListIterator(start.currentNode);
		DListIterator minItr = new DListIterator(itr.currentNode);
		int min = itr.currentNode.data;
		while (itr.currentNode != header) {
			if (itr.currentNode.data < min) {
				min = itr.currentNode.data;
				minItr = new DListIterator(itr.currentNode);
			}
			itr.next();
		}
		return minItr;
	}
	public void selectionSortRecursive() throws Exception {
		selectionSortR(header.nextNode);
	}
	public void selectionSortR(DListNode n) throws Exception { // max ~ 27k
		if (n==header) return;
		DListNode min = findMin(n,n,n.data);
		int temp = n.data;
		n.data = min.data;
		min.data = temp;
		selectionSortR(n.nextNode);
	}
	public DListNode findMin(DListNode n, DListNode minNode, int min) throws Exception {
		if (n==header) return minNode;
		if (n.data < min) {
			minNode = n;
			min = n.data;
		}
		return findMin(n.nextNode,minNode,min);
	}
	//----------------------------------------------------------------------------//
	public void quickSort() throws Exception {
		partition(header,header);
	}
	public void partition(DListNode start, DListNode end) throws Exception {
		if (start.nextNode == end) return;
		DListIterator pivot = new DListIterator(end.previousNode);
		DListIterator l = new DListIterator(end.previousNode.previousNode);
		while (l.currentNode != start) {
			if (l.currentNode.data > pivot.currentNode.data) {
				removeAt(l);
				insert(l.currentNode.data,pivot);
			}
			l.previous();
		}
		partition(start,pivot.currentNode);
		partition(pivot.currentNode,end);
	}
	//----------------------------------------------------------------------------//
	public void mergeSort() throws Exception {
		divide(1,size);
	}
	public void divide(int start, int end) throws Exception {
		if (end-start > 0) {
			int mid = start+(end-start)/2;
			divide(start,mid);
			divide(mid+1,end);
			merge(start,mid,end);
		}
	}
	public void merge(int start, int mid, int end) throws Exception {
		int itrnum = 1;
		DListIterator itr = new DListIterator(header.nextNode);
		for (int i=1; i < start; i++) {
			itrnum++;
			itr.next();
		}
		int itr1num = itrnum;
		DListIterator itr1 = new DListIterator(itr.currentNode);
		for (int i=start; i < mid+1; i++) {
			itr1num++;
			itr1.next();
		}
		DListIterator itr2 = new DListIterator(itr1.currentNode);
		for (int i=mid+1; i < end; i++) {
			itr2.next();
		}
		DListIterator tail = new DListIterator(header.previousNode);
		DListIterator tailSave = new DListIterator(tail.currentNode);
		DListIterator itrSave = new DListIterator(itr.currentNode);
		while (itrnum <= mid && itr1num <= end) {
			if (itr1.currentNode.data >= itr.currentNode.data) {
				insert(itr.currentNode.data,tail);
				tail.next();
				itr.next();
				itrnum++;
			} else if (itr1.currentNode.data < itr.currentNode.data){
				insert(itr1.currentNode.data,tail);
				tail.next();
				itr1.next();
				itr1num++;
			}
		}
		while (itrnum <= mid) {
			insert(itr.currentNode.data,tail);
			tail.next();
			itr.next();
			itrnum++;
		}
		while (itr1num <= end) {
			insert(itr1.currentNode.data,tail);
			tail.next();
			itr1.next();
			itr1num++;
		}
		DListNode oldStartNode = itrSave.currentNode;
		DListNode oldEndNode = itr2.currentNode; 
		DListNode newStartNode = tailSave.currentNode.nextNode;
		DListNode newEndNode = header.previousNode;
		tailSave.currentNode.nextNode = newEndNode.nextNode;
		header.previousNode = tailSave.currentNode;
		newStartNode.previousNode = oldStartNode.previousNode;
		newEndNode.nextNode = oldEndNode.nextNode;
		oldStartNode.previousNode.nextNode = newStartNode;
		itrSave.currentNode.previousNode = null;
		oldEndNode.nextNode.previousNode = newEndNode;
		itr2.currentNode.nextNode = null;
	}
	//----------------------------------------------------------------------------//
	public static void main(String [] args) throws Exception {

		int n=1600000;
		
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