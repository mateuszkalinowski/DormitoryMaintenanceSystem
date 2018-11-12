import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InhabitantRequestsPageComponent } from './inhabitant-requests-page.component';

describe('InhabitantRequestsPageComponent', () => {
  let component: InhabitantRequestsPageComponent;
  let fixture: ComponentFixture<InhabitantRequestsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InhabitantRequestsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InhabitantRequestsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
