import { TestBed, inject } from '@angular/core/testing';

import { InhabitantPageGuardService } from './inhabitant-page-guard.service';

describe('InhabitantPageGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InhabitantPageGuardService]
    });
  });

  it('should be created', inject([InhabitantPageGuardService], (service: InhabitantPageGuardService) => {
    expect(service).toBeTruthy();
  }));
});
