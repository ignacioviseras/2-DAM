package beans;

import java.io.Serializable;

public class Users implements Serializable{
	private static final long serialVersionUID = 1L;
	String name, password;
	
	public Users(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Users [name=" + name + ", password=" + password + "]";
	}
	
	
}
