import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowByIDComponent } from './show-by-id.component';

describe('ShowByIDComponent', () => {
  let component: ShowByIDComponent;
  let fixture: ComponentFixture<ShowByIDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowByIDComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowByIDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
