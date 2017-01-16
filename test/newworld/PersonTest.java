package newworld;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import newworld.CombinedCdo;
import newworld.GenericCdo;
import newworld.Person;

import org.junit.experimental.runners.Enclosed;

@RunWith(Enclosed.class)
public class PersonTest {
	
	private Person p;
	
	protected void setUp() {
		p = new Person();
	}

	@Test
	public void setFirstname() {
		p.setFirstname("Fred");
		assertEquals("Fred", p.getFirstname());
	}
	
	@Test
	public void setLastname() {
		p.setLastname("Andre");
		assertEquals("Andre", p.getLastname());
	}
	
	@Test
	public void setAddress1() {
		p.setAddress1("1 Street");
		assertEquals("1 Street", p.getAddress1());
	}
	
	@Test
	public void setAddress2() {
		p.setAddress2("Area");
		assertEquals("Area", p.getAddress2());
	}
	
	@Test
	public void setPostcode() {
		p.setPostcode("SR1 1RS");
		assertEquals("SR1 1RS", p.getPostcode());
	}
	
	public static class HydratePersonTests
	{
		@Test
	    public void hydratePerson() {
			GenericCdo data = new GenericCdo();
			data.setFname("Fred");
			data.setLname("Andre");
			Person p = data.new PersonGenerator().hydrate();
			
			assertEquals("Fred", p.getFirstname());
			assertEquals("Andre", p.getLastname());
		}
	}
	
	public static class HydratePersonCdoTests
	{
		@Test
	    public void hydratePerson() {
			CombinedCdo data = new CombinedCdo();
			data.setFname("Steve");
			data.setLname("Davis");
			Person p = data.new PersonGenerator().hydrate();
			
			assertEquals("Steve", p.getFirstname());
			assertEquals("Davis", p.getLastname());
		}
	}

}
