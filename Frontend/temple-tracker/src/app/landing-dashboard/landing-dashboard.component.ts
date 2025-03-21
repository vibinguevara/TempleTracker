import { Component } from '@angular/core';
import { TempleService } from 'src/app/services/temple.service';

@Component({
  selector: 'app-landing-dashboard',
  templateUrl: './landing-dashboard.component.html',
  styleUrls: ['./landing-dashboard.component.scss']
})
export class LandingDashboardComponent {
  temples: any[] = [];

  constructor(private templeService: TempleService) {}

  ngOnInit(): void {
    this.templeService.getAllTemples().subscribe((data) => {
      this.temples = data;
    });
  }
}
