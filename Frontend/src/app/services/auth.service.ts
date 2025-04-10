import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { isPlatformBrowser } from '@angular/common';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private user: any = null;
  private clientId: string = '752976774987-0homqda54v91pt5lm9h8ths8nfvos5lh.apps.googleusercontent.com';//environment.googleClientId;
  private isGoogleLoaded = false;

  constructor(
    private router: Router,
    private http: HttpClient,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    if (isPlatformBrowser(this.platformId)) {
      const storedUser = localStorage.getItem('user');
      this.user = storedUser ? JSON.parse(storedUser) : null;
    }
  }

  private async initializeGoogleAuth(): Promise<void> {
    if (this.isGoogleLoaded) {
      return;
    }

    return new Promise((resolve) => {
      window.onGoogleLibraryLoad = () => {
        console.log('Google library loaded');
        
        window.google.accounts.id.initialize({
          client_id: this.clientId,
          callback: this.handleCredentialResponse.bind(this),
          auto_select: false,
          cancel_on_tap_outside: true,
          context: 'signin',
          ux_mode: 'popup',
        });

        this.isGoogleLoaded = true;
        resolve();
      };

      // Load the Google library if not already loaded
      if (!document.getElementById('google-jssdk')) {
        const script = document.createElement('script');
        script.id = 'google-jssdk';
        script.src = 'https://accounts.google.com/gsi/client';
        script.async = true;
        script.defer = true;
        document.body.appendChild(script);
      } else if (window.google) {
        window.onGoogleLibraryLoad();
      }
    });
  }

  private async handleCredentialResponse(response: any): Promise<void> {
    console.log('Google Credential Response:', response);

    if (response.credential) {
        try {
            const headers = new HttpHeaders({
                'Content-Type': 'application/json'
            });

            const res: any = await firstValueFrom(
                this.http.post(
                    `http://localhost:8080/api/auth/google-login`,
                    { idToken: response.credential },
                    { headers }
                )
            );

            console.log('User authenticated:', res);
            this.user = res;

            if (isPlatformBrowser(this.platformId)) {
                localStorage.setItem('user', JSON.stringify(this.user));
            }

            this.router.navigate(['/']);
        } catch (error) {
            console.error('Authentication error:', error);
        }
    }
}
 
  async login(): Promise<void> {
    if (!isPlatformBrowser(this.platformId)) {
      return;
    }

    try {
      await this.initializeGoogleAuth();
      
      // Use Google Identity Services to get ID token
      window.google.accounts.id.prompt((notification: any) => {
        if (notification.isNotDisplayed() || notification.isSkippedMoment()) {
          // If the One Tap UI is not displayed, fall back to the standard sign-in button
          window.google.accounts.id.renderButton(
            document.getElementById('googleSignInButton') as HTMLElement,
            { theme: 'outline', size: 'large' }
          );
        }
      });
      
    } catch (error) {
      console.error('Error during login:', error);
    }
}

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      if (this.user?.email) {
        window.google.accounts.id.revoke(this.user.email, () => {
          this.clearUserData();
        });
      } else {
        this.clearUserData();
      }
    }
  }

  private clearUserData(): void {
    this.user = null;
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('user');
    }
    this.router.navigate(['/']);
  }

  getUserProfile(): any {
    if (isPlatformBrowser(this.platformId)) {
      return JSON.parse(localStorage.getItem('user') || 'null');
    }
    return null;
  }

  isAuthenticated(): boolean {
    return this.getUserProfile() !== null;
  }
}