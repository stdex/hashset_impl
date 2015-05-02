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
	private static final Object PRESENT = new Object(); // так как в HashMap
														// данные кладутся
														// парами
														// "ключ-значение",

	// в качестве ключей будем использовать наши объекты типа Е, а в качестве
	// значений - единственный объект PRESENT

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
		return map.keySet().iterator(); // возвращает итератор по элементам
										// хешсета
	}

	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	public boolean add(E e) {
		return map.put(e, PRESENT) == null; // если map.put() возвращает не
											// null, значит такой объект уже
											// содержится в HashMap
	}

	public boolean remove(Object o) {
		return map.remove(o) == PRESENT; // если объект успешно удален, HashMap
											// возвращает его "значение".
		// У нас все значения - PRESENT
	}

	public void clear() {
		map.clear();
	}
	
}
