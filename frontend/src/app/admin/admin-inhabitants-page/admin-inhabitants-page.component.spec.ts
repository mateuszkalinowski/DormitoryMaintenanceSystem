import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminInhabitantsPageComponent } from './admin-inhabitants-page.component';

describe('AdminInhabitantsPageComponent', () => {
  let component: AdminInhabitantsPageComponent;
  let fixture: ComponentFixture<AdminInhabitantsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminInhabitantsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminInhabitantsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
