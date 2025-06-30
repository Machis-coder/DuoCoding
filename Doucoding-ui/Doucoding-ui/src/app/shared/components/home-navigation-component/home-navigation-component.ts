import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-navigation',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home-navigation-component.html',
  styleUrl: './home-navigation-component.css'
})
export class HomeNavigationComponent implements OnInit {
  router: Router = inject(Router);
  ngOnInit(): void {     
  }
  
  onClick() {
    this.router.navigate(['/home']);
  }


}
