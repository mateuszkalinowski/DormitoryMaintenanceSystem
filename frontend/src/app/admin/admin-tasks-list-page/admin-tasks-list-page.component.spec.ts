import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminTasksListPageComponent } from './admin-tasks-list-page.component';

describe('AdminTasksListPageComponent', () => {
  let component: AdminTasksListPageComponent;
  let fixture: ComponentFixture<AdminTasksListPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminTasksListPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminTasksListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
