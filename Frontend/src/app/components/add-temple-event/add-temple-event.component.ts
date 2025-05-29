import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TempleEventService } from '../../services/temple-event.service';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-temple-event',
  templateUrl: './add-temple-event.component.html',
  styleUrls: ['./add-temple-event.component.scss'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule]
})
export class AddTempleEventComponent implements OnInit {
  templeId: any;
  eventForm: FormGroup;
  hasEventsToday: boolean = false;
  eventCountText: string = 'No events for today';

  constructor(
    private fb: FormBuilder,
    private templeEventService: TempleEventService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.eventForm = this.fb.group({
      templeType: ['', Validators.required],
      eventName: ['', Validators.required],
      eventDescription: ['', Validators.required],
      eventDateTime: ['', Validators.required],
      isFreeMealsAvailable: [false],
      isPrasadhamAvailable: [false]
    });
  }

  ngOnInit() {
    this.templeId = this.route.snapshot.paramMap.get('templeId');
    this.checkTodayEvents();
  }

  async checkTodayEvents(): Promise<void> {
    try {
      const response = await firstValueFrom(this.templeEventService.getTodayEvents());
      const eventCount = response.length;
      this.hasEventsToday = eventCount > 0;
      this.eventCountText = eventCount > 0 
        ? `${eventCount} events for today` 
        : 'No events for today';
    } catch (error) {
      console.error('Error fetching today\'s events:', error);
      this.hasEventsToday = false;
      this.eventCountText = 'No events for today';
    }
  }

  async onSubmit(): Promise<void> {
    if (this.eventForm.valid) {
      try {
        const response = await firstValueFrom(
          this.templeEventService.createEvent(this.eventForm.value)
        );
        console.log('Event created successfully:', response);
        await this.router.navigate(['/temple-list']);
      } catch (error) {
        console.error('Error creating event:', error);
        // You might want to add user feedback here, like a toast message
        // this.toastService.showError('Failed to create event. Please try again.');
      }
    }
  }

  resetForm() {
    this.eventForm.reset();
  }
}