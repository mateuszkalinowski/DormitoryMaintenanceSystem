import { TestBed, inject } from '@angular/core/testing';

import { AppEntranceGuardService } from './app-entrance-guard.service';

describe('AppEntranceGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AppEntranceGuardService]
    });
  });

  it('should be created', inject([AppEntranceGuardService], (service: AppEntranceGuardService) => {
    expect(service).toBeTruthy();
  }));
});
