import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InhabitantRegisterComponent } from './inhabitant-register.component';

describe('InhabitantRegisterComponent', () => {
  let component: InhabitantRegisterComponent;
  let fixture: ComponentFixture<InhabitantRegisterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InhabitantRegisterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InhabitantRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
