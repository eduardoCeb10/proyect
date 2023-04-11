package org.ditalia.bbb.security;

import java.util.Iterator;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;

public class CustomUserDetails implements User {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFullName(String fullName) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Group> getGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Role> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDatabase getUserDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRole(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isInGroup(Group group) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInRole(Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeGroups() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRole(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRoles() {
		// TODO Auto-generated method stub

	}

}
