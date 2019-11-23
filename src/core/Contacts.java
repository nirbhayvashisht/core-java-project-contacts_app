package core;
public class Contacts {

	//private int id;
	private String firstName;
	private String lastName;
	private String email;
	private int number;
	
	public Contacts(/*int id,*/ String firstName, String lastName, String email,
			int number) {
		super();
		//this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.number = number;
	}

	/*public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}*/

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return String
				.format("Contacts [ firstName=%s,lastName=%s,  email=%s, number=%s]",
						firstName, lastName,  email, number);
	}

}
