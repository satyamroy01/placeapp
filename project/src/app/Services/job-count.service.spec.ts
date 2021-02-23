import { TestBed } from '@angular/core/testing';

import { JobCountService } from './job-count.service';

describe('JobCountService', () => {
  let service: JobCountService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobCountService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
