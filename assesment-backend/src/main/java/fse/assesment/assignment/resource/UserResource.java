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

import fse.assesment.assignment.entity.Task;
import fse.assesment.assignment.entity.User;
import fse.assesment.assignment.response.Response;
import fse.assesment.assignment.service.TaskManagerService;
import fse.assesment.assignment.service.UserService;



@RestController
public class UserResource {
	
	Logger log = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private UserService userService;
	
	
		
	@PostMapping("/adduser")
	@ResponseBody
	public Response addUser(@RequestBody User user) {
		log.info("Start method add user in resource");
		return userService.addUser(user);
	}
	
	@PostMapping("/updateuser")
	@ResponseBody
	public Response updateUser(@RequestBody User user) {
		log.info("Start method add update user in resource");
		return userService.updateUser(user);
		
	}
	
	
	
	@GetMapping("/getalluser")
	@ResponseBody
	public Response getAllUser() {
		log.info("Start method  getAllUser in resource");
		return userService.searchAllUser();
	}
	
	@GetMapping("/searchuser/{id}")
	@ResponseBody
	public Response searchUserById(@PathVariable long id) {
		log.info("Start method  searchUserById in resource");
		return userService.searchUserByID(id);
	}
	
	@GetMapping("/searchbyemployeeid/{employeeid}")
	@ResponseBody
	public Response searchUserByEmployee(@PathVariable String employeeid) {
		log.info("Start method  searchUserByEmployee in resource");
		return userService.searchUserByEmployeeId(employeeid);
	}
	
	
	@GetMapping("/deleteuser/{id}")
	@ResponseBody
	public Response deleteUser(@PathVariable long id) {
		log.info("Start method  delete user in resource");
		return userService.deleteUser(id);
	}
	
	

}
