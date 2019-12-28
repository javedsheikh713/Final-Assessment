import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { HttpService } from '../services/http.service';


@Component({
  selector: 'user-modal',
  templateUrl: './user-modal.component.html'
})
export class UserModalComponent implements OnInit {

 // @Input() modalHeaderMsg: string;
    @Input() selectedUserId: number;
    @Output() userModalEvent = new EventEmitter();

  searchUser: string = '';
  users;

  constructor(private httpService:HttpService) { }

  ngOnInit() {
    this.httpService.getAllUser().then((res:any) => {

      this.users=res.userList;
    })
  }

  filterUser() {
    let searchUser = this.searchUser.toLowerCase();
    this.users.forEach(usr => {
      usr.hide = (usr.firstName.toLowerCase().indexOf(searchUser) == -1 && usr.lastName.toLowerCase().indexOf(searchUser) == -1);
    });
  }

  saveUserId() {
    this.userModalEvent.emit(this.selectedUserId);
  }

 
}