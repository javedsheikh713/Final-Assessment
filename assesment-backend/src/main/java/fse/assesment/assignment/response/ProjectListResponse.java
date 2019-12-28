package fse.assesment.assignment.response;

import java.util.List;

import fse.assesment.assignment.entity.Project;

public class ProjectListResponse extends Response {
	
	private List<Project> projectList;

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	
	
	

}
