import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../interfaces/user';
import { BasePage } from '../base.page';
import { UserService } from 'src/app/core/services/user.service';
import { isOkResponse, loadResponseData } from 'src/app/core/services/utils.service';
import { FormsModule } from '@angular/forms';
import { ButtonComponent } from 'src/app/shared/components/button-component/button-component';
import { Question } from 'src/app/interfaces/question';
import { CommonModule } from '@angular/common';
import { HomeNavigationComponent } from 'src/app/shared/components/home-navigation-component/home-navigation-component';

@Component({
  selector: 'app-new-test',
  imports: [
      CommonModule, FormsModule, ButtonComponent,  HomeNavigationComponent
  ],
  templateUrl: './new-test.component.html',
  styleUrl: './new-test.component.css'
})
export class NewTestComponent extends BasePage  {
  route: ActivatedRoute = inject(ActivatedRoute);
  router: Router = inject(Router)
  userService: UserService = inject(UserService);

  id: number = null;
  name: string = null;
  description: string = null;
  questions: Question[] = [];



  ngOnInit(): void {
    super.ngOnInit();
    let userId = Number(this.route.snapshot.params['id']);
    
  }

  addQuestion():void {
    this.questions.push({description:'', type:0, responses:[], answer: ''});
  }

  get isnew(): boolean {
    return this.id === undefined || this.id === null;
  } 

  validateForm(): boolean {
    return true;
  }

  createOrUpdate(user: User) {

  }


  onSave() {
    
  }

  onBack() {
  
  }

  
}
