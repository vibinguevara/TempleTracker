import { TestBed } from '@angular/core/testing';

import { TempleEventService } from './temple-event.service';

describe('TempleEventService', () => {
  let service: TempleEventService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TempleEventService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
