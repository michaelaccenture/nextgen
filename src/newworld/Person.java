package newworld;

public class Person {

	private final String sex;
	private final int age;
	private final long salary;
	private final String address;

	private Person(Person.Builder builder){
		sex = builder.sex;
		age = builder.age;
		salary = builder.salary;
		address = builder.address;
	}
	
	public static class Builder {

		private String sex;
		private int age;
		private long salary;
		private String address;
		
		public Builder(){}
		
		public Builder(Person person){
			sex = person.sex;
			age = person.age;
			salary = person.salary;
			address = person.address;
		}

		public Builder setSex(String sex) {
			this.sex = sex;
			return this;
		}

		public Builder setAge(int age) {
			this.age = age;
			return this;
		}

		public Builder setSalary(long salary) {
			this.salary = salary;
			return this;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		public Person build() {
			return new Person(this);
		}

	}

	public String getSex() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public long getSalary() {
		return salary;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Person [sex=" + sex + ", age=" + age + ", salary=" + salary + ", address=" + address
				+ "]";
	}

}
