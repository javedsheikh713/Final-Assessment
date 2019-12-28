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

import fse.assesment.assignment.entity.ParentTask;
import fse.assesment.assignment.entity.Task;
import fse.assesment.assignment.response.Response;
import fse.assesment.assignment.service.TaskManagerService;



@RestController
public class TaskManagerResource {
	
	Logger log = LoggerFactory.getLogger(TaskManagerResource.class);
	
	@Autowired
	private TaskManagerService taskManagerService;
	
	
	@RequestMapping(value="/addtask",method = RequestMethod.POST)
	@ResponseBody
	public Response addTaskManager(@RequestBody Task task) {
		log.info("Start method add task in resource");
		return taskManagerService.addTask(task);
	}
	
	@PostMapping("/updatetask")
	@ResponseBody
	public Response updateTaskManager(@RequestBody Task task) {
		log.info("Start method add updatetask in resource");
		return taskManagerService.updateTask(task);
		
	}
	
	
	@GetMapping("/getalltask")
	@ResponseBody
	public Response getAllTask() {
		log.info("Start method  getAllTask in resource");
		return taskManagerService.searchAllTask();
	}
	
	@GetMapping("/searchbyid/{id}")
	@ResponseBody
	public Response searchTaskById(@PathVariable long id) {
		log.info("Start method  searchTaskById in resource");
		return taskManagerService.searchById(id);
	}
	
	@RequestMapping(value="/addparenttask",method = RequestMethod.POST)
	@ResponseBody
	public Response addParentTask(@RequestBody ParentTask parentTask) {
		log.info("Start method add parent task in resource");
		return taskManagerService.addParentTask(parentTask);
	}
	
	
	
	@GetMapping("/getallparenttask")
	@ResponseBody
	public Response getAllParentTask() {
		log.info("Start method  getAllParentTask in resource");
		return taskManagerService.searchAllParentTask();
	}

	@GetMapping("/endtask/{id}")
	@ResponseBody
	public Response endTask(@PathVariable long id) {
		log.info("Start method  endTask in resource");
		return taskManagerService.endTask(id);
	}
	
}
