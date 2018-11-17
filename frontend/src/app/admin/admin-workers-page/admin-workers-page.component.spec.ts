import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminWorkersPageComponent } from './admin-workers-page.component';

describe('AdminWorkersPageComponent', () => {
  let component: AdminWorkersPageComponent;
  let fixture: ComponentFixture<AdminWorkersPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminWorkersPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminWorkersPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
