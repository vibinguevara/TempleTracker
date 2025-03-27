import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router'; 


@Component({
  selector: 'temple-tracker-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.scss',
  standalone: true,
  imports:[RouterModule,CommonModule]
})
export class FooterComponent {

}
