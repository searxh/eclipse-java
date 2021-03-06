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
	public void bubbleSort() throws Exception {
		DListIterator left = new DListIterator(header.nextNode);
		DListIterator right = new DListIterator(header.nextNode.nextNode);
		boolean swapped = false;
		for (int i = 0; i < this.size-1; i++) {
			for (int j = 0; j < this.size-i-1; j++) {
				if (left.currentNode.data > right.currentNode.data) {
					swapped = true;
					int temp = right.currentNode.data;
                    right.currentNode.data = left.currentNode.data;
                    left.currentNode.data = temp;
				}
				left.next();
				right.next();
			}
			left = new DListIterator(header.nextNode);
			right = new DListIterator(header.nextNode.nextNode);
			if (swapped==false) {
				break;
			}
		}
	}
	public void insertionSort() throws Exception {
		DListIterator itr = new DListIterator(header.nextNode.nextNode);
		for (int i = 0; i < this.size-1; i++) {
			DListIterator itr1 = new DListIterator(itr.currentNode.previousNode);
			if (itr.currentNode.data < itr1.currentNode.data) {
				removeAt(itr);
				while (itr.currentNode.data < itr1.currentNode.data) {
					itr1.previous();
				}
				insert(itr.currentNode.data,itr1);
			}
			itr.next();
		}
	}
	public void selectionSort() throws Exception {
		DListIterator itr = new DListIterator(header.nextNode);
		for (int i = 0; i < this.size-1; i++) {
			DListIterator itr1 = findMin(itr);
			int temp = itr.currentNode.data;
            itr.currentNode.data = itr1.currentNode.data;
            itr1.currentNode.data = temp;
            itr.next();
		}
	}
	public DListIterator findMin(DListIterator start) throws Exception {
		DListIterator copy = new DListIterator(start.currentNode);
		DListIterator save = new DListIterator(header);
		int min = copy.currentNode.data;
		while (copy.currentNode != header) {
			if (copy.currentNode.data < min) {
				min = copy.currentNode.data;
				save = new DListIterator(copy.currentNode);
			}
			copy.next();
		}
		return save;
		
	}
	// write the sort method below
	public void sort() throws Exception { // any sorting will do BUT you must use only 'this' list. No array or any other data structures allowed!!!
		//System.out.print("\n BEFORE SORT ");
		//printer();
		//mergeSort(1,this.size);
		//bubbleSort();
		//insertionSort();
		//selectionSort();
		quickSort(header,header);
	}
	public void quickSort(DListNode l, DListNode r) throws Exception {
		DListIterator pivot = new DListIterator(r.previousNode);
		DListIterator right = new DListIterator(pivot.currentNode.previousNode);
		while (right.currentNode != l) {
			if (right.currentNode.data > pivot.currentNode.data) {
				removeAt(right);
				insert(right.currentNode.data,pivot);
			}
			right.previous();
		}
		if (l.nextNode != pivot.currentNode) {
			quickSort(l, pivot.currentNode);
		}
		if (r.previousNode != pivot.currentNode) {
			quickSort(pivot.currentNode, r);
		}
	}
	public void merge(int start, int end, int mid) throws Exception {
		//System.out.println("\nMERGING | start: "+start+" mid: "+mid+" end: "+end+"\n");
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
		//printer();
	}
	public void mergeSort(int start, int end) throws Exception {
		if (end-start > 0) {
			int mid = start+(end-start)/2;
			mergeSort(start,mid);
			mergeSort(mid+1,end);
			merge(start,end,mid);
		}
	}
	public void mergeSort1(int start, int end) throws Exception {
		if (end-start > 0) {
			int mid = start+(end-start)/2;
			DListIterator midItr = new DListIterator(header.nextNode);
			for (int i=0; i < mid; i++) {
				midItr.next();
			}
			CDLinkedList left = new CDLinkedList();
			left.header = header.nextNode;
			mergeSort(start,mid);
			mergeSort(mid+1,end);
			merge(start,end,mid);
		}
	}
	public void merge1(int start, int end, int mid) throws Exception {
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
		DListIterator itrSave = new DListIterator(itr.currentNode);
		CDLinkedList temp = new CDLinkedList();
		DListIterator tempItr = new DListIterator(temp.header.nextNode);
		while (itrnum <= mid && itr1num <= end) {
			if (itr1.currentNode.data >= itr.currentNode.data) {
				temp.insert(itr.currentNode.data,tail);
				tempItr.next();
				itr.next();
				itrnum++;
			} else if (itr1.currentNode.data < itr.currentNode.data){
				temp.insert(itr1.currentNode.data,tail);
				tempItr.next();
				itr1.next();
				itr1num++;
			}
		}
		while (itrnum <= mid) {
			temp.insert(itr.currentNode.data,tail);
			tempItr.next();
			itr.next();
			itrnum++;
		}
		while (itr1num <= end) {
			temp.insert(itr1.currentNode.data,tail);
			tempItr.next();
			itr1.next();
			itr1num++;
		}
	}
	public void printer() throws Exception {
		DListIterator itr = new DListIterator(header.nextNode);
		System.out.print("CDLinkedList: ");
		while (itr.currentNode != header) {
			System.out.print(itr.currentNode.data+",");
			itr.next();
		}
		System.out.println("");
	}
	public void print(DListNode a) throws Exception {
		System.out.println(a.data);
	}

}