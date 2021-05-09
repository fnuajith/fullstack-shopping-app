import { Component, OnInit, OnDestroy } from '@angular/core';
import { ProductService } from 'src/app/products/product.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Product } from 'src/app/products/product.model';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.scss']
})
export class ProductCreateComponent implements OnInit, OnDestroy {

  isLoading = false;
  mode = 'create';
  productId: string;
  product: Product;
  form: FormGroup;

  constructor(public productService: ProductService, public route: ActivatedRoute) { }

  ngOnInit(): void {

    this.form = new FormGroup({
      productName: new FormControl(null, {
        validators: [Validators.required]
      }),
      productDescription: new FormControl(null, {
        validators: [Validators.required]
      })
    });

    this.route.paramMap.subscribe((paramMap: ParamMap) => {
      if (paramMap.has('productId')) {
        this.mode = 'edit';
        this.productId = paramMap.get('productId');
        this.isLoading = true;
        this.productService.getProduct(this.productId)
          .subscribe(productData => {
            this.isLoading = false;
            this.product = {...productData};
            this.form.setValue({
              productName: this.product.productName,
              productDescription: this.product.productDescription
            });
          });
      } else {
        this.mode = 'create';
        this.productId = null;
      }
    });

  }

  onSaveProduct(): void {
    if (this.form.invalid) {
      return;
    }

    this.isLoading = true;
    if (this.mode === 'create') {
      this.productService.addProduct(
        this.form.value.productName,
        this.form.value.productDescription);
    } else {
      this.productService.editProduct(
        this.productId,
        this.form.value.productName,
        this.form.value.productDescription);
    }
    this.form.reset();
  }

  ngOnDestroy(): void {
  }

}
