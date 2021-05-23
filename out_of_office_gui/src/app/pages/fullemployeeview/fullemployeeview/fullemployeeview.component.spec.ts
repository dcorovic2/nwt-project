import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FullemployeeviewComponent } from './fullemployeeview.component';

describe('FullemployeeviewComponent', () => {
  let component: FullemployeeviewComponent;
  let fixture: ComponentFixture<FullemployeeviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FullemployeeviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FullemployeeviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
