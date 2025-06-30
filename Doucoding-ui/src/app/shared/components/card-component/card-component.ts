import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card-component.html',
  styleUrl: './card-component.css'
})
export class CardComponent implements OnInit {
  @Input() title;
  @Input() description;
  @Input() navigation;

  @Input() data: any[] = [];
  @Input() headers: string[] = []
  @Input() rowkey: string;

  @Output() dblClic = new EventEmitter<any>()
  @Output() clic = new EventEmitter<any>()
  router: Router = inject(Router);

  ngOnInit(): void {
      
  }

  onDblClick() {
    //this.dblClic.emit(this.rowkey? row[this.rowkey]: row);
    this.router.navigate(['/'+ this.navigation]);
  }

  onClick() {
    this.router.navigate(['/'+ this.navigation]);
    //this.clic.emit(this.navigation);
  }


}
