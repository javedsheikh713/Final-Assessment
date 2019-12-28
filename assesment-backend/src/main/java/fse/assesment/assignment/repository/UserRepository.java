package fse.assesment.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fse.assesment.assignment.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmployeeId(String employeeId);
	public List<User> findByFirstNameContaining(String searchText);
	public User findByTaskId(long taskId);

}
