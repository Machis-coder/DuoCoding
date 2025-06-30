import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageFooter } from './page-footer';

describe('PageFooter', () => {
  let component: PageFooter;
  let fixture: ComponentFixture<PageFooter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PageFooter]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PageFooter);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
