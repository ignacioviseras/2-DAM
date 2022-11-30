package beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Users {
	int id;
	String name, password;
	private static List<Users> list;
	
	
	public Users(int id, String name, String password) throws NoSuchAlgorithmException {
		this.id = id;
		this.name = name;
		this.password = password;
		loadingUsersList();
	}

	private void loadingUsersList() throws NoSuchAlgorithmException{
		list.add(new Users(1,"Paco","�N����'Ry:�LUNS�쬃��J�O���h�j(A��=�Z)�Vba�O���S��ڕ��"));//asd
		list.add(new Users(2,"Juan","��w�{�sX��F������6BM��F��UB=̿�q�z0��z1�{\r\n"
				+ ")�T�.S.�)� ��s1;�*"));//qwe
		list.add(new Users(3,"Manolo","�y��о�+Hś�I�_�����F�������Q�4�rHN�f�A[\r\n"
				+ "��R(���lR�"));//zxc
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
	
	
}
