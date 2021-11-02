import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Logout.ViewComponent } from './logout.view.component';

describe('Logout.ViewComponent', () => {
  let component: Logout.ViewComponent;
  let fixture: ComponentFixture<Logout.ViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ Logout.ViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(Logout.ViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
