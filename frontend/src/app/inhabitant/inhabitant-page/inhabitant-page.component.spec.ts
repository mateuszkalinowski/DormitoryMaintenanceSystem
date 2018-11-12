import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InhabitantPageComponent } from './inhabitant-page.component';

describe('InhabitantPageComponent', () => {
  let component: InhabitantPageComponent;
  let fixture: ComponentFixture<InhabitantPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InhabitantPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InhabitantPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
