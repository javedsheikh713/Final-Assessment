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
import fse.assesment.assignment.repository.ProjectManagerRepository;
import fse.assesment.assignment.repository.UserRepository;
import fse.assesment.assignment.response.ProjectListResponse;
import fse.assesment.assignment.response.Response;
import fse.assesment.assignment.response.TaskListResponse;

@Service
public class ProjectManagerService {

Logger log = LoggerFactory.getLogger(ProjectManagerService.class);
	
	@Autowired
	private ProjectManagerRepository projectManagerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public Response addProject(Project project) {
		log.info("Start Method Add Project");
		
		if(Objects.isNull(project)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		Project existingProject=searchProjectByName(project.getProject());
		if(Objects.nonNull(existingProject)) {
			throw new AppException(ErrorCodes.PROJECT_ALREADY_EXIST);
		}
		
		if(Objects.isNull(project.getManagerId())) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		//update manager
		User user=userRepository.findOne(project.getManagerId());
		if(Objects.isNull(user)){
			throw new AppException(ErrorCodes.MANAGER_NOT_EXIST);
		}
		
		
		Project newProject=projectManagerRepository.save(project);
		
		user.setProjectId(newProject.getId());
		userRepository.save(user);
		newProject.setSuccessMessage(AppConstants.ADD_SUCCESS_MESSAGE);
		log.info("Finish Method Add Project");
		return newProject;
	}
	
	public Response updateProject(Project project) {
		log.info("Start Method update project");
		
		if(Objects.isNull(project)) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		
		
		projectManagerRepository.save(project);
		project.setSuccessMessage(AppConstants.UPDATE_SUCCESS_MESSAGE);
		log.info("Finish Method update project");
		return project;
	}
	
	public Response searchProjectByID(long id) {
		log.info("Start Method search project by id");
		if(Objects.isNull(id) || id==0) {
			throw new AppException(ErrorCodes.INVALID_REQUEST);
		}
		return projectManagerRepository.findOne(id);
		
	}
	
	public Response searchAllProject() {
		log.info("Start Method searchAllProject ");
		ProjectListResponse response=new ProjectListResponse();
		List<Project> projectList=projectManagerRepository.findAll();
		if(Objects.isNull(projectList) || projectList.isEmpty()) {
			throw new AppException(ErrorCodes.NO_RECORDS);
		}
		List<Project> updatedList=new ArrayList<Project>();
		for (Project project : projectList) {
			int taskCompleted=0;
			project.setNoOfTask(project.getTasks().size());
			
			if(project.getTasks()!=null && !project.getTasks().isEmpty()) {
				for(Task task: project.getTasks()) {
					if(task.isStatus()) {
						taskCompleted++;
					}
					
					project.setTaskCompleted(taskCompleted);
				}
			}
			
			updatedList.add(project);
		}
		response.setProjectList(updatedList);
		
		log.info("Finish method  getAllTask in service {} ",updatedList.toString());
		return response;
	}
	
	public Project searchProjectByName(String projectName) {
		log.info("Start Method searchProjectByName ");
		
		if(Objects.isNull(projectName)) {
			throw new AppException(ErrorCodes.NO_RECORDS);
		}
		
		log.info("Finish Method searchProjectByName ");
		
		return projectManagerRepository.findByProject(projectName);
	}
	
	public void deleteProject(long id) {
		projectManagerRepository.delete(id);
	}
	
}
