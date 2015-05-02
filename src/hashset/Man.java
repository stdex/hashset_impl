package hashset;

public class Man {
	private int age;
	private String name;
	private int height;

	public Man(int age, String name, int height) {
		this.age = age;
		this.name = name;
		this.height = height;
	}

	public int getAge() {
		return this.age;
	}

	public String getName() {
		return this.name;
	}

	public int getHeight() {
		return this.height;
	}

	public int setAge(int age) {
		return this.age = age;
	}
	
	@Override
	public String toString() {//необязательный метод, добавлен для удобного вывода данных на консоль
		
		return name;
	}
	
}
