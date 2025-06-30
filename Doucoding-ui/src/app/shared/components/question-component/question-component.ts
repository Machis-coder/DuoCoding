import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ResponseComponent } from '../response-component/response-component';

@Component({
  selector: 'app-question',
  standalone: true,
  imports: [CommonModule, ResponseComponent ],
  templateUrl: './question-component.html',
  styleUrl: './question-component.css'
})
export class QuestionComponent implements OnInit {
  @Input() text: string;
  @Input() type: number;
  @Input() responses: Response[];


  textInternal: string;
  typeInternal: number;
  responsesInternal: Response[];
  
  
  ngOnInit(): void {
    this.textInternal = this.text;
    this.typeInternal = this.type;
    this.responsesInternal = this.responses;
  }

  

  @Output() click = new EventEmitter<any>()

  description: string = ''
  answer: string = ''
  
  

  get types(): string[] {
    return [QuestionType.FREETEXT.toString(), 
      QuestionType.MONOSELECTION.toString(),
      QuestionType.MULTISELECTION.toString(),
      QuestionType.GAP.toString(),
      QuestionType.CODE.toString()]
  }

  onClick() {    
    this.click.emit(this);
  }

  onTypeChange($event) {

  }

  saveResponse() {

  }

}

export enum QuestionType {
  FREETEXT,
  MONOSELECTION,
  MULTISELECTION,
  GAP,
  CODE
}
