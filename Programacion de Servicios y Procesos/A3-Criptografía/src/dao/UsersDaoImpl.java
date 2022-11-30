package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Users;

public class UsersDaoImpl implements IntUsersDao{

	private ArrayList<Users> list;
	
	public UsersDaoImpl() {
		list = new ArrayList<Users>();
		loadingUsersList();
	}
	
	private void loadingUsersList(){
		list.add(new Users("Paco","�N����'Ry:�LUNS�쬃��J�O���h�j(A��=�Z)�Vba�O���S��ڕ��"));//asd
		list.add(new Users("Juan","%/��5�O�c�٠K�<:Vv|�ր��{mvu�'Pw}��v�AX��2���SH�\bU�n5�FiN�E&"));//gas
		list.add(new Users("Manolo","r��W>.��l#��qP$�������$(���θ�/���`��k���\b'zQ@��ĉ���I��"));//zx
	}
	
	@Override
	public List<Users> userList() {
		return list;
	}
	
	@Override
	public String findByName(String name) {
		for (Users user : list) {
			if (user.getName().equals(name)) {
				return user.getName();
			}
		}
		return null;
	}
	
	public String getCon(String name) {
		for (Users user : list) {
			if (user.getName().equals(name)) {
				return user.getPassword();
			}
		}
		return null;
	}
}
