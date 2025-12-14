import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodUpload } from './food-upload';

describe('FoodUpload', () => {
  let component: FoodUpload;
  let fixture: ComponentFixture<FoodUpload>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoodUpload]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoodUpload);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
