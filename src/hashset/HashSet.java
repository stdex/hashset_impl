package hashset;

/**
 * HashSet implementation
 * 
 * @author Tatiana Lopatkina
 * 
 * @param <E>
 */
public class HashSet<E> {

	static final int DEFAULT_INITIAL_CAPACITY = 16;
	static final int MAXIMUM_CAPACITY = 1 << 30;

	static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * The next size value at which to resize (capacity * load factor).
	 */
	int threshold;

	private float loadFactor;  //������������ ������� ������������� ������� - ��� ��� ���������� ������� �������������
	private int size; //������ ���-���� - ���������� ���������

	private Entry<E>[] table;  //���������� ��������� - ������ �����, � ������ ������ ��������, 
	//� ������� ������ ������������� ������� � ������� - ������ ��������� � ������ indexFor(hash, capacity)

	public HashSet() {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
	}

	public HashSet(int capacity) { //capacity= 2^k (������� ������)
		this.loadFactor = DEFAULT_LOAD_FACTOR;
		threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
	}

	public HashSet(int capacity, float loadfactor) {//capacity= 2^k (������� ������), 0<loadfactor<1
		this.loadFactor = loadfactor;
		threshold = (int) (capacity * loadfactor);
		table = new Entry[capacity];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	//�������� ���� �� ������ � �������
	public boolean contains(Object o) {
		if (o == null) return false;
		int hash = o.hashCode(); 
		//���� �� ���� ��������� � ������ � �������� ��� ������� ���-����
		for (Entry<E> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
			Object k;
			//�������� �� ���������������
			if (e.hash == hash
					&& ((k = e.e) == o || (o != null && o.equals(k))))
				return true;
		}
		return false;
	}

	//���������� ������� � ������
	public boolean add(E e) {
		if (e == null)
			return false;
		int hash = e.hashCode();
		int i = indexFor(hash, table.length); //����������� ������� �� ���-����

		//��������, ���� �� ������������� ������ � �������
		for (Entry<E> entry = table[i]; entry != null; entry = entry.next) {
			Object k;
			if (entry.hash == hash && ((k = entry.e) == e || e.equals(k))) {
				return false;
			}
		}
		//���� �������������� ������� ���, ����� ��������� 
		addEntry(hash, e, i);
		return true;
	}

	
	private void addEntry(int hash, E e, int i) {
		Entry<E> entry = table[i]; 
		table[i] = new Entry<>(hash, e, entry); //��������� � ������ ������ 
		if (size++ >= threshold)  // ���� ������������� ������� ��������� loadFactor(75%) �� ����������� �����
			resize(2 * table.length);

	}

	//�������� �������
	public boolean remove(Object o) {
		
		if (o == null) return false;
		int hash = o.hashCode();
		int i = indexFor(hash, table.length);
		Entry<E> prev = table[i]; 
		Entry<E> e = prev;

		//����� ������� � ������ � ���� ������, ��������
		while (e != null) {
			Entry<E> next = e.next;
			Object k;
			if (e.hash == hash
					&& ((k = e.e) == o || (o != null && o.equals(k)))) {
				size--;
				if (prev == e)
					table[i] = next;
				else
					prev.next = next;
				return true;
			}
			prev = e;
			e = next;
		}
		return false;
	}

	
	void resize(int newCapacity) {
		Entry<E>[] oldTable = table;
		int oldCapacity = oldTable.length;
		if (oldCapacity == MAXIMUM_CAPACITY) {
			threshold = Integer.MAX_VALUE;
			return;
		}

		Entry<E>[] newTable = new Entry[newCapacity];
		transfer(newTable); //��������� ��� ������ �� ������ ������� � �����
		table = newTable;
		threshold = (int) (newCapacity * loadFactor);
	}

	void transfer(Entry<E>[] newTable) {
		Entry<E>[] src = table;
		int newCapacity = newTable.length;
		for (int j = 0; j < src.length; j++) {
			Entry<E> e = src[j];
			if (e != null) {
				src[j] = null;
				do {
					Entry<E> next = e.next;
					int i = indexFor(e.hash, newCapacity);
					e.next = newTable[i];
					newTable[i] = e;
					e = next;
				} while (e != null);
			}
		}
	}

	//������� �������
	public void clear() {
		Entry<E>[] tab = table;
		for (int i = 0; i < tab.length; i++)
			tab[i] = null;
		size = 0;
	}

	//���������� ��������� � �������
	public int size() {
		return size;
	}

	/**
	 * Returns index for hash code h.
	 */
	static int indexFor(int h, int length) {
		return h & (length - 1);  //length = capacity - ������ �������� �������� 2�� (������� 16, ����� ������������� �����)
		//� �������� ������ ������� ������ ������ ���� 100..00, ������ length -1 = 11..11 (���-�� �����)
		//h & (length - 1) - ��������� ��������� - ������ ���� ����� 0 <= .. < length ���������� ������� �����
		//��� �������� �������, ��� ������� ������� �� �������, ��� ������������� ��� ���-��������
	}

	//�������������� �����, ����� ��� �������� ������ ������ �� ������� � ������ Test
	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("Hashset capacity = " + table.length +"\n");
		for (int i = 0; i < table.length; i++) {
			Entry<E> e = table[i];
			if (e != null) {
				sb.append(i + ":\n");
			}
			while (e != null) {
				sb.append("   " + e.toString() + "(h=" + e.hashCode() + ")\n");
				e = e.next;
			}

		}
		return sb.toString();
	}

	//����� - �������� ��� ������� ���� �, �������� ��� ������, ��� ���-���  � ������ �� ��������� ����� � ������ ������
	static class Entry<E> {
		Entry<E> next;
		E e;
		int hash;

		public Entry(int h, E e, Entry<E> entry) { //�������� ����� ��������� � ������������ ������, 
			//��������� ������ �� ����������, entry
			this.e = e;
			this.hash = h;
			this.next = entry;
		}

		public final boolean equals(Object o) {
			if (!(o instanceof Entry))
				return false;
			Entry<E> entry = (Entry<E>) o;
			Object k1 = e;
			Object k2 = entry.e;
			if (k1 == k2 || (k1 != null && k1.equals(k2))) {
				return true;
			}
			return false;
		}

		public final int hashCode() {
			return (e == null ? 0 : e.hashCode());
		}

		public final String toString() {
			return e.toString();
		}

	}
}
