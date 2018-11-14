import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerOwnAnnouncementsPageComponent } from './worker-own-announcements-page.component';

describe('WorkerOwnAnnouncementsPageComponent', () => {
  let component: WorkerOwnAnnouncementsPageComponent;
  let fixture: ComponentFixture<WorkerOwnAnnouncementsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkerOwnAnnouncementsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkerOwnAnnouncementsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
