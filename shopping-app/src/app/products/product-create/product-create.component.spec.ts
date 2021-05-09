import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProductCreateComponent } from './product-create.component';

import { RouterTestingModule } from '@angular/router/testing';

import { ProductService } from 'src/app/products/product.service';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { Product } from 'src/app/products/product.model';

describe('ProductCreateComponent', () => {
  let component: ProductCreateComponent;
  let fixture: ComponentFixture<ProductCreateComponent>;

  // Overriding Functions
  class MockProductService {
    addProduct(productName: string, productDescription: string): void {}
    editProduct(productId: string, productName: string, productDescription: string): void {}
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [{ provide: ProductService, useClass: MockProductService }],
      declarations: [ ProductCreateComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
