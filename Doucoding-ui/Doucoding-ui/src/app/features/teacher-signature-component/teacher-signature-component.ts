import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { BasePage } from '../base.page';
import { HomeNavigationComponent } from 'src/app/shared/components/home-navigation-component/home-navigation-component';

@Component({
  selector: 'app-teacher-signature',
  imports: [CommonModule,HomeNavigationComponent],
  templateUrl: './teacher-signature-component.html',
  standalone: true,
  styleUrl: './teacher-signature-component.css'
})
export class TeacherSignatureComponent extends BasePage implements OnInit {

  ngOnInit(): void {
    super.ngOnInit();
  }

}
