import { TestBed, inject } from '@angular/core/testing';

import { WorkerPageGuardService } from './worker-page-guard.service';

describe('WorkerPageGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WorkerPageGuardService]
    });
  });

  it('should be created', inject([WorkerPageGuardService], (service: WorkerPageGuardService) => {
    expect(service).toBeTruthy();
  }));
});
