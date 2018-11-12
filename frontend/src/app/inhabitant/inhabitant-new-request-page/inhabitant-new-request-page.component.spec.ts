import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InhabitantNewRequestPageComponent } from './inhabitant-new-request-page.component';

describe('InhabitantNewRequestPageComponent', () => {
  let component: InhabitantNewRequestPageComponent;
  let fixture: ComponentFixture<InhabitantNewRequestPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InhabitantNewRequestPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InhabitantNewRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
