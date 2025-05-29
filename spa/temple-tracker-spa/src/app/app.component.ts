import { Component } from '@angular/core';
import { FooterComponent } from './components/footer/footer.component';
import { AboutComponent } from './components/about/about.component';
import { FeaturesComponent } from './components/features/features.component';
import { HeaderComponent } from './components/header/header.component';
import { GithubComponent } from './components/github/github.component';
import { HeroComponent } from './components/hero/hero.component';
import { MissionComponent } from './components/mission/mission.component';

@Component({
  selector: 'app-root',
  imports: [FooterComponent,AboutComponent,FeaturesComponent,HeaderComponent,GithubComponent,HeroComponent,MissionComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'temple-tracker-spa';
}
