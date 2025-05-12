import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayUserByIdComponent } from './display-user-by-id.component';

describe('DisplayUserByIdComponent', () => {
  let component: DisplayUserByIdComponent;
  let fixture: ComponentFixture<DisplayUserByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayUserByIdComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayUserByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
