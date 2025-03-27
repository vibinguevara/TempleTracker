import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TempleListComponent } from './components/temple-list/temple-list.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';

@Component({
  selector: 'app-root',
  standalone: true,   // <-- Ensure it's standalone
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  imports: [RouterOutlet,TempleListComponent, FooterComponent, HeaderComponent]
})
export class AppComponent { }
