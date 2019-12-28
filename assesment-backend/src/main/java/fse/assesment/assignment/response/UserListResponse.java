package fse.assesment.assignment.response;

import java.util.List;

import fse.assesment.assignment.entity.Project;
import fse.assesment.assignment.entity.User;

public class UserListResponse extends Response {
	
	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	
	
	
	

}
