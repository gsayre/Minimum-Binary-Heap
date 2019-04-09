package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will initially
												// be null. This is ok! Just build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for simplicity
													// of child/parent computations...
													// the book/animation page both do this.
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		int index = size + 1;
		array[index] = entry;
		size++;

		boolean hasParent = Math.floor(index / 2) >= 1;
		EntryPair thirdCup;

		while (hasParent) {
			if (array[index].getPriority() < array[(int) Math.floor(index / 2)].getPriority()) {
				thirdCup = array[index];
				array[index] = array[(int) Math.floor(index / 2)];
				array[(int) Math.floor(index / 2)] = thirdCup;
				index = (int) Math.floor(index / 2);
			} else {
				break;
			}
		}

	}

	@Override
	public void delMin() {
		if (size == 0) {
			return;
		}
		
		array[1] = array[size];
		array[size] = null;
		
		int index = 1;
		EntryPair Lchild = array[index * 2];
		EntryPair Rchild = array[(index * 2) + 1];
		boolean hasLChild = (Lchild != null);
		boolean hasRChild = (Rchild != null);
		EntryPair thirdCup;

		while (hasRChild || hasLChild) {

			if (Rchild == null && Lchild == null) {
				break;
			}
			if (Rchild == null) {
				if (array[index].getPriority() > Lchild.getPriority()) {
					thirdCup = array[index];
					array[index] = Lchild;
					array[index * 2] = thirdCup;
					break;
				} else {
					break;
				}
			}

			if (Lchild.getPriority() > Rchild.getPriority()) {
				if (array[index].getPriority() > Rchild.getPriority()) {
					thirdCup = array[index];
					array[index] = Rchild;
					array[(index * 2) + 1] = thirdCup;
					index = (index * 2) + 1;
				} else {
					break;
				}
			} else {
				if (array[index].getPriority() > Lchild.getPriority()) {
					thirdCup = array[index];
					array[index] = Lchild;
					array[index * 2] = thirdCup;
					index = index * 2;
				} else {
					break;
				}
			}
			if (index * 2 > 9999) {
				break;
			}
			Lchild = array[index * 2];
			Rchild = array[(index * 2) + 1];
			hasLChild = (Lchild != null);
			hasRChild = (Rchild != null);
		}
		size--;
	}

	@Override
	public EntryPair getMin() {
		if (size == 0) {
			return null;
		}else {
			return array[1];
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		EntryPair thirdCup;

		for (int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i];
		}

		for (int i = (int) Math.floor(entries.length / 2); i >= 1; i--) {
			int index = i;
			EntryPair Lchild = array[index * 2];
			EntryPair Rchild = array[(index * 2) + 1];
			boolean hasLChild = (Lchild != null);
			boolean hasRChild = (Rchild != null);

			while (hasRChild || hasLChild) {

				if (Rchild == null && Lchild == null) {
					break;
				}
				if (Rchild == null) {
					if (array[index].getPriority() > Lchild.getPriority()) {
						thirdCup = array[index];
						array[index] = Lchild;
						array[index * 2] = thirdCup;
						break;
					} else {
						break;
					}
				}

				if (Lchild.getPriority() > Rchild.getPriority()) {
					if (array[index].getPriority() > Rchild.getPriority()) {
						thirdCup = array[index];
						array[index] = Rchild;
						array[(index * 2) + 1] = thirdCup;
						index = (index * 2) + 1;
					} else {
						break;
					}
				} else {
					if (array[index].getPriority() > Lchild.getPriority()) {
						thirdCup = array[index];
						array[index] = Lchild;
						array[index * 2] = thirdCup;
						index = index * 2;
					} else {
						break;
					}
				}
				if (index * 2 > 9999) {
					break;
				}
				Lchild = array[index * 2];
				Rchild = array[(index * 2) + 1];
				hasLChild = (Lchild != null);
				hasRChild = (Rchild != null);
			}

		}
		size = entries.length;
	}
}