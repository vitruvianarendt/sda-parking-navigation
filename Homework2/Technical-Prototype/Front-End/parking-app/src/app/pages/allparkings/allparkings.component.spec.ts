import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllparkingsComponent } from './allparkings.component';

describe('AllparkingsComponent', () => {
  let component: AllparkingsComponent;
  let fixture: ComponentFixture<AllparkingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllparkingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllparkingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
