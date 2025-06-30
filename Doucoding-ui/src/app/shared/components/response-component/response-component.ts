import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { ButtonComponent } from '../button-component/button-component';

@Component({
  selector: 'app-response',
  standalone: true,
  imports: [CommonModule, ButtonComponent],
  templateUrl: './response-component.html',
  styleUrl: './response-component.css'
})
export class ResponseComponent implements OnInit {
  @Input() text: string;
  @Input() order: string;
  

  @Output() click = new EventEmitter<any>()  

  ngOnInit(): void {
    
  }

  onClick() {
    this.click.emit({order: this.order, text: this.text});
  }

}

export enum QuestionType {
  FREETEXT,
  MONOSELECTION,
  MULTISELECTION,
  GAP,
  CODE
}
