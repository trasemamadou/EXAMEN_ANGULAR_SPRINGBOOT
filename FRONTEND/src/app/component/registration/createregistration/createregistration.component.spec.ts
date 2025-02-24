import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateregistrationComponent } from './createregistration.component';

describe('CreateregistrationComponent', () => {
  let component: CreateregistrationComponent;
  let fixture: ComponentFixture<CreateregistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateregistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateregistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
