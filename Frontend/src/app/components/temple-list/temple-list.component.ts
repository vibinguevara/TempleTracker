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

export class TempleListComponent implements OnInit{
  temples: any[] = [];

  constructor(private templeService: TempleListService) {}

  ngOnInit(): void {
    this.templeService.getAllTemples().subscribe((data) => {
      this.temples = data;
    });
  }
}
