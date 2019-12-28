package fse.assesment.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fse.assesment.assignment.entity.Project;

public interface ProjectManagerRepository extends JpaRepository<Project, Long> {
	
	public Project findByProject(String project);

}
