import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListofemployeesComponent } from './listofemployees.component';

describe('ListofemployeesComponent', () => {
  let component: ListofemployeesComponent;
  let fixture: ComponentFixture<ListofemployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListofemployeesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListofemployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
