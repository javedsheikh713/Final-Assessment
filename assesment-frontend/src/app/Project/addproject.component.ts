
import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators,ReactiveFormsModule, FormBuilder, FormArray} from '@angular/forms';
import { HttpService } from '../services/http.service';
import { UserModalComponent } from '../Modal/user-modal.component';

declare var $: any;

@Component({
   templateUrl: './addproject.component.html'
 
})
export class AddProjectComponent {
  constructor(public router: Router,private httpService:HttpService,private fb: FormBuilder) { }
  errorResponse=false;
  errorDescription='';
  successMessage;
  projectList;
  filteredProjectList ;
  rangevalue;
  errorMessage;
  startDate;
  endDate;
  updatedIndex;
  managerId;
  managerName;
  projectForm:FormGroup;
  ngOnInit() {
   this.getAllProject();
 }




 onClickSubmit(data) {
  
  if(data.isChecked){
    if(this.isEmpty(data.startDate)){
      data.startDate=this.startDate;
    }

    if(this.isEmpty(data.endDate)){
      data.endDate=this.endDate;
    }

   if(!this.dateValidation(data.startDate,data.endDate)){
    this.errorMessage='End Date should be Greater than Start date';
    return;
   }
  }

  
 this.successMessage=null;
 this.errorMessage=null;
    this.httpService.addProject(data).then((res:any) => {
    
      //alert(res);
      this.successMessage=res.successMessage;
      this.errorMessage='';
      if(res.error){
      this.errorMessage=res.error.description;
      }
      this.getAllProject();
     })

 }


 deleteUser(id:any) {
   this.httpService.deleteUser(id).then((res:any) => {

    //this.userList=res.userList;
  })


 }

 
 valueChanged(e) {
  this.rangevalue = e.target.value;
}

checkValue(event: any){
  if(event){

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
  

dateValidation(startDt:any,endDt:any){
   if(startDt >= endDt){
    
    return false; 
}

return true;
}

isEmpty(text:any){
  if(text ==null || text == ''){
    return true;
  }
  return false;
}

addData() {

  this.projectForm = this.fb.group({
      add: this.fb.array([]),
    });
  let formArr = this.projectForm.controls.add as FormArray;
  this.filteredProjectList.forEach(x => {
    formArr.push(this.fb.group({
      id: x.id,
      project: x.project,
      startDate: x.startDate,
      endDate: x.endDate,
      priority: x.priority,
      noOfTask : x.noOfTask,
      taskCompleted : x.taskCompleted
    }))
  })
}

getAllProject(){

  this.httpService.getAllProject().then((res:any) => {
    
    this.projectList=res.projectList;
    this.filteredProjectList =res.projectList;
    this.addData();
  })


 }


 searchManager(searchText:any){
  this.httpService.getManager(searchText).then((res:any) => {

  })
 }

 updateProject(data){
 
   
  if(!this.isEmpty(data.add[this.updatedIndex].startDate) && !this.isEmpty(data.add[this.updatedIndex].endDate) ){
    
   if(!this.dateValidation(data.add[this.updatedIndex].startDate,data.add[this.updatedIndex].endDate)){
   
    this.errorMessage='End Date should be Greater than Start date';
    return;
   }
  }
  
  this.successMessage=null;
 this.errorMessage=null;
    this.httpService.editProject(data.add[this.updatedIndex]).then((res:any) => {
    
      //alert(res);
      this.successMessage=res.successMessage;
      this.errorMessage='';
      if(res.error){
      this.errorMessage=res.error.description;
      }

      this.getAllProject();
     })
 }


 registerIndex(i:any){
  this.updatedIndex=i;
  return;
 }

 sortByStartDate(){

  this.filteredProjectList = this.projectList.sort((project: any,project1:any) => (project.startDate > project1.startDate) ? 1 : -1);
  this.addData();
 }

 sortByEndDate(){

  this.filteredProjectList = this.projectList.sort((project: any,project1:any) => (project.endDate > project1.endDate) ? 1 : -1);
  this.addData();
 
 }

 sortByPriority(){

 
  this.filteredProjectList = this.projectList.sort((project: any,project1:any) => (project.priority > project1.priority) ? 1 : -1);
  this.addData();
 }

 sortByCompleted(){
  this.filteredProjectList = this.projectList.sort((project: any,project1:any) => (project.taskCompleted > project1.taskCompleted) ? 1 : -1);
  this.addData();
 }

searchText(searchText : any){

  if(!searchText){
    this.filteredProjectList=this.projectList;
    this.addData();
  }else{
  this.filteredProjectList = this.projectList.filter((project: any) => project.project === searchText);
  this.addData();
  
  }
}

handleSelectedUser(selectedUserId: number) {
 this.managerId=selectedUserId;
  this.httpService.getUserById(selectedUserId).then((res:any) => {
   this.managerId=res.id;
   this.managerName=res.firstName + ' '+res.lastName;
   })

}

}
