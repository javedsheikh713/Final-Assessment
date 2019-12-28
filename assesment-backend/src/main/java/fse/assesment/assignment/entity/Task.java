package fse.assesment.assignment.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fse.assesment.assignment.response.Response;

@Entity
@Table(name = "task")
@JsonIgnoreProperties
public class Task extends Response {
	
	
	private Long id;
	
	private Long parentId;
	
	private Long projectId;
	
	private String project;
	
	private Long userId;
	
	private User user;
	
	//private Long parentID;
    private String task;
   
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
   
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    private int priority;
    
    
    private boolean status;
    
    
    private ParentTask parent;
    
 //   private Project project;
   
   

	private String parentTask;
    
    
    public Task() {
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
	
	

	/*
	 * @Column(name="parentid") public Long getParentID() { return parentID; }
	 * 
	 * public void setParentID(Long parentID) { this.parentID = parentID; }
	 */


	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
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


	
	 public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	@Transient
	public String getParentTask() {
		return parentTask;
	}


	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id")
	public ParentTask getParent() {
		return parent;
	}

	public void setParent(ParentTask parent) {
		this.parent = parent;
	}





	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	@Transient
	public String getProject() {
		return project;
	}


	public void setProject(String project) {
		this.project = project;
	}


	@Transient
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	@Transient
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	 

	
	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name="project_id") public Project getProject() { return project;
	 * }
	 * 
	 * 
	 * public void setProject(Project project) { this.project = project; }
	 */



    

}
