package fse.assesment.assignment.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import fse.assesment.assignment.entity.Task;

public interface TaskManagerRepository extends JpaRepository<Task, Long> {
	
	public Task findByTask(String task);

}
