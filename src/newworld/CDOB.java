package newworld;

public class CDOB {
	private long salary;
	private String address;
	
	public CDOB(long salary, String address) {
		super();
		this.salary = salary;
		this.address = address;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
