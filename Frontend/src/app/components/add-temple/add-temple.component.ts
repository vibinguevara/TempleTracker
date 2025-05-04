import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'add-temple',
  templateUrl: './add-temple.component.html',
  styleUrls: ['./add-temple.component.scss'],
  standalone: true,
  imports:[ReactiveFormsModule]
})
export class AddTempleComponent implements OnInit {
  templeForm: FormGroup;
  errorMessage: string = '';
  user: any;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private authService: AuthService
  ) {
    this.templeForm = this.fb.group({
      primaryTempleName: ['', Validators.required],
      secondaryTempleName: [''],
      streetAddress: ['', Validators.required],
      otherAddress: [''],
      cityAddress: ['', Validators.required],
      stateAddress: ['', Validators.required],
      postalCodeAddress: ['', Validators.required],
      officialEmail: [''],
      officialPhone: [''],
      officialWebsite: [''],
      deityName: [''],
      jurisdictionOfficer: [''],
      stateGoverned: [false]
    });
  }

  ngOnInit(): void {
    this.user = this.authService.getUserProfile();
    if (!this.user) {
      this.router.navigate(['/']);
    }
  }

  onSubmit(): void {
    if (this.templeForm.valid && this.user) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      });
      // Convert the form data to JSON
      const templeData = {
        ...this.templeForm.value,
        userId: this.user.id  // Add the user ID to the payload
      };
       // Format the specialPoojaDateTime if it exists
       if (templeData.specialPoojaDateTime) {
        templeData.specialPoojaDateTime = new Date(templeData.specialPoojaDateTime).toISOString();
      }
      this.http.post('http://localhost:8080/api/temples', templeData, { headers })
        .subscribe({
          next: (response) => {
            console.log('Temple added successfully:', response);
            this.router.navigate(['/']); // Navigate to temples list or appropriate page
          },
          error: (error) => {
            console.error('Error adding temple:', error);
            this.errorMessage = 'Failed to add temple. Please try again.';
            if (error.error && error.error.message) {
              this.errorMessage = error.error.message;
            }
          }
        });
    }
  }

  onCancel(): void {
    this.router.navigate(['/']); // Navigate back to home or appropriate page
  }
}