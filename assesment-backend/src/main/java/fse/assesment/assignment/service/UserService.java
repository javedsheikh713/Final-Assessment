package fse.assesment.assignment.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fse.assesment.assignment.common.AppConstants;
import fse.assesment.assignment.entity.ParentTask;
import fse.assesment.assignment.entity.Project;
import fse.assesment.assignment.entity.Task;
import fse.assesment.assignment.entity.User;
import fse.assesment.assignment.exception.AppException;
import fse.assesment.assignment.exception.ErrorCodes;
import fse.assesment.assignment.repository.ProjectManagerRepository;
import fse.assesment.assignment.repository.TaskManagerRepository;
import fse.assesment.assignment.repository.UserRepository;
import fse.assesment.assignment.response.ProjectListResponse;
import fse.assesment.assignment.response.Response;
import fse.assesment.assignment.response.TaskListResponse;
import fse.assesment.assignment.response.UserListResponse;

@Service
public class UserService {

Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectManagerRepository projectManagerRepository;
	
	@Autowired
	private TaskManagerRepository taskManagerRepository;
	
	
	public Response addUser(User user) {
		log.info("Start Method Add User");
		
		if(Objects.isNull(user)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		User existingUser=userRepository.findByEmployeeId(user.getEmployeeId());
		if(Objects.nonNull(existingUser)) {
			throw new AppException(ErrorCodes.USER_ALREADY_EXIST);
		}
		
		Task task=null;
		
		  if(Objects.nonNull(user.getTaskId()) && user.getTaskId() > 0) { 
			  task=taskManagerRepository.findOne(user.getTaskId());
			  
			  if(Objects.isNull(task)) {
				  throw new AppException(ErrorCodes.INVALID_TASK);
			  }
			  
			  user.setTaskId(task.getId());
			  
		  }
		 
		  
		
				
		/*
		 * Project project=null; if(Objects.nonNull(user.getProjectId()) &&
		 * user.getProjectId() >0) {
		 * project=projectManagerRepository.findOne(user.getProjectId());
		 * if(Objects.isNull(project)) { throw new
		 * AppException(ErrorCodes.INVALID_PROJECT); }
		 * 
		 * user.setProject(project); }
		 */
		 
		  
		
		
		
		User newUser=userRepository.save(user);
		newUser.setSuccessMessage(AppConstants.ADD_SUCCESS_MESSAGE);
		log.info("Finish Method Add User");
		return newUser;
	}
	
	public Response updateUser(User user) {
		log.info("Start Method update user");
		
		if(Objects.isNull(user)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		
		userRepository.save(user);
		user.setSuccessMessage(AppConstants.UPDATE_SUCCESS_MESSAGE);
		log.info("Finish Method update project");
		return user;
	}
	
	public Response searchUserByID(long id) {
		log.info("Start Method search user by id");
		
		User user=userRepository.findOne(id);
		
		if(Objects.isNull(user)) {
			throw new AppException(ErrorCodes.NO_RECORDS);
		}
		return user;
		
	}
	
	public Response searchUserByEmployeeId(String employeeId) {
		log.info("Start Method searchUserByEmployeeId");
		if(Objects.isNull(employeeId)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST,false);
		}
		return userRepository.findByEmployeeId(employeeId);
		
	}
	
	public Response searchAllUser() {
		log.info("Start Method searchAllUser ");
		UserListResponse response=new UserListResponse();
		List<User> userList=userRepository.findAll();
		if(Objects.isNull(userList) || userList.isEmpty()) {
			throw new AppException(ErrorCodes.NO_RECORDS);
		}
		response.setUserList(userList);
		
		log.info("Finish method  searchAllUser in service {} ",userList.toString());
		return response;
	}
	
	public Response deleteUser(long id) {
		log.info("Start Method deleteUser ");
		if(Objects.isNull(id) || id==0) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		userRepository.delete(id);
		
		log.info("Finish Method deleteUser ");
		return searchAllUser();
	}
	
	
	
	
}
