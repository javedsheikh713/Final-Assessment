package fse.assesment.assignment.service;

import java.util.ArrayList;
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
import fse.assesment.assignment.repository.ParentTaskRepository;
import fse.assesment.assignment.repository.ProjectManagerRepository;
import fse.assesment.assignment.repository.TaskManagerRepository;
import fse.assesment.assignment.repository.UserRepository;
import fse.assesment.assignment.response.ParentTaskListResponse;
import fse.assesment.assignment.response.Response;
import fse.assesment.assignment.response.TaskListResponse;

@Service
public class TaskManagerService {

	Logger log = LoggerFactory.getLogger(TaskManagerService.class);
	
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	@Autowired
	private ProjectManagerRepository projectManagerRepository;
	
	@Autowired
	private TaskManagerRepository taskManagerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public Response addTask(Task task) {
		log.info("Start Method Add task");
		
		if(Objects.isNull(task)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		Task existingTask=taskManagerRepository.findByTask(task.getTask());
		if(Objects.nonNull(existingTask)) {
			throw new AppException(ErrorCodes.TASK_ALREADY_EXIST);
		}
		
		ParentTask parent=null;
		
		  if(Objects.nonNull(task.getParentTask())) { 
			  parent=parentTaskRepository.findByParentTask(task.getParentTask()); 
			  
		  }
		 
		  if(Objects.isNull(parent)) {
			  parent=new ParentTask();
			  //parent.setParentTask(task.getParentTask());
		  }
		
		parent.setParentTask(task.getParentTask());
		
		Project project=null;
		if(Objects.nonNull(task.getProject())) { 
			  project=projectManagerRepository.findByProject(task.getProject()); 
			  
		  }
		 
		  if(Objects.isNull(project)) {
			  throw new AppException(ErrorCodes.INVALID_PROJECT);
		  }
		  task.setProjectId(project.getId());
		  
		  User user=userRepository.findOne(task.getUserId());
		
		  if(Objects.isNull(user)) {
			  throw new AppException(ErrorCodes.MANDATORY_FIELD_MISSING);
		  }
		//Set<Task> children = new HashSet<Task>();
	   // children.add(task);
		//parent.setTasks(children);
		
		//ParentTask newParentTask=parentTaskRepository.save(parent);
		//task.setParentID(newParentTask.getId());
		task.setParent(parent);
		Task newTask=taskManagerRepository.save(task);
		
		
		user.setTaskId(newTask.getId());
		userRepository.save(user);
		
		task.setSuccessMessage(AppConstants.ADD_SUCCESS_MESSAGE);
		log.info("Finish Method Add task");
		return task;
	}
	
	public Response updateTask(Task task) {
		log.info("Start Method update task");
		
		if(Objects.isNull(task)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		ParentTask parent=null;
		if(Objects.nonNull(task.getParentTask())) { 
			  parent=parentTaskRepository.findByParentTask(task.getParentTask()); 
			  
		  }
		 

		task.setParent(parent);
		taskManagerRepository.save(task);
		task.setSuccessMessage(AppConstants.UPDATE_SUCCESS_MESSAGE);
		log.info("Finish Method update task");
		return task;
	}
	
	
	
	public Response searchAllTask() {
		log.info("Start Method searchAllTask task");
		TaskListResponse response=new TaskListResponse();
		List<Task> taskList=taskManagerRepository.findAll();
		if(Objects.isNull(taskList) || taskList.isEmpty()) {
			throw new AppException(ErrorCodes.NO_RECORDS);
		}
		
		List<Task> newtaskList=new ArrayList<Task>();
		for (Task task : taskList) {
			
		  User usr=	userRepository.findByTaskId(task.getId());
		  
		  task.setUser(usr);
		
		  Project prj=projectManagerRepository.findOne(task.getProjectId());
			task.setProject(prj.getProject());
			
			newtaskList.add(task);
		}
		response.setTaskList(newtaskList);
		log.info("Finish method  getAllTask in service {} ",newtaskList.toString());
		return response;
	}
	
	public Response searchAllParentTask() {
		log.info("Start Method searchAllParentTask task");
		ParentTaskListResponse response=new ParentTaskListResponse();
		List<ParentTask> parentTaskList=parentTaskRepository.findAll();
		if(Objects.isNull(parentTaskList) || parentTaskList.isEmpty()) {
			throw new AppException(ErrorCodes.NO_RECORDS);
		}
		response.setParentTaskList(parentTaskList);
		log.info("Finish method  searchAllParentTask in service {} ",parentTaskList.toString());
		return response;
	}
	
	public Response endTask(long id) {
		
			log.info("Start Method endTask in service");
		
		Task task=taskManagerRepository.findOne(id);
		task.setStatus(true);
		
		log.info("Finish Method endTask in service");
		
		return taskManagerRepository.save(task);
	}
	
	public Response searchById(long id) {
		log.info("Start Method search task by id");
		Task response=new Task();
		response=taskManagerRepository.findOne(id);
		
		
		User usr=	userRepository.findByTaskId(response.getId());
		  
		response.setUser(usr);
		
		  Project prj=projectManagerRepository.findOne(response.getProjectId());
		  response.setProject(prj.getProject());
	
		
		log.info("Finish method  searchById in service {} ");
		return response;
	}
	
	public Response addParentTask(ParentTask task) {
		log.info("Start Method Add Parent task");
		
		if(Objects.isNull(task)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		ParentTask existingTask=parentTaskRepository.findByParentTask(task.getParentTask());
		if(Objects.nonNull(existingTask)) {
			throw new AppException(ErrorCodes.PARENT_TASK_ALREADY_EXIST);
		}
		
		
		
		parentTaskRepository.save(task);
		task.setSuccessMessage(AppConstants.ADD_SUCCESS_MESSAGE);
		log.info("Finish Method Add task");
		return task;
	}
	
	public ParentTask findByParentTask(String parentTask) {
		
		return  parentTaskRepository.findByParentTask(parentTask); 
		
	}
	
	public Task searchByTask(String task) {
		return taskManagerRepository.findByTask(task);
	}
	
	public void deleteTask(long id) {
		taskManagerRepository.delete(id);
	}
	
}
