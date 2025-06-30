import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BasePage } from '../base.page';
import { HomeNavigationComponent } from 'src/app/shared/components/home-navigation-component/home-navigation-component';

@Component({
  selector: 'app-student-signature',
  imports: [CommonModule, HomeNavigationComponent],
  templateUrl: './student-signature-component.html',
  standalone: true,
  styleUrl: './student-signature-component.css'
})
export class StudentSignatureComponent extends BasePage implements OnInit {

  ngOnInit(): void {
    super.ngOnInit();
  }

}
