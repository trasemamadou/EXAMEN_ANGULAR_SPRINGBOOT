import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListregistrationComponent } from './listregistration.component';

describe('ListregistrationComponent', () => {
  let component: ListregistrationComponent;
  let fixture: ComponentFixture<ListregistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListregistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListregistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
