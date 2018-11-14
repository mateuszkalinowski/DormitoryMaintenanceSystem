import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerAssignedTasksComponent } from './worker-assigned-tasks.component';

describe('WorkerAssignedTasksComponent', () => {
  let component: WorkerAssignedTasksComponent;
  let fixture: ComponentFixture<WorkerAssignedTasksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkerAssignedTasksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkerAssignedTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
