import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { Router } from '@angular/router';
import { GoogleAuthProvider, getAuth, signInWithPopup, signOut, User } from 'firebase/auth';
import { initializeApp } from 'firebase/app';
import { isPlatformBrowser } from '@angular/common';
import { environment } from '../../environments/environments';

initializeApp(environment.firebaseConfig);

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private auth = getAuth();
  user: User | null = null;

  constructor(private router: Router, @Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      const storedUser = localStorage.getItem('user');
      this.user = storedUser ? JSON.parse(storedUser) : null;
    }

    this.auth.onAuthStateChanged((user) => {
      this.user = user;
      if (isPlatformBrowser(this.platformId)) {
        localStorage.setItem('user', JSON.stringify(user));
      }
    });
  }

  async signInWithGoogle(): Promise<void> {
    try {
      const provider = new GoogleAuthProvider();
      const result = await signInWithPopup(this.auth, provider);
      this.user = result.user;
      if (isPlatformBrowser(this.platformId)) {
        localStorage.setItem('user', JSON.stringify(this.user));
      }
    } catch (error) {
      console.error('Google Sign-In Error', error);
    }
  }

  async logout(): Promise<void> {
    await signOut(this.auth);
    this.user = null;
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('user');
    }
    this.router.navigate(['/']);
  }

  getUserProfile(): User | null {
    return this.user;
  }

  isAuthenticated(): boolean {
    return this.user !== null;
  }
}
