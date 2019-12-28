import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subject } from 'rxjs';
import { Task } from '../Task/task';




const baseUrl: string = 'http://localhost:8080/'
const header = {
    'Content-Type': 'application/json'
   
    };
    const httpOptions = new HttpHeaders(header);


@Injectable()
export class HttpService {
    
     public task: Task;

  

  
    
    constructor(public http: HttpClient) { }
      


    addTask(task: any){
        
        return this.http.post(baseUrl+'addtask', task,{headers : httpOptions}).toPromise();
    }

    addParentTask(parentTask : any){
        return this.http.post(baseUrl+'addparenttask', parentTask,{headers : httpOptions}).toPromise();
    }
    addUser(user:any){
        return this.http.post(baseUrl+'adduser', user,{headers : httpOptions}).toPromise();
    }

    editUser(user:any){
        return this.http.post(baseUrl+'updateuser', user,{headers : httpOptions}).toPromise();
    }

    editProject(project:any){
        return this.http.post(baseUrl+'updateproject', project,{headers : httpOptions}).toPromise();
    }

    addProject(project:any){
        return this.http.post(baseUrl+'addproject', project,{headers : httpOptions}).toPromise();
    }

   
updateTask(task: any){
        return this.http.post(baseUrl+'updatetask', task).toPromise();
    }

    getTasks(){
        return this.http.get(baseUrl+'getalltask').toPromise();
    }

    searchTaskById(id:number){
        var url='searchbyid/'+id;
        return this.http.get(baseUrl+url).toPromise();
    }

    endTask(id:number){
        var url='endtask/'+id;
        return this.http.get(baseUrl+url).toPromise();
    }

    getAllUser(){
        return this.http.get(baseUrl+'getalluser').toPromise();
    }

    deleteUser(id:any){
        var url='deleteuser/'+id;
        return this.http.get(baseUrl+url).toPromise();
    }

    getAllProject(){
        return this.http.get(baseUrl+'getallproject').toPromise();
    }

    getManager(searchText:any){
        var url='searchmanager/'+searchText;
        return this.http.get(baseUrl+url).toPromise();
    }

    getUserById(userId:any){
        var url='searchuser/'+userId;
        return this.http.get(baseUrl+url).toPromise();
    }

    getProjectById(projectId:any){
        var url='searchprojectbyid/'+projectId;
        return this.http.get(baseUrl+url).toPromise();
    }


    getAllParentTask(){
        return this.http.get(baseUrl+'getallparenttask').toPromise();
    }
}