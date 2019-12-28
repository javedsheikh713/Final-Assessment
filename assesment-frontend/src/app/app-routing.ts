import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ViewTaskComponent} from './Task/viewtask.component';
import { AddTaskComponent } from './Task/addtask.component';
import { UpdateTaskComponent } from './Task/updatetask.component';
import { AddUserComponent } from './User/adduser.component';
import { AddProjectComponent } from './Project/addproject.component';


const routes: Routes = [
{
  path:'',
  component:ViewTaskComponent,
},
{
  path:'task',
  component:ViewTaskComponent,
  
},
{
  path:'addtask',
  component:AddTaskComponent,
  
},
{
  path:'updatetask/:id',
  component:UpdateTaskComponent,
  
},
{
  path:'adduser',
  component:AddUserComponent,
  
},
{
  path:'addproject',
  component:AddProjectComponent,
  
},


];


export const routing=RouterModule.forRoot(routes);
