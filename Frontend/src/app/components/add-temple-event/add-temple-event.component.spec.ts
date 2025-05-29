import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTempleEventComponent } from './add-temple-event.component';

describe('AddTempleEventComponent', () => {
  let component: AddTempleEventComponent;
  let fixture: ComponentFixture<AddTempleEventComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddTempleEventComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddTempleEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
