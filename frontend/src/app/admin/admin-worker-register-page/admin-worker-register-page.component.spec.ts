import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWorkerRegisterPageComponent } from './admin-worker-register-page.component';

describe('AdminWorkerRegisterPageComponent', () => {
  let component: AdminWorkerRegisterPageComponent;
  let fixture: ComponentFixture<AdminWorkerRegisterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminWorkerRegisterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWorkerRegisterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
