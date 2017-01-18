package newworld;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

	@Test
    public void createPerson() {
		Person.Builder b = new Person.Builder();
		
		Person.Builder b2 = b.setAge(30);
		assertEquals(b, b2);
		b.setAddress("22 Acadia Avenue");
		
		Person p = b.build();
		assertNotNull(p);
		
		assertEquals(30, p.getAge());
		assertEquals("22 Acadia Avenue", p.getAddress());
		
		assertNull(p.getSex());
		assertEquals(0, p.getSalary());
	}

}
