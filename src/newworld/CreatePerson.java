package newworld;

public class CreatePerson {

	public static void main(String[] args) {
		Person p1 = new Person.Builder()
				.setSex("M")
				.setSalary(25000)
				.build();
				
		Person p2 = new Person.Builder()
				.setAge(30)
				.setAddress("22 Acadia Avenue")
				.build();
				
		System.out.println("Person 1: " + p1.toString());
		System.out.println("Person 2: " + p2.toString());
	}
	
	

}
