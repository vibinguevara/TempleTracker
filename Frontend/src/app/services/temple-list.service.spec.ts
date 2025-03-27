import { TestBed } from '@angular/core/testing';

import { TempleListService } from './temple-list.service';

describe('TempleListService', () => {
  let service: TempleListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TempleListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
