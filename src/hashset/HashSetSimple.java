package hashset;

import hashset.HashSet.Entry;

import java.util.HashMap;
import java.util.Iterator;

/**
 * A simple implementation of a HashSet using a HashMap class.
 * 
 * @author Tatiana Lopatkina
 * 
 * @param <E>
 */
public class HashSetSimple<E> {

	private HashMap<E, Object> map;
	private static final Object PRESENT = new Object(); // ��� ��� � HashMap
														// ������ ��������
														// ������
														// "����-��������",

	// � �������� ������ ����� ������������ ���� ������� ���� �, � � ��������
	// �������� - ������������ ������ PRESENT

	/**
	 * default constructor
	 */
	public HashSetSimple() {
		map = new HashMap<>();
	}

	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Iterator<E> iterator() {
		return map.keySet().iterator(); // ���������� �������� �� ���������
										// �������
	}

	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	public boolean add(E e) {
		return map.put(e, PRESENT) == null; // ���� map.put() ���������� ��
											// null, ������ ����� ������ ���
											// ���������� � HashMap
	}

	public boolean remove(Object o) {
		return map.remove(o) == PRESENT; // ���� ������ ������� ������, HashMap
											// ���������� ��� "��������".
		// � ��� ��� �������� - PRESENT
	}

	public void clear() {
		map.clear();
	}
	
}
