package fse.assesment.assignment.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fse.assesment.assignment.response.Response;

@Entity
@Table(name = "parent_task")
@JsonIgnoreProperties
public class ParentTask extends Response {

	private Long id;
	
	
    private String parentTask;


   
    
   // private Set<Task> tasks;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="parenttask")
	public String getParentTask() {
		return parentTask;
	}


	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	//@OneToMany(cascade= CascadeType.ALL, mappedBy = "parent")
	/*
	 * public Set<Task> getTasks() { return tasks; }
	 * 
	 * public void setTasks(Set<Task> tasks) { this.tasks = tasks; }
	 */
    
    
}
