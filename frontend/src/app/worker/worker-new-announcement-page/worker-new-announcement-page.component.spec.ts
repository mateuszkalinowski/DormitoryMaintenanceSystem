import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerNewAnnouncementPageComponent } from './worker-new-announcement-page.component';

describe('WorkerNewAnnouncementPageComponent', () => {
  let component: WorkerNewAnnouncementPageComponent;
  let fixture: ComponentFixture<WorkerNewAnnouncementPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkerNewAnnouncementPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkerNewAnnouncementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
