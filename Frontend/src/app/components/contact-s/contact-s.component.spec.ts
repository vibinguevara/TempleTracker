import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactSComponent } from './contact-s.component';

describe('ContactSComponent', () => {
  let component: ContactSComponent;
  let fixture: ComponentFixture<ContactSComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactSComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactSComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
