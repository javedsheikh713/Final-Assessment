
import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { Observable } from 'rxjs';
import { HttpService } from '../services/http.service';
import {FormControl, FormGroup, Validators,ReactiveFormsModule, FormBuilder} from '@angular/forms';
import { Task } from './task';


@Component({
   templateUrl: './updatetask.component.html'
 
})
export class UpdateTaskComponent  {

   id1;
   task : Task; 
   response;
   successMessage;
   errorMessage; 
   isParentTask=false;
  project;
  userId;
  userName;
  parentTask;
    

   updateForm=new FormGroup( {
    id: new FormControl('',Validators.required ),
    task: new FormControl( '' ,Validators.required),
    parentTask: new FormControl( '',Validators.required ),
    priority: new FormControl( '' ,Validators.required),
    startDate: new FormControl( '',Validators.required ),
    endDate: new FormControl( '',Validators.required ),
    parent:new FormControl( '',Validators.required ),
    userName:new FormControl( '',Validators.required ),
    project: new FormControl( '',Validators.required ),
    projectId: new FormControl( '',Validators.required )
  } );

  constructor(public router: Router,public activatedRoute: ActivatedRoute,private httpService:HttpService,private fb: FormBuilder) {

   }
  
  
  ngOnInit() {
   this.id1 = this.activatedRoute.snapshot.params['id'];
   this.httpService.searchTaskById(this.id1).then((res:any) => {
     
      this.task=res; 

      this.updateForm.patchValue({
        id: res.id,
        task: res.task,
        parentTask:res.parent.parentTask,
        priority:res.priority,
        startDate:res.startDate,
        endDate:res.endDate,
        parent:res.parent,
        userName:res.user.firstName + res.user.lastName,
        project : res.project ,
        projectId:res.projectId   // other form fields
      })
      
    });

 }
 onSubmit() {
  
  this.task=this.updateForm.value;
  this.task.parent=null;
  //this.task.parent.parentTask=this.updateForm.value.parentTask;

  if(!this.isEmpty(this.updateForm.value.startDate) && !this.isEmpty(this.updateForm.value.endDate) ){
    
    if(!this.dateValidation(this.updateForm.value.startDate,this.updateForm.value.endDate)){
    
     this.errorMessage='End Date should be Greater than Start date';
     return;
    }
   }

 this.httpService.updateTask(this.task).then((res:any) => {
  this.response=res;
 
  this.successMessage=res.successMessage;
  if(res.error){
  this.errorMessage=res.error.description;
  }
 })

 }

 cancelTask(){
   this.router.navigate(['/task']);
 }
 
 valueChanged(e) {
   this.updateForm.value.priority = e.target.value;
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
  this.updateForm.value.parentTask=selectedParentTaskId;
  console.log(this.updateForm.value.parentTask);

 }
  
 isEmpty(text:any){
  if(text ==null || text == ''){
    return true;
  }
  return false;
}


dateValidation(startDt:any,endDt:any){
  if(startDt >= endDt){
   
   return false; 
}

return true;
}

}
