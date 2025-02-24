import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateclasseComponent } from './updateclasse.component';

describe('UpdateclasseComponent', () => {
  let component: UpdateclasseComponent;
  let fixture: ComponentFixture<UpdateclasseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateclasseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateclasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
