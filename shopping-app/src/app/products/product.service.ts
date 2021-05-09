import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Product } from 'src/app/products/product.model';
import { Subject } from 'rxjs/internal/Subject';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';

const BACKEND_URL = environment.apiURL + '/products/';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private products: Product[] = [];
  private productsUpdated = new Subject<Product[]>();

  constructor(private http: HttpClient, private router: Router) { }

  getProductsUpdatedListener(): Observable<Product[]> {
    return this.productsUpdated.asObservable();
  }

  getAllProducts(): void {
    console.log('Calling get all products');
    this.http.get<Product[]>(BACKEND_URL).subscribe((results) => {
      console.log('All products', results);
      if (results) {
        this.products = results;
        this.productsUpdated.next([...this.products]);
      } else {
        this.productsUpdated.next([]);
      }
    });
  }

  getProduct(productId: string): Observable<Product> {
    return this.http.get<Product>(BACKEND_URL + productId);
  }

  addProduct(productName: string, productDescription: string): void {
    const productData: Product = {
      id: null,
      productName: productName,
      productDescription: productDescription
    };

    this.http
      .post<Product>(
        BACKEND_URL,
        productData)
      .subscribe((response) => {
        this.router.navigate(['/']);
    });
  }

  editProduct(productId: string, productName: string, productDescription: string): void {
    const productData: Product = {
      id: productId,
      productName: productName,
      productDescription: productDescription
    };

    this.http.put<Product>(
      BACKEND_URL + productId,
      productData)
      .subscribe((response) => {
        this.router.navigate(['/']);
      });
  }

  deleteProduct(productId: string): Observable<any> {
    return this.http.delete(BACKEND_URL + productId);
  }
}
