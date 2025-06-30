import { Component, inject, OnInit } from '@angular/core';
import { User } from '../../interfaces/user';
import { CommonModule } from '@angular/common';
import { TableComponent } from 'src/app/shared/components/table-component/table-component';
import { UserService } from 'src/app/core/services/user.service';
import {  
  isOkResponse,
  loadResponseData
} from "../../core/services/utils.service";
import { BasePage } from '../base.page';
import { Router } from '@angular/router';
import { HomeNavigationComponent } from 'src/app/shared/components/home-navigation-component/home-navigation-component';
import { ButtonComponent } from 'src/app/shared/components/button-component/button-component';


@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule, TableComponent, HomeNavigationComponent, ButtonComponent],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent extends BasePage {
  userList: User[] = [];
  
  router: Router = inject(Router);
  userService: UserService = inject(UserService);


  ngOnInit() {
      super.ngOnInit();
      this.loadUsers();
  }
  
  loadUsers() {
    this.userService.findUsers().subscribe({
      next: (response) => {
        if (isOkResponse(response)) {
          this.userList = loadResponseData(response);
        } else {
          //this.error = loadResponseError(response);
        }
      },
      error: (err) =>{
        console.log(err);
      }
      
    });      
  } 

  gotoEdit(id) {
    this.router.navigate(['/user/' + id]);
  }

  gotoNew() {
    this.router.navigate(['/user']);
  }

}

