package fse.assesment.assignment.response;

import java.util.List;

import fse.assesment.assignment.entity.ParentTask;

public class ParentTaskListResponse extends Response {
	
	private List<ParentTask> parentTaskList;

	public List<ParentTask> getParentTaskList() {
		return parentTaskList;
	}

	public void setParentTaskList(List<ParentTask> parentTaskList) {
		this.parentTaskList = parentTaskList;
	}
	
	

	

}
