package fse.assesment.assignment.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fse.assesment.assignment.entity.Project;
import fse.assesment.assignment.response.Response;
import fse.assesment.assignment.service.ProjectManagerService;

@RestController
public class ProjectManagerResource {

	Logger log = LoggerFactory.getLogger(TaskManagerResource.class);
	
	@Autowired
	private ProjectManagerService projectManagerService;
	
	
	@RequestMapping(value="/addproject",method = RequestMethod.POST)
	@ResponseBody
	public Response addProjectManager(@RequestBody Project project) {
		log.info("Start method add project manager in resource");
		return projectManagerService.addProject(project);
	}
	
	@PostMapping("/updateproject")
	@ResponseBody
	public Response updateProjectManager(@RequestBody Project project) {
		log.info("Start method update project manager in resource");
		return projectManagerService.updateProject(project);
		
	}
	

	@GetMapping("/getallproject")
	@ResponseBody
	public Response getAllProject() {
		log.info("Start method  getAllProject in resource");
		return projectManagerService.searchAllProject();
	}
	
	@GetMapping("/searchproject/{id}")
	@ResponseBody
	public Response searchProjectById(@PathVariable long id) {
		log.info("Start method  searchProjectById in resource");
		return projectManagerService.searchProjectByID(id);
	}
	
	
}
