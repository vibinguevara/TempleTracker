import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router'; 
import { TempleListService } from '../../services/temple-list.service';

@Component({
  selector: 'temple-tracker-list',
  templateUrl: './temple-list.component.html',
  styleUrl: './temple-list.component.scss',
  standalone: true,
  imports:[RouterModule,CommonModule]
})

export class TempleListComponent implements OnInit {
  temples: any[] = [];
  page: number = 0;
  size: number = 10;

  constructor(private templeService: TempleListService) {}

  ngOnInit(): void {
    this.loadTemples();
  }

  loadTemples(): void {
    this.templeService.getPaginatedTemples(this.page, this.size).subscribe(response => {
      this.temples = response.content;
    });
  }

  nextPage(): void {
    this.page++;
    this.loadTemples();
  }

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.loadTemples();
    }
  }
}