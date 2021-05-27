import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateholidaytypeComponent } from './createholidaytype.component';

describe('CreateholidaytypeComponent', () => {
  let component: CreateholidaytypeComponent;
  let fixture: ComponentFixture<CreateholidaytypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateholidaytypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateholidaytypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
