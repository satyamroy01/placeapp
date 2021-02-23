import { TestBed } from '@angular/core/testing';

import { FavJobService } from './fav-job.service';

describe('FavJobService', () => {
  let service: FavJobService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FavJobService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
