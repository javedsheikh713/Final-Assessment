import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'header-root',
    templateUrl: './header.component.html'
   
  })

  export class HeaderComponent {


    constructor(public router: Router) { }
   taskName=''

  ngOnInit() {
 
 }

    addTask() {
 
      this.router.navigate(['/addtask']);
      
      }

      viewTask() {
 
        this.router.navigate(['/task']);
        
        }


        addUser() {
 
          this.router.navigate(['/adduser']);
          
          }

          addProject(){
            this.router.navigate(['/addproject']);
          }
  }