package newworld;

public class GenericCdo {

	private String fname;
	private String lname;
	private String add1;
	private String add2;
	private String post_code;
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getPost_code() {
		return post_code;
	}
	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public class PersonGenerator implements PersonData
	{
		private Person p;
		
		public PersonGenerator(){
			p = new Person();
		}
		
		@Override
		public String getFirstname() {
			return getFname();
		}

		@Override
		public String getLastname() {
			return getLname();
		}

		@Override
		public String getAddress1() {
			return getAdd1();
		}

		@Override
		public String getAddress2() {
			return getAdd2();
		}

		@Override
		public String getPostcode() {
			return getPost_code();
		}

		@Override
		public Person hydrate() {
			p.setFirstname(getFirstname());
	    	p.setLastname(getLastname());
	    	p.setAddress1(getAddress1());
	    	p.setAddress2(getAddress2());
	    	p.setPostcode(getPostcode());
	    	
	    	return p;
		}
	}
}
