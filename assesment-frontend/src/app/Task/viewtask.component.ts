
import { Component, Output } from '@angular/core';
import {Router} from '@angular/router';
import { HttpService } from '../services/http.service';
import { Task } from './task';



@Component({
   templateUrl: './viewtask.component.html'
 
})
export class ViewTaskComponent {

   errorResponse=false;
    tasks;
    public searchTask : string;
    public searchParentTask : string;
    filteredList ;
     successMessage;
    errorMessage;
    project;
    //public priorityFrom : string;
   // public priorityTo : string;



  constructor(public router: Router,private httpService:HttpService) { }
  

  ngOnInit() {
   this.getAllTask();
 }
 

getAllTask(){

   this.httpService.getTasks().then((res:any) => {
        this.tasks=res.taskList;
        this.filteredList=res.taskList;
      
 
  this.successMessage=res.successMessage;
  if(res.error){
  this.errorMessage=res.error.description;
  }
    }); 
}

  editTask(data:any){
    
    var url ="updatetask/"+data;
    this.router.navigateByUrl(url);
  }

  

endTask(id:any){
  
  this.httpService.endTask(id).then((res:any) => {
    this.getAllTask();
   
}); 
}


sortByStartDate(){

  this.filteredList = this.tasks.sort((task: any,task1:any) => (task.startDate > task1.startDate) ? 1 : -1);

 }

 sortByEndDate(){

  this.filteredList = this.tasks.sort((task: any,task1:any) => (task.endDate > task1.endDate) ? 1 : -1);

 
 }

 sortByPriority(){

 
  this.filteredList = this.tasks.sort((task: any,task1:any)  => (task.priority > task1.priority) ? 1 : -1);

 }

 sortByCompleted(){
  this.filteredList = this.tasks.sort((task: any,task1:any)  => (task.taskCompleted > task1.taskCompleted) ? 1 : -1);

 }


  
 searchByProject(searchText:any){

  if(searchText == null || searchText == ''){
    this.getAllTask();
  }

 }

 handleSelectedProject(selectedProjectId: any) {
   
  this.project=selectedProjectId;

  this.filteredList = this.tasks.filter((task: any) => task.project === selectedProjectId);
     
 }
}
