package fse.assesment.assignment.response;

import java.util.List;

import fse.assesment.assignment.entity.Task;

public class TaskListResponse extends Response {
	
	private List<Task> taskList;

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	

}
