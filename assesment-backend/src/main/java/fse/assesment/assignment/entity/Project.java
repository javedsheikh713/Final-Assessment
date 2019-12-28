package fse.assesment.assignment.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fse.assesment.assignment.response.Response;

@Entity
@Table(name = "project")
@JsonIgnoreProperties
public class Project extends Response {
	
	
	private Long id;
	
	
	//private Long parentID;
    private String project;
    
    private Set<Task> tasks;
   
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
   
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    private int priority;
    
    private int noOfTask;
    private int taskCompleted;
    private User manager;

    private long managerId;
    
    public Project() {
    	super();
    }
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	

	

	@OneToMany(mappedBy = "projectId")

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}


	@Transient
	public int getNoOfTask() {
		return noOfTask;
	}


	public void setNoOfTask(int noOfTask) {
		this.noOfTask = noOfTask;
	}


	@Transient
	public int getTaskCompleted() {
		return taskCompleted;
	}


	public void setTaskCompleted(int taskCompleted) {
		this.taskCompleted = taskCompleted;
	}


	//@OneToOne(mappedBy = "projectId",targetEntity = User.class)
	@Transient
	public User getManager() {
		return manager;
	}


	public void setManager(User manager) {
		this.manager = manager;
	}


	@Transient
	public long getManagerId() {
		return managerId;
	}


	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}


	
	
	 
		 

    

}
