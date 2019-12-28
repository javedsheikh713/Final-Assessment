package fse.assesment.assignment;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import fse.assesment.assignment.TaskManager;
import fse.assesment.assignment.entity.ParentTask;
import fse.assesment.assignment.entity.Project;
import fse.assesment.assignment.entity.Task;
import fse.assesment.assignment.entity.User;
import fse.assesment.assignment.response.ParentTaskListResponse;
import fse.assesment.assignment.response.ProjectListResponse;
import fse.assesment.assignment.response.TaskListResponse;
import fse.assesment.assignment.response.UserListResponse;
import fse.assesment.assignment.service.ProjectManagerService;
import fse.assesment.assignment.service.TaskManagerService;
import fse.assesment.assignment.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class ProjectManagerResourceTest {

	
	 
	 
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectManagerService projectManagerService;
	
	@MockBean
	private TaskManagerService taskManagerService;
	
	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testGetProjects() throws Exception {

		Project project = GetObject.getProjectObject();
		List<Project> projects = Arrays.asList(project);
		
		ProjectListResponse response=new ProjectListResponse();
		response.setProjectList(projects);
		given(projectManagerService.searchAllProject()).willReturn(response);

		String json=mapper.writeValueAsString(response);

		this.mockMvc.perform(get("/getallproject"))
					.andExpect(content().json(json));

	}
	


	@Test
	public void testGetParentTasks() throws Exception {

		// given
		ParentTask parentTask =GetObject.getParentTaskObject();


		List<ParentTask> parentTasks = Arrays.asList(parentTask);
		ParentTaskListResponse parentTaskListResponse=new ParentTaskListResponse();
		parentTaskListResponse.setParentTaskList(parentTasks);
		given(taskManagerService.searchAllParentTask()).willReturn(parentTaskListResponse);

		String json=mapper.writeValueAsString(parentTaskListResponse);
		
		this.mockMvc.perform(get("/getallparenttask"))
					.andExpect(content().json(json));

	}
	
	

	@Test
	public void testGetTasks() throws Exception {

		Task task=GetObject.getTaskObject();

		List<Task> tasks = Arrays.asList(task);
		TaskListResponse taskListResponse=new TaskListResponse();
		taskListResponse.setTaskList(tasks);
		given(taskManagerService.searchAllTask()).willReturn(taskListResponse);

		String json=mapper.writeValueAsString(taskListResponse);
		
		this.mockMvc.perform(get("/getalltask"))
					.andExpect(content().json(json));

	}

	@Test
	public void testGetUsers() throws Exception {


		User user = GetObject.getUserObject();
		
		List<User> users = Arrays.asList(user);
		UserListResponse userListResponse=new UserListResponse();
		userListResponse.setUserList(users);
		given(userService.searchAllUser()).willReturn(userListResponse);

		String json=mapper.writeValueAsString(userListResponse);
		
		this.mockMvc.perform(get("/getalluser"))
					.andExpect(content().json(json));

	}
	
	@Test
	public void testSearchUserById() throws Exception {


		User user = GetObject.getUserObject();
		
		
		given(userService.searchUserByID(1)).willReturn(user);

		String json=mapper.writeValueAsString(user);
		
		this.mockMvc.perform(get("/searchuser/1"))
					.andExpect(content().json(json));

	}
	
	@Test
	public void testSearchUserByWrongId() throws Exception {


		User user = GetObject.getUserObject();
		
		
		given(userService.searchUserByID(1)).willReturn(user);

		String json=mapper.writeValueAsString(user);
		
		this.mockMvc.perform(get("/searchuser/10"))
					.andExpect(status().isOk());

	}
	
	@Test
	public void testSearchProjectById() throws Exception {


		Project project = GetObject.getProjectObject();
		
		
		given(projectManagerService.searchProjectByID(1)).willReturn(project);

		String json=mapper.writeValueAsString(project);
		
		this.mockMvc.perform(get("/searchproject/1"))
					.andExpect(content().json(json));

	}
	
	@Test
	public void testSearchUserByEmployeeId() throws Exception {


		User user = GetObject.getUserObject();
		
		
		given(userService.searchUserByEmployeeId("EMP2110")).willReturn(user);

		String json=mapper.writeValueAsString(user);
		
		this.mockMvc.perform(get("/searchbyemployeeid/EMP2110"))
					.andExpect(content().json(json));

	}
	
	@Test
	public void testSearchTaskById() throws Exception {


		Task task = GetObject.getTaskObject();
		
		
		given(taskManagerService.searchById(1L)).willReturn(task);

		String json=mapper.writeValueAsString(task);
		
		this.mockMvc.perform(get("/searchbyid/1"))
					.andExpect(content().json(json));

	}

	
	@Test 
	public void testAddUser() throws Exception {
	  
	 User user=GetObject.getUserObject();
	  
	 given(userService.addUser(user)).willReturn(user);

	  String json = mapper.writeValueAsString(user);
	  
		/*
		 * RequestBuilder request = MockMvcRequestBuilders .post("/adduser")
		 * .accept(MediaType.APPLICATION_JSON) .content(json)
		 * .contentType(MediaType.APPLICATION_JSON);
		 * 
		 * this. mockMvc.perform(request) .andExpect(status().isOk());
		 */
	           
		
		  this.mockMvc.perform(post("/adduser")
		  .contentType(MediaType.APPLICATION_JSON) .content("{\"id\":1,\"firstName\":\"Javed\",\"lastName\":\"Abdulmajid\",\"employeeId\":\"EMP2110\"}"))
		  .andDo(print())
		  .andExpect(status().isOk());
		 
	  }

	@Test 
	public void testAddProject() throws Exception {
	  
	 
	  
	  String json = mapper.writeValueAsString(GetObject.getProjectObject());
	  
	 
	  this.mockMvc.perform(post("/addproject")
	  .contentType(MediaType.APPLICATION_JSON) .content("{\"project\":\"Project1\",\"startDate\":\"2018-10-21\",\"endDate\":\"2019-12-21\",\"priority\":1,\"noOfTask\":0,\"taskCompleted\":0,\"manager\":null,\"managerId\":1}")
	  .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isOk());
	   }

	@Test 
	
	public void addParentTask() throws Exception {
	  
	    
	 // String json = mapper.writeValueAsString(GetObject.getParentTaskObject());
	  
	  
	  this.mockMvc.perform(post("/addparenttask")
	  .contentType(MediaType.APPLICATION_JSON) .content("{\"id\":1,\"parentTask\":\"Parent Task 1\"}")
	  .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isOk());
	 }

	@Test 
	public void addTask() throws Exception {
	  
	 // String json = mapper.writeValueAsString(GetObject.getTaskObject());
	  
	  
	  this.mockMvc.perform(post("/addtask")
	  .contentType(MediaType.APPLICATION_JSON) .content("{\"status\":false,\"id\":1,\"projectId\":1,\"project\":\"FSE1\",\"userId\":1,\"user\":null,\"task\":\"Task-T 1\",\"startDate\":\"2019-12-27\",\"endDate\":\"2019-12-27\",\"priority\":20,\"parent\":null,\"parentTask\":null}")
	  .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isOk());
	 }

	

	@Test 
	
	public void testUpdateUser() throws Exception {
	  
		User user=GetObject.getUserObject();
		user.setLastName("Updated last name");
	
	  //String json = mapper.writeValueAsString(user);
	  

	  this.mockMvc.perform(post("/updateuser")
	  .contentType(MediaType.APPLICATION_JSON) .content("{\"id\":1,\"firstName\":\"Javed\",\"lastName\":\"Abdulmajid\",\"employeeId\":\"EMP2110\"}")
	  .accept(MediaType.APPLICATION_JSON)); 
	  }
	

	@Test 
	public void testUpdateProject() throws Exception {
	  
       Project project=GetObject.getProjectObject();
       project.setPriority(28);
	  
	  //String json = mapper.writeValueAsString(project);
	  
	  
	  this.mockMvc.perform(post("/updateproject")
	  .contentType(MediaType.APPLICATION_JSON) .content("{\"project\":\"Project1\",\"startDate\":\"2018-10-21\",\"endDate\":\"2019-12-21\",\"priority\":1,\"noOfTask\":0,\"taskCompleted\":0,\"manager\":null,\"managerId\":1}")
	  .accept(MediaType.APPLICATION_JSON)); 
	  
	}

	@Test
	public void updateTask() throws Exception {

		Task task=GetObject.getTaskObject();
		task.setStatus(true);

		//String json = mapper.writeValueAsString(task);

		this.mockMvc.perform(post("/updatetask").contentType(MediaType.APPLICATION_JSON)
				.content("{\"status\":false,\"id\":1,\"projectId\":1,\"project\":\"FSE1\",\"userId\":1,\"user\":null,\"task\":\"Task-T 1\",\"startDate\":\"2019-12-27\",\"endDate\":\"2019-12-27\",\"priority\":20,\"parent\":null,\"parentTask\":null}").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		// @formatter:on
	}
	
	@Test
	public void updateEndTask() throws Exception {

		Task task=GetObject.getTaskObject();
		task.setStatus(true);

		given(taskManagerService.endTask(1L)).willReturn(task);

		String json=mapper.writeValueAsString(task);
		
		this.mockMvc.perform(get("/endtask/1"))
					.andExpect(content().json(json));

	}
	
@Test
	
	public void deleteUser() throws Exception {
	  
		  this.mockMvc.perform(get("/deleteuser/1")
	  .accept(MediaType.APPLICATION_JSON));
	  }
	 
}
