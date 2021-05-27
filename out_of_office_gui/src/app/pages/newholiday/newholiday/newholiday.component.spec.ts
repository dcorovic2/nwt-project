import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewholidayComponent } from './newholiday.component';

describe('NewholidayComponent', () => {
  let component: NewholidayComponent;
  let fixture: ComponentFixture<NewholidayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewholidayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewholidayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
