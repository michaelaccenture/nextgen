package newworld;

public class Person {

	private String firstname;
	private String lastname;
	private String address1;
	private String address2;
	private String postcode;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public class PersonHydrator
	{
		public void hydrate(PersonData p) {
			setFirstname(p.getFirstname());
	    	setLastname(p.getLastname());
	    	setAddress1(p.getAddress1());
	    	setAddress2(p.getAddress2());
	    	setPostcode(p.getPostcode());
			
		}
	}
	
}
