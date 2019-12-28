import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { HttpService } from '../services/http.service';


@Component({
  selector: 'parent-task-modal',
  templateUrl: './task-modal.component.html'
})
export class TaskModalComponent implements OnInit {

  @Input() selectedParentId: number;
 // @Input() parentTasks: Task[];
  @Output() parentTaskModalEvent = new EventEmitter();

  searchParentTask: string = '';
  parentTasks;

  constructor(private httpService:HttpService) { }

  ngOnInit() {
    
    this.httpService.getAllParentTask().then((res:any) => {

      this.parentTasks=res.parentTaskList;
    })
  }

  filterParentTask() {
    let searchParentTask = this.searchParentTask.toLowerCase();
    this.parentTasks.forEach(parentTask => {
      parentTask.hide = (parentTask.task.toLowerCase().indexOf(searchParentTask) == -1);
    });
  }

  saveParentId() {
    this.parentTaskModalEvent.emit(this.selectedParentId);
  }
}