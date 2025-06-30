import { Component, OnInit } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import { PageHeader } from './features/page-header/page-header';
import { PageFooter } from './features/page-footer/page-footer';
import { isUserLogged } from './utils/user.utils';
import { CommonModule } from '@angular/common';

@Component({
  selector: "app-root",
  styleUrls: ['./app.component.css'],
  templateUrl: "./app.component.html",
  imports: [
    RouterOutlet, PageHeader, PageFooter, CommonModule
  ],
  standalone: true,

})
export class AppComponent implements OnInit {
  isUserLogged: boolean = true;
  
  ngOnInit(): void {
    this.isUserLogged = isUserLogged();
  }



}
