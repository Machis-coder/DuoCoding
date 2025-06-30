import { Component, OnInit } from '@angular/core';
import { CardComponent } from 'src/app/shared/components/card-component/card-component';
import { menu } from './menu';
import { isUserInRole, userRole } from 'src/app/utils/user.utils';
import { MenuOption } from 'src/app/interfaces/menuOption';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-home-component',
  imports: [CommonModule, CardComponent],
  templateUrl: './home-component.html',
  standalone: true,
  styleUrl: './home-component.css'
})
export class HomeComponent implements OnInit {

  optionList = menu;

  ngOnInit(): void {

  }

  get options(): MenuOption[] {
    const userOptions = this.optionList.filter(m => isUserInRole(m.roles));
    return userOptions;
  }

}
