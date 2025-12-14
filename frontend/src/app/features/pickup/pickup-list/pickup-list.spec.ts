import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PickupList } from './pickup-list';

describe('PickupList', () => {
  let component: PickupList;
  let fixture: ComponentFixture<PickupList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PickupList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PickupList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
