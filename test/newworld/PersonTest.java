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

	@Test
	public void setFirstname() {
		Person p = new Person();
		p.setFirstname("Fred");
		assertEquals("Fred", p.getFirstname());
	}
	
	@Test
	public void setLastname() {
		Person p = new Person();
		p.setLastname("Andre");
		assertEquals("Andre", p.getLastname());
	}
	
	@Test
	public void setAddress1() {
		Person p = new Person();
		p.setAddress1("1 Street");
		assertEquals("1 Street", p.getAddress1());
	}
	
	@Test
	public void setAddress2() {
		Person p = new Person();
		p.setAddress2("Area");
		assertEquals("Area", p.getAddress2());
	}
	
	@Test
	public void setPostcode() {
		Person p = new Person();
		p.setPostcode("SR1 1RS");
		assertEquals("SR1 1RS", p.getPostcode());
	}
	
	public static class HydratePersonTests
	{
		@Test
	    public void hydratePerson() {
			Person p = new Person();
			Person.PersonHydrator ph = p.new PersonHydrator();
			GenericCdo data = new GenericCdo();
			GenericCdo.PersonGenerator pg = data.new PersonGenerator();
			
			data.setFname("Fred");
			data.setLname("Andre");
			ph.hydrate(pg);
			
			assertEquals("Fred", p.getFirstname());
			assertEquals("Andre", p.getLastname());
		}
	}
	
	public static class HydratePersonCdoTests
	{
		@Test
	    public void hydratePerson() {
			Person p = new Person();
			Person.PersonHydrator ph = p.new PersonHydrator();
			CombinedCdo data = new CombinedCdo();
			CombinedCdo.PersonGenerator pg = data.new PersonGenerator();
			
			data.setFname("Fred");
			data.setLname("Andre");
			ph.hydrate(pg);
			
			assertEquals("Fred", p.getFirstname());
			assertEquals("Andre", p.getLastname());
		}
	}

}
