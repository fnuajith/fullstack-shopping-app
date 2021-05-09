import { Component, OnInit, OnDestroy } from '@angular/core';
import { Product } from 'src/app/products/product.model';
import { Subscription } from 'rxjs/internal/Subscription';
import { ProductService } from 'src/app/products/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit, OnDestroy {

  products: Product[] = [];
  productSubscription: Subscription;
  isLoading = false;

  constructor(public productService: ProductService) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.productSubscription = this.productService.getProductsUpdatedListener()
      .subscribe((returnedProducts: Product[]) => {
        console.log('Products...', returnedProducts);
        this.isLoading = false;
        this.products = returnedProducts;
      });
    this.productService.getAllProducts();
  }

  onDelete(productId: string): void {
    this.isLoading = true;
    this.productService.deleteProduct(productId).subscribe(() => {
      this.productService.getAllProducts();
    }, () => {
      this.isLoading = false;
    });
  }

  ngOnDestroy(): void {
  }

}
