import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HolidaytypelistComponent } from './holidaytypelist.component';

describe('HolidaytypelistComponent', () => {
  let component: HolidaytypelistComponent;
  let fixture: ComponentFixture<HolidaytypelistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HolidaytypelistComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HolidaytypelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
