package fse.assesment.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fse.assesment.assignment.entity.ParentTask;


public interface ParentTaskRepository extends JpaRepository<ParentTask, Long> {

	
	public ParentTask findByParentTask(String parentTask);
}
