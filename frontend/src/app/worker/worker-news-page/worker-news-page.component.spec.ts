import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerNewsPageComponent } from './worker-news-page.component';

describe('WorkerNewsPageComponent', () => {
  let component: WorkerNewsPageComponent;
  let fixture: ComponentFixture<WorkerNewsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkerNewsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkerNewsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
