import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTaskDetailsPageComponent } from './admin-task-details-page.component';

describe('AdminTaskDetailsPageComponent', () => {
  let component: AdminTaskDetailsPageComponent;
  let fixture: ComponentFixture<AdminTaskDetailsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTaskDetailsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTaskDetailsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
