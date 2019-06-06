import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResearchModalComponent } from './research-modal.component';

describe('ResearchModalComponent', () => {
  let component: ResearchModalComponent;
  let fixture: ComponentFixture<ResearchModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResearchModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResearchModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
