import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TempleListComponent } from './temple-list.component';

describe('TempleListComponent', () => {
  let component: TempleListComponent;
  let fixture: ComponentFixture<TempleListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TempleListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TempleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
