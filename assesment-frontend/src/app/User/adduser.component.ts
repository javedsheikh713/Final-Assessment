
import { Component } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { HttpService } from '../services/http.service';
import {FormControl, FormGroup, Validators,ReactiveFormsModule, FormBuilder, FormArray} from '@angular/forms';

declare var $:any;
@Component({
   templateUrl: './adduser.component.html'
 
})
export class AddUserComponent {
  
  errorResponse=false;
  errorDescription='';
  successMessage;
  userList;
  updatedIndex;
  filteredList ;
  errorMessage;
  userForm:FormGroup;

  constructor(public router: Router,public activatedRoute: ActivatedRoute,private httpService:HttpService,private fb: FormBuilder) {

    this.userForm = fb.group({
      add: this.fb.array([]),
    });
    
   }

  updateUserForm=new FormGroup( {
    id: new FormControl('',Validators.required ),
    firstName: new FormControl( '' ,Validators.required),
    lastName: new FormControl( '',Validators.required ),
    employeeId: new FormControl( '' ,Validators.required)
  } );
  
  ngOnInit() {
  this.getAllUser();
 }

 addData() {

  this.userForm = this.fb.group({
      add: this.fb.array([]),
    });
  let formArr = this.userForm.controls.add as FormArray;
  this.filteredList.forEach(x => {
    formArr.push(this.fb.group({
      id: x.id,
      firstName: x.firstName,
      lastName: x.lastName,
      employeeId: x.employeeId
    }))
  })
}

 sortByFirstName(){

  this.filteredList = this.userList.sort((user: any,user1:any) => (user.firstName.toLowerCase() > user1.firstName.toLowerCase()) ? 1 : -1);
  this.addData();
 }

 sortByLastName(){

  this.filteredList = this.userList.sort((user: any,user1:any) => (user.lastName.toLowerCase() > user1.lastName.toLowerCase()) ? 1 : -1);
  this.addData();
  console.log(this.filteredList);
 }

 sortByEmployeeId(){

 
  this.filteredList = this.userList.sort((user: any,user1:any) => (user.employeeId.toLowerCase() > user1.employeeId.toLowerCase()) ? 1 : -1);
  this.addData();
 }
 onClickSubmit(data) {
  this.successMessage=null;
 this.errorMessage=null;
    this.httpService.editUser(data.add[this.updatedIndex]).then((res:any) => {
    
      //alert(res);
      this.successMessage=res.successMessage;
      this.errorMessage='';
      if(res.error){
      this.errorMessage=res.error.description;
      }

      this.getAllUser();
     })

 }

 onClickAddSubmit(data) {
  this.successMessage=null;
 this.errorMessage=null;
    this.httpService.addUser(data).then((res:any) => {
    
      //alert(res);
      this.successMessage=res.successMessage;
      this.errorMessage='';
      if(res.error){
      this.errorMessage=res.error.description;
      }

      this.getAllUser();
     })

 }


 deleteUser(id:any) {
   
   this.httpService.deleteUser(id).then((res:any) => {

    this.getAllUser();
  })


 }

 getAllUser(){

  this.httpService.getAllUser().then((res:any) => {

    this.userList=res.userList;
    this.filteredList =res.userList;
    this.addData();
  })

 }
 
  

 registerIndex(i:any){
  this.updatedIndex=i;
  return;
 }


 searchText(searchText : any){
  
  if(!searchText){
    this.filteredList=this.userList;
    this.addData();
  }else{

    this.filteredList=null;
   this.filteredList = this.searchByFirstName(searchText);
   
   if(this.filteredList == null || this.filteredList.length == 0){
    this.filteredList = this.searchByLastName(searchText);

    if(this.filteredList == null || this.filteredList.length == 0){

      this.filteredList = this.searchByEmployeeId(searchText);
    }
   }

  if(this.filteredList !=null || this.filteredList.length > 0){
    this.addData();
  }
  
  }
}

searchByFirstName(searchText : any){
  return this.userList.filter((user: any) => user.firstName === searchText);
}

searchByLastName(searchText : any){
  return this.userList.filter((user: any) => user.lastName === searchText);
}

searchByEmployeeId(searchText : any){
  return this.userList.filter((user: any) => user.employeeId === searchText);
}


}
