
import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { HttpService } from '../services/http.service';


@Component({
   templateUrl: './addtask.component.html'
 
})
export class AddTaskComponent {


  constructor(public router: Router,private httpService:HttpService) { }
  errorResponse=false;
  errorDescription='';
  taskName='Add Task';
  rangevalue = 0;
  successMessage;
  errorMessage;
  startDate;
  endDate;
  isParentTask=false;
  project;
  userId;
  userName;
  parentTask;

  
  ngOnInit() {
   this.taskName='Add Task'; 
   this.defaultDate();
 }



 onClickSubmit(data) {
 this.successMessage=null;
 this.errorMessage=null;

 if(data.isParentTask){

 data.parentTask=data.task;
  this.httpService.addParentTask(data).then((res:any) => {
    
    //alert(res);
    this.successMessage=res.successMessage;
    this.errorMessage='';
    if(res.error){
    this.errorMessage=res.error.description;
    }
   })

 }else{
  
    this.httpService.addTask(data).then((res:any) => {
    
      this.successMessage=res.successMessage;
      this.errorMessage='';
      if(res.error){
      this.errorMessage=res.error.description;
      }
     })
    }
 }

 

  valueChanged(e) {
    this.rangevalue = e.target.value;
  }
 
  defaultDate(){
     
      var date = new Date();
      var currentDate = date.toISOString().slice(0,10);
      this.startDate = currentDate;
  
      var tomorrow = new Date(Date.now() + 24 * 60 * 60 * 1000)
      this.endDate=tomorrow.toISOString().slice(0,10);
    
  
  }
 
 

  changeValue(event: any){
   
    if(!event){
  
      var date = new Date();
      var currentDate = date.toISOString().slice(0,10);
      this.startDate = currentDate;
  
      var tomorrow = new Date(Date.now() + 24 * 60 * 60 * 1000)
      this.endDate=tomorrow.toISOString().slice(0,10);
    }else{
      this.startDate=null;
      this.endDate=null;
    }
  
  }
  
  handleSelectedProject(selectedProjectId: any) {
   
    this.project=selectedProjectId;
       
   }

   handleSelectedUser(selectedUserId: number) {
    this.userId=selectedUserId;
     this.httpService.getUserById(selectedUserId).then((res:any) => {
      this.userId=res.id;
      this.userName=res.firstName + ' '+res.lastName;
      })
   
   }

   handleSelectedParentTask(selectedParentTaskId: any) {
   
    this.parentTask=selectedParentTaskId;
       
   }

}
