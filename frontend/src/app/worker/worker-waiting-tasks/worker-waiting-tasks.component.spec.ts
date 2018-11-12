import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerWaitingTasksComponent } from './worker-waiting-tasks.component';

describe('WorkerWaitingTasksComponent', () => {
  let component: WorkerWaitingTasksComponent;
  let fixture: ComponentFixture<WorkerWaitingTasksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkerWaitingTasksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkerWaitingTasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
