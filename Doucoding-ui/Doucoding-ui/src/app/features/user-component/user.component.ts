import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../interfaces/user';
import { BasePage } from '../base.page';
import { UserService } from 'src/app/core/services/user.service';
import { isOkResponse, loadResponseData } from 'src/app/core/services/utils.service';
import { FormsModule } from '@angular/forms';
import { ButtonComponent } from 'src/app/shared/components/button-component/button-component';

@Component({
  selector: 'app-user',
  imports: [
      FormsModule, ButtonComponent
  ],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent extends BasePage  {
  route: ActivatedRoute = inject(ActivatedRoute);
  router: Router = inject(Router)
  userService: UserService = inject(UserService);

  id: number = null;
  name: string = null;
  surname: string = null;
  dni: string = null;
  birthdate: Date = null;
  email: string = null;
  username: string = null;
  password: string = null;
  repassword: string= null;
  role: string = null;



  ngOnInit(): void {
    super.ngOnInit();
    let userId = Number(this.route.snapshot.params['id']);
    if (userId) {
        this.userService.findUser(userId).subscribe({
          next: (response) => {
             const user: User = loadResponseData(response);
              this.id = user.id;
              this.name = user.name;
              this.surname = user.surname;
              this.dni = user.dni;
              this.birthdate = user.birthday;
              this.email = user.email;
              this.username = user.username;
              this.password = user.password;
              this.repassword = user.repassword;
              this.role = user.role;
          }, 
          error: (err) => { 
            console.log(err);
          }
        })
    }
  }

  get isnew(): boolean {
    return this.id === undefined || this.id === null;
  } 

  validateForm(): boolean {
    return (this.name !== '' && this.surname !== '' && this.dni != '' && this.birthdate != null && this.email !== '' 
      && this.username != '' && this.password !== '' && this.role != '');
  }

  createOrUpdate(user: User) {

    if (!user.id) {

      this.userService.save(user).subscribe({
        next: (response) => {
          if (isOkResponse(response)) {
            this.router.navigate(['/users']);
          } 
        },
        error: (err) => {
          console.log(err);
        }
        });
      } else  {
        this.userService.update(user).subscribe({
        next: (response) => {
          if (isOkResponse(response)) {
            this.router.navigate(['/users']);
          } 
        },
        error: (err) => {
          console.log(err);
        }
        });
    }
    


  }


  onSave() {
    if (this.validateForm()) {
      const user: User = {
        id: this.id,
        name: this.name,
        surname: this.surname,
        dni: this.dni,
        birthday: this.birthdate,
        email: this.email,
        username:  this.username,
        password: this.password,
        repassword: this.repassword,
        role: this.role
      }
      this.createOrUpdate(user);           
    } else {
        alert('Formulario no valido')
    }
}

  onBack() {
    this.router.navigate(['/users']);
  }

  
}
