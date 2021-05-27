import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateholidayComponent } from './createholiday.component';

describe('CreateholidayComponent', () => {
  let component: CreateholidayComponent;
  let fixture: ComponentFixture<CreateholidayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateholidayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateholidayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
