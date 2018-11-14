import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerTaskDetailsComponent } from './worker-task-details.component';

describe('WorkerTaskDetailsComponent', () => {
  let component: WorkerTaskDetailsComponent;
  let fixture: ComponentFixture<WorkerTaskDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkerTaskDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkerTaskDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
