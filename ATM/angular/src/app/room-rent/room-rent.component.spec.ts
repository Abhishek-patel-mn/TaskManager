import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomRentComponent } from './room-rent.component';

describe('RoomRentComponent', () => {
  let component: RoomRentComponent;
  let fixture: ComponentFixture<RoomRentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomRentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomRentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
