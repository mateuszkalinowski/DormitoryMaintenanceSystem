import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InhabitantNewsPageComponent } from './inhabitant-news-page.component';

describe('InhabitantNewsPageComponent', () => {
  let component: InhabitantNewsPageComponent;
  let fixture: ComponentFixture<InhabitantNewsPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InhabitantNewsPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InhabitantNewsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
