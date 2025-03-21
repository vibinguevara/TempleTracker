import { TestBed } from '@angular/core/testing';

import { TempleService } from './temple.service';

describe('TempleService', () => {
  let service: TempleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TempleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
