import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'temple-tracker-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
  standalone: true,
  imports: [CommonModule],
})
export class HeaderComponent implements OnInit {
  user: any = null;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.user = this.authService.getUserProfile();
  }

  async login() {
    await this.authService.login();   // Updated from signInWithGoogle()
    this.user = this.authService.getUserProfile();
  }

  logout() {
    this.authService.logout();
    this.user = null;
  }
}
