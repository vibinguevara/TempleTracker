import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { RouterModule } from '@angular/router'; 
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'temple-tracker-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
  standalone: true,
  imports: [RouterModule, CommonModule]
})
export class HeaderComponent {
  user: any = null;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    // Check if user is authenticated and retrieve profile
    if (this.authService.isAuthenticated()) {
      this.user = this.authService.getUserProfile();
    }
  }

  login() {
    this.authService.signInWithGoogle().then(() => {
      this.user = this.authService.getUserProfile();
    });
  }

  logout() {
    this.authService.logout();
    this.user = null;
  }
}
