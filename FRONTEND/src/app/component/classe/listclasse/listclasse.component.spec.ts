import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListclasseComponent } from './listclasse.component';

describe('ListclasseComponent', () => {
  let component: ListclasseComponent;
  let fixture: ComponentFixture<ListclasseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListclasseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListclasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
