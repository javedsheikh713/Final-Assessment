import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { routing } from './app-routing';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import {ViewTaskComponent} from './Task/viewtask.component';
import {FooterComponent} from './footer.component';
import {HeaderComponent} from './header.component';
import {AddTaskComponent} from './Task/addtask.component';
import { UpdateTaskComponent } from './Task/updatetask.component';
import { AddUserComponent } from './User/adduser.component';
import { HttpService } from './services/http.service';
import { GrdFilterPipe } from './grd-filter.pipe';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddProjectComponent } from './Project/addproject.component';
import { ProjectModalComponent } from './Modal/project-modal.component';
import { UserModalComponent } from './Modal/user-modal.component';
import { TaskModalComponent } from './Modal/task-modal.component';









@NgModule({
  declarations: [
    AppComponent,
    ViewTaskComponent,
     FooterComponent,
    HeaderComponent,
    AddTaskComponent,
    UpdateTaskComponent,
    GrdFilterPipe,
    AddUserComponent,
    AddProjectComponent,
    ProjectModalComponent,
    UserModalComponent,
    TaskModalComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    routing,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule
   
    
  ],
  providers: [HttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
