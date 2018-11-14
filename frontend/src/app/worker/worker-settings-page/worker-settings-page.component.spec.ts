import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkerSettingsPageComponent } from './worker-settings-page.component';

describe('WorkerSettingsPageComponent', () => {
  let component: WorkerSettingsPageComponent;
  let fixture: ComponentFixture<WorkerSettingsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkerSettingsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkerSettingsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
