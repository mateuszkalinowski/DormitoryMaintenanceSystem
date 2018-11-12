import { TestBed, inject } from '@angular/core/testing';

import { LoginPageGuardService } from './login-page-guard.service';

describe('LoginPageGuardService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoginPageGuardService]
    });
  });

  it('should be created', inject([LoginPageGuardService], (service: LoginPageGuardService) => {
    expect(service).toBeTruthy();
  }));
});
