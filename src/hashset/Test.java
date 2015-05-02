package hashset;

import java.io.EOFException;
import java.util.Iterator;

public class Test {

	public static void testHasSetSimple() {

		HashSetSimple<Man> people2 = new HashSetSimple<Man>();
		for (int i = 0; i < 6; i++) {
			people2.add(new Man(i, "Woman" + i, 180));
		}
		System.out.println("size = " + people2.size());

		Man woman = null;
		// � ���� ���������� ��� ����������� �������� �������, ���������
		// ���-������� � �� �����, ������ ��� ��� ��������������� � ������ HasMap
		// �� ���� ���� ��������� �� ���� ���������
		for (Iterator iterator = people2.iterator(); iterator.hasNext();) {
			woman = (Man) iterator.next();
			System.out.println(woman);
		}
		if (people2.contains(woman)){
			System.out.println("HashSet contains " + woman);
		}
		people2.remove(woman);
		System.out.println("After removing last element: size = " + people2.size());
		if (!people2.contains(woman)){
			System.out.println("Now HashSet doesn't contain " + woman);
		}

	}

	public static void main(String[] args) {

		testHashSet();

		System.out.println("*****************************");

		testHasSetSimple();

	}

	private static void testHashSet() {
		Man firstMan = new Man(18, "Vasya", 220);
		Man secondMan = new Man(20, "Viktoria", 165);

		firstMan.setAge(firstMan.getAge() + 2);
		secondMan.setAge(secondMan.getAge() + 2);

		//����� ���������, ����� ������ ��� HashSet, ������� F3, ����� ������ ��������� �� ����� ����� HashSet
		//��� ����� ������ Alt+Shift+R, ����� ������������� ����� (������ ���� ��������� �� ����� ����� HashSet)
		HashSet<Man> people1 = new HashSet<Man>();
		// ��������� 2 ��������
		people1.add(firstMan);
		people1.add(secondMan);
		// ����� �������
		System.out.println(people1);
		System.out.println("____________________________");
		// ������� ����
		people1.remove(firstMan);
		// ����� �������
		System.out.println(people1);
		System.out.println("____________________________");

		// ��������� 15 ��������� => ������ ���-������� ������ ����������� �����
		for (int i = 0; i < 15; i++) {
			people1.add(new Man(i, "Man" + i, 210));
		}

		// ����� �������
		System.out.println(people1);
	}

}
