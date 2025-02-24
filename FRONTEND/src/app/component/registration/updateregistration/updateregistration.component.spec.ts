import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateregistrationComponent } from './updateregistration.component';

describe('UpdateregistrationComponent', () => {
  let component: UpdateregistrationComponent;
  let fixture: ComponentFixture<UpdateregistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateregistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateregistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
