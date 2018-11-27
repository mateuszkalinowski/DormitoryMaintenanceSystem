import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminOwnAnnouncementPageComponent } from './admin-own-announcement-page.component';

describe('AdminOwnAnnouncementPageComponent', () => {
  let component: AdminOwnAnnouncementPageComponent;
  let fixture: ComponentFixture<AdminOwnAnnouncementPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminOwnAnnouncementPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminOwnAnnouncementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
