import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminNewAnnouncementPageComponent } from './admin-new-announcement-page.component';

describe('AdminNewAnnouncementPageComponent', () => {
  let component: AdminNewAnnouncementPageComponent;
  let fixture: ComponentFixture<AdminNewAnnouncementPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminNewAnnouncementPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminNewAnnouncementPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
