package fse.test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.catalina.mbeans.UserMBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import fse.assesment.assignment.common.AppConstants;
import fse.assesment.assignment.entity.ParentTask;
import fse.assesment.assignment.entity.Project;
import fse.assesment.assignment.entity.Task;
import fse.assesment.assignment.entity.User;
import fse.assesment.assignment.exception.AppException;
import fse.assesment.assignment.exception.ErrorCodes;
import fse.assesment.assignment.repository.ParentTaskRepository;
import fse.assesment.assignment.repository.ProjectManagerRepository;
import fse.assesment.assignment.repository.TaskManagerRepository;
import fse.assesment.assignment.repository.UserRepository;
import fse.assesment.assignment.response.ParentTaskListResponse;
import fse.assesment.assignment.response.ProjectListResponse;
import fse.assesment.assignment.response.Response;
import fse.assesment.assignment.response.TaskListResponse;
import fse.assesment.assignment.response.UserListResponse;
import fse.assesment.assignment.service.ProjectManagerService;
import fse.assesment.assignment.service.TaskManagerService;
import fse.assesment.assignment.service.UserService;

/**
 * 
 * @author Javed
 *
 */
	
	@RunWith(MockitoJUnitRunner.class)
	public class ProjectManagerTest {

		 private SimpleDateFormat formate=new SimpleDateFormat("dd/MM/yyyy");
		 
		@Mock
		private ProjectManagerRepository projectManagerRepository;

		@Mock
		private UserRepository userRepository;

		@Mock
		private ParentTaskRepository parentTaskRepository;

		@Mock
		private TaskManagerRepository taskRepository;



		@InjectMocks
		private ProjectManagerService projectManagerService;
		
		@InjectMocks
		private TaskManagerService taskManagerService;
		
		@InjectMocks
		private UserService userService;

		@Before
		public void setUp() throws SQLException {

		Mockito.when(parentTaskRepository.findAll()).thenReturn(getAllParentTasksMockReturn());
		Mockito.when(projectManagerRepository.findAll()).thenReturn(getAllProjectsMockReturn());
		Mockito.when(userRepository.findAll()).thenReturn(getAllUsersMockReturn());
		
		Mockito.when(taskRepository.findAll()).thenReturn(getAllTasksMockReturn());
		
		Mockito.when(projectManagerRepository.findOne(1L)).thenReturn(getProject());
		
		Mockito.when(userRepository.findOne(1L)).thenReturn(getUser());
		Mockito.when(taskRepository.findOne(1L)).thenReturn(getTask());
		Mockito.when(parentTaskRepository.findOne(1L)).thenReturn(getParentTask());
		
		Mockito.when(userRepository.save(any(User.class))).thenReturn(getUserObject());
		Mockito.when(taskRepository.save(any(Task.class))).thenReturn(getTask());
		
		Mockito.when(projectManagerRepository.save(any(Project.class))).thenReturn(getProjectObject());
		
		
		
		Mockito.when(userRepository.findByEmployeeId("EMP2115")).thenReturn(getUser());
		Mockito.when(projectManagerRepository.findByProject("FSE1")).thenReturn(getProject());

		Mockito.when(parentTaskRepository.findByParentTask("Parent Task 1")).thenReturn(getParentTask());
		
		Mockito.when(taskRepository.findByTask("Task-T 1")).thenReturn(getTask());
		
		
		
		}
		
		public Project getProject() {
			return getAllProjectsMockReturn().get(0);
		}
		
		public User getUser() {
			return getAllUsersMockReturn().get(0);
		}
		
		public Task getTask() {
			return getAllTasksMockReturn().get(0);
		}
		
		public ParentTask getParentTask() {
			return getAllParentTasksMockReturn().get(0);
		}

		
		public List<Project> getAllProjectsMockReturn() {

			Project proj1 = new Project();
			proj1.setId(1L);
			proj1.setProject("FSE1");
			proj1.setPriority(5);
			proj1.setStartDate(new Date());
			proj1.setEndDate(new Date());
			proj1.setManager(getUser());
			Set<Task> tasks=new HashSet<Task>();


			Task task = new Task();
			task.setId(1L);
			task.setTask("Task-Mock 1");
			task.setStartDate(new Date());
			task.setEndDate(new Date());
			task.setPriority(20);
			tasks.add(task);
			proj1.setTasks(tasks);

			Project proj2 = new Project();
			proj2.setId(2L);
			proj2.setProject("FSE2");
			proj2.setPriority(10);
			proj2.setStartDate(new Date());
			proj2.setEndDate(new Date());
			proj2.setTasks(tasks);
			
			List<Project> projects = Arrays.asList(proj1, proj2);
			return projects;
		}

		public List<User> getAllUsersMockReturn() {

			User user = new User();
			user.setId(1L);
			user.setFirstName("javed");
			user.setLastName("sheikh");
			user.setEmployeeId("EMP2115");

			List<User> users = Arrays.asList(user);
			return users;
		}

		public List<ParentTask> getAllParentTasksMockReturn() {

			ParentTask parentTask = new ParentTask();
			parentTask.setId(1L);
			parentTask.setParentTask("Parent Task 1");

			List<ParentTask> parentTasks = Arrays.asList(parentTask);
			return parentTasks;
		}

		public List<Task> getAllTasksMockReturn() {

			Task task = new Task();
			task.setId(1L);
			task.setTask("Task-T 1");
			task.setStartDate(new Date());
			task.setEndDate(new Date());
			task.setPriority(20);

			task.setProject("FSE1");
			task.setProjectId(1L);
			task.setUserId(1L);
			List<Task> parentTasks = Arrays.asList(task);
			return parentTasks;
		}

		
		@Test
		public void testGetAllProjects() {

			
					 ProjectListResponse response=(ProjectListResponse) projectManagerService.searchAllProject();
					 List<Project> projectList =response.getProjectList();
			 int actSize=projectList.size();
			int expSize = 2;

			assertThat(actSize).isEqualTo(expSize);
		}

	
	@Test
	public void testGetAllUsers() {
		
		UserListResponse response=(UserListResponse) userService.searchAllUser();

		List<User> userList = response.getUserList();
		int actSize = userList.size();
		int expSize = 1;

		assertThat(actSize).isEqualTo(expSize);
	}

	@Test
	public void testGetAllParentTasks() {

		ParentTaskListResponse response=(ParentTaskListResponse) taskManagerService.searchAllParentTask();
		List<ParentTask> parentTaskList = response.getParentTaskList();
		int actSize = parentTaskList.size();
		int expSize = 1;

		assertThat(actSize).isEqualTo(expSize);
	}

	@Test
	public void testGetAllTasks() {

		TaskListResponse response=(TaskListResponse)  taskManagerService.searchAllTask();
		List<Task> taskList = response.getTaskList();
		int actSize = taskList.size();
		int expSize = 1;

		assertThat(actSize).isEqualTo(expSize);
	}

	@Test
	public void testAddUser() {
		userService.addUser(getUserObject());
	}

	public User getUserObject() {
		User user = new User();
		user.setFirstName("Javed");
		user.setLastName("Abdulmajid");
		user.setEmployeeId("EMP2110");
		return user;
	}
	
	@Test
	public void testUpdateUser() {

		String updateString = "Shekh";
		User user = (User) userService.searchUserByEmployeeId("EMP2115");

		user.setLastName(updateString);

		User updatedUser = (User) userService.updateUser(user);

		assertThat(updateString).isEqualTo(updatedUser.getLastName());
	}
	
	@Test
	public void testUpdateUserWithNull() {

		try {
			 userService.updateUser(null);
			}catch(AppException e) {
				assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
			}
		

	}
	
	@Test
	public void testAddUserWithNull() {

		try {
			 userService.addUser(null);
			}catch(AppException e) {
				assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
			}
		

	}
	
	@Test
	public void testAddExistingUser() {

		try {
			userService.addUser(getUser());
			}catch(AppException e) {
				assertThat(ErrorCodes.USER_ALREADY_EXIST).isEqualTo(e.getErrorCode());
			}
		

	}

	@Test
	public void testAddProject() {

		projectManagerService.addProject(getProjectObject());
	}

	public Project getProjectObject() {
		

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
		project.setManagerId(getUser().getId());
		
		return project;
	}
	@Test
	public void testUpdateProject() {

		String startDt = "25/10/2018";
		String endDt = "18/12/2019";

		Project project = projectManagerService.searchProjectByName("FSE1");

		try {
			project.setStartDate(formate.parse(startDt));
			project.setEndDate(formate.parse(endDt));
		} catch (Exception e) {
			e.printStackTrace();
		}
		project.setPriority(23);

		Project updatedProject = (Project) projectManagerService.updateProject(project);
		assertThat(23).isEqualTo(updatedProject.getPriority());
		assertThat(0).isEqualTo(updatedProject.getNoOfTask());
		assertThat(0).isEqualTo(updatedProject.getTaskCompleted());
	}
	
	@Test
	public void testAddProjectWithNull() {

		try {
			 projectManagerService.addProject(null);
		} catch (AppException e) {
			assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
		}
	}
	
	@Test
	public void testUpdateProjectWithNull() {

		

		try {
			 projectManagerService.updateProject(null);
		} catch (AppException e) {
			assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
		}
		
	}

	@Test
	public void testAddParentTask() {

		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask("TestParent Task 1");

		taskManagerService.addParentTask(parentTask);
	}
	
	@Test
	public void testAddParentTaskWithNull() {
		
		try {
			taskManagerService.addParentTask(null);
			}catch(AppException e) {
				assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
			}
	}
	
	@Test
	public void testAddParentTaskExisting() {

		ParentTask parentTask = new ParentTask();
		parentTask.setParentTask("Parent Task 1");

		try {
			taskManagerService.addParentTask(parentTask);
			}catch(AppException e) {
				assertThat(ErrorCodes.PARENT_TASK_ALREADY_EXIST).isEqualTo(e.getErrorCode());
			}
	}
	
	@Test
	public void testAddTask() {

		String startDt = "25/10/2018";
		String endDt = "10/12/2019";

		Task task = new Task();
		task.setTask("Test Task 1");

		task.setPriority(10);
		try {
			task.setStartDate(formate.parse(startDt));
			task.setEndDate(formate.parse(endDt));
		} catch (Exception e) {
			e.printStackTrace();
		}

		User user = (User) userService.searchUserByEmployeeId("EMP2115");
		task.setUserId(user.getId());

		Project project = projectManagerService.searchProjectByName("FSE1");
		task.setProjectId(project.getId());
		task.setProject(project.getProject());
		task.setStatus(false);

		ParentTask parent = taskManagerService.findByParentTask("Parent Task 1");
		task.setParentTask(parent.getParentTask());

		taskManagerService.addTask(task);
	}

	@Test
	public void testUpdateTask() {

		Task task = taskManagerService.searchByTask("Task-T 1");

		task.setTask("Updated Task Test");

		task.setPriority(16);
		task.setStatus(true);

		Task updatedTask = (Task) taskManagerService.updateTask(task);
		updatedTask.setStatus("SUCCESS");
		updatedTask.setSuccessMessage(AppConstants.UPDATE_SUCCESS_MESSAGE);

		assertThat("Updated Task Test").isEqualTo(updatedTask.getTask());
		String startDt=formate.format(updatedTask.getStartDate());
		assertThat(formate.format(new Date())).isEqualTo(startDt);
	}
	@Test
	public void testUpdateTaskWithNull() {

		try {
		taskManagerService.updateTask(null);
			}catch(AppException e) {
				assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
			}
		
			
	}
	
	@Test
	public void testEndTask() {

		Task task = taskManagerService.searchByTask("Task-T 1");

		
		Task updatedTask = (Task) taskManagerService.endTask(task.getId());

		assertThat(false).isEqualTo(updatedTask.isStatus());
		
		assertThat(false).isEqualTo(updatedTask.isStatus());
	}

	@Test
	public void testSearchTaskById() {

		Task task =(Task) taskManagerService.searchById(1L);

		assertThat(1L).isEqualTo(task.getId());
		assertThat(20).isEqualTo(task.getPriority());
		
	}
	
	@Test
	public void testSearchUserById() {

		User user =(User) userService.searchUserByID(1L);

		assertThat("EMP2115").isEqualTo(user.getEmployeeId());
		assertThat("javed").isEqualTo(user.getFirstName());
	}
	
	@Test
	public void testSearchUserByWrongId() {

		try {
		userService.searchUserByID(10L);
		}catch(AppException e) {
			assertThat(ErrorCodes.NO_RECORDS).isEqualTo(e.getErrorCode());
		}
		
	}
	
	
	@Test
	public void testSearchUserByNullEmployeeId() {

		try {
		userService.searchUserByEmployeeId(null);
		}catch(AppException e) {
			assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
		}
		
	}

	@Test
	public void testSearchProjectById() {

		Project project =(Project) projectManagerService.searchProjectByID(1L);

		assertThat("FSE1").isEqualTo(project.getProject());
		String startDt = formate.format(new Date());
		String endDt = formate.format(new Date());
		
		assertThat(startDt).isEqualTo(formate.format(project.getStartDate()));
		assertThat(endDt).isEqualTo(formate.format(project.getEndDate()));
	}
	
	@Test
	public void testSearchProjectByIdwithNull() {

		try {
			projectManagerService.searchProjectByID(0);
			}catch(AppException e) {
				assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
			}
		

		
	}
	
	@Test
	public void testSearchProjectByProjectwithNull() {

		try {
			projectManagerService.searchProjectByName(null);
			}catch(AppException e) {
				assertThat(ErrorCodes.NO_RECORDS).isEqualTo(e.getErrorCode());
			}
		

		
	}

	
	@Test
	public void testDeleteUser() {
		User user = (User) userService.searchUserByEmployeeId("EMP2115");
		userService.deleteUser(user.getId());
	}

	@Test
	public void testDeleteTask() {
		Task task = taskManagerService.searchByTask("Task-T 1");
		taskManagerService.deleteTask(task.getId());
	}

	@Test
	public void testDeleteProject() {
		Project project = projectManagerService.searchProjectByName("FSE1");
		projectManagerService.deleteProject(project.getId());
	}
	 
	@Test
	public void testDeleteUserWithNull() {
		
		try {
			userService.deleteUser(0);
			}catch(AppException e) {
				assertThat(ErrorCodes.INVALID_REQUEST).isEqualTo(e.getErrorCode());
			}
		
	}

	
	 
	}
