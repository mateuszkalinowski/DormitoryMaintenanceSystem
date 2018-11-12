import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InhabitantRequestDetailsComponent } from './inhabitant-request-details.component';

describe('InhabitantRequestDetailsComponent', () => {
  let component: InhabitantRequestDetailsComponent;
  let fixture: ComponentFixture<InhabitantRequestDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InhabitantRequestDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InhabitantRequestDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
