import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './button-component.html',
  styleUrl: './button-component.css'
})
export class ButtonComponent implements OnInit {
  @Input() text: string;
  @Input() clazz: string;
  @Input() type: ButtonType= ButtonType.PRIMARY;  

  @Output() click = new EventEmitter<any>()

  styleClass: string = '';
  styleStyle: string = '';

  ngOnInit(): void {
    const classToApply = (this.clazz? this.clazz: '') +  (this.type === ButtonType.PRIMARY ? " button_primary":" button_secondary");
    this.styleClass = classToApply
  }

  onClick() {    
    this.click.emit(this);
  }

}

export enum ButtonType {
  PRIMARY,
  SECONDARY
}
