import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InhabitantSettingsPageComponent } from './inhabitant-settings-page.component';

describe('InhabitantSettingsPageComponent', () => {
  let component: InhabitantSettingsPageComponent;
  let fixture: ComponentFixture<InhabitantSettingsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InhabitantSettingsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InhabitantSettingsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
