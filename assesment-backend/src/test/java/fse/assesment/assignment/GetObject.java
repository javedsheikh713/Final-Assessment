package fse.assesment.assignment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import fse.assesment.assignment.common.AppConstants;
import fse.assesment.assignment.entity.ParentTask;
import fse.assesment.assignment.entity.Project;
import fse.assesment.assignment.entity.Task;
import fse.assesment.assignment.entity.User;
import fse.assesment.assignment.response.ParentTaskListResponse;
import fse.assesment.assignment.response.ProjectListResponse;
import fse.assesment.assignment.response.TaskListResponse;
import fse.assesment.assignment.response.UserListResponse;

public class GetObject {
	
	private static SimpleDateFormat formate=new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) throws Exception {
		
		getProjectObject();
		
		getParentTaskObject();
		
		getTaskObject();
		
		getUserObject();
	}
	
public static Project getProjectObject() throws Exception {
		

		String startDt = "22/10/2018";
		String endDt = "22/12/2019";
		

		Project project = new Project();
		project.setProject("Project1");
		try {
			project.setStartDate(formate.parse(startDt));
			project.setEndDate(formate.parse(endDt));
		} catch (Exception e) {
			e.printStackTrace();
		}
		project.setPriority(1);
		project.setManagerId(getUserObject().getId());
		project.setTasks(null);
		project.setId(1L);
		
		
		return project;
	}



public static ParentTask getParentTaskObject() throws Exception{
	ParentTask parentTask = new ParentTask();
	parentTask.setId(1L);
	parentTask.setParentTask("Parent Task 1");
	parentTask.setSuccessMessage(AppConstants.ADD_SUCCESS_MESSAGE);
	
	
	return parentTask;
}




public static Task getTaskObject() throws Exception {
	
	Task task = new Task();
	task.setId(1L);
	task.setTask("Task-T 1");
	task.setStartDate(new Date());
	task.setEndDate(new Date());
	task.setPriority(20);

	task.setProject("FSE1");
	task.setProjectId(1L);
	task.setUserId(1L);
	
	
	
	return task;
}



public static User getUserObject()  throws Exception{
	User user = new User();
	user.setId(1L);
	user.setFirstName("Javed");
	user.setLastName("Abdulmajid");
	user.setEmployeeId("EMP2110");
	user.setProjectId(1L);
	user.setTaskId(1L);
	
	UserListResponse response=new UserListResponse();
	List<User> userList=new ArrayList<User>();
	userList.add(user);
	response.setUserList(userList);
	ObjectMapper mapper=new ObjectMapper();
	System.out.println("user JSON :-- "+mapper.writeValueAsString(response)); 
	
	return user;
}

}
