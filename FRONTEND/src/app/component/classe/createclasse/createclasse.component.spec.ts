import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateclasseComponent } from './createclasse.component';

describe('CreateclasseComponent', () => {
  let component: CreateclasseComponent;
  let fixture: ComponentFixture<CreateclasseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateclasseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateclasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
