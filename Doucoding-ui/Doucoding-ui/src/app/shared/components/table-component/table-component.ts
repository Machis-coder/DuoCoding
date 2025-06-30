import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './table-component.html',
  styleUrl: './table-component.css'
})
export class TableComponent implements OnInit {
  @Input() data: any[] = [];
  @Input() headers: string[] = []
  @Input() rowkey: string;

  @Output() rowDblClick = new EventEmitter<any>()

  ngOnInit(): void {
      
  }

  onRowDblClick(row: any) {
    this.rowDblClick.emit(this.rowkey? row[this.rowkey]: row);
  }

  get columns(): string[] {
    if (this.headers && this.headers.length > 0) {
      return this.headers;
    }
    if (!this.data || this.data.length === 0) return [];
    return Object.keys(this.data[0]);
  }


}
