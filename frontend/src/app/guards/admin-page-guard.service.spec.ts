import { TestBed, inject } from '@angular/core/testing';

import { AdminPageGuardService } from './admin-page-guard.service';

describe('AdminPageGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminPageGuardService]
    });
  });

  it('should be created', inject([AdminPageGuardService], (service: AdminPageGuardService) => {
    expect(service).toBeTruthy();
  }));
});
