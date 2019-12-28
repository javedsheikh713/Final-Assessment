import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { HttpService } from '../services/http.service';


@Component({
  selector: 'project-modal',
  templateUrl: './project-modal.component.html'
})
export class ProjectModalComponent implements OnInit {

  @Input() selectedProjectId: number;
 // @Input() projects: Project[];
  @Output() projectModalEvent = new EventEmitter();

  searchProject: string = '';
  projects;

  constructor(private httpService:HttpService) { }

  ngOnInit() {
  
    this.httpService.getAllProject().then((res:any) => {

      this.projects=res.projectList;
    })
  }

  filterProject() {
    let searchProject = this.searchProject.toLowerCase();
    this.projects.forEach(proj => {
      proj.hide = (proj.project.toLowerCase().indexOf(searchProject) == -1);
    });
  }

  saveProjectId() {
    
    this.projectModalEvent.emit(this.selectedProjectId);
  }
}