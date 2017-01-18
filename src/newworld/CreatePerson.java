package newworld;

public class CreatePerson {

	public static void main(String[] args) {
		CDOA cdoA = new CDOA("M", 30);
		CDOB cdoB = new CDOB(25000, "22 Acadia Avenue");
		
		Person p1 = new Person.Builder()
				.setSex(cdoA.getSex())
				.setSalary(cdoB.getSalary())
				.build();
		
		Person p2 = new Person.Builder()
				.setSex(cdoA.getSex())
				.setAge(cdoA.getAge())
				.setSalary(cdoB.getSalary())
				.setAddress(cdoB.getAddress())
				.build();
		
		Person p3 = new Person.Builder(p1)
				.setAge(25)
				.build();
							
		System.out.println("Person 1: " + p1.toString());
		System.out.println("Person 2: " + p2.toString());
		System.out.println("Person 3: " + p3.toString());
	}	

}
