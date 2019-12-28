package fse.assesment.assignment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fse.assesment.assignment.response.Response;

@Entity
@Table(name = "user")
@JsonIgnoreProperties
public class User extends Response {
	
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	//private Long parentID;
    private String employeeId; 
  //  private Project project;
  //  private Task task;
   
    private Long projectId;
    private Long taskId;
   
    public User() {
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
	
	


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="project_id")
	 * 
	 * public Project getProject() { return project; }
	 * 
	 * 
	 * public void setProject(Project project) { this.project = project; }
	 */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="task_id")
	 * 
	 * public Task getTask() { return task; }
	 * 
	 * 
	 * public void setTask(Task task) { this.task = task; }
	 */

	//@OneToOne(mappedBy = "manager",targetEntity = Project.class)
	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}



	public Long getTaskId() {
		return taskId;
	}


	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	


    

}
