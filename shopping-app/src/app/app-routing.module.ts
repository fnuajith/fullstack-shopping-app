import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductListComponent } from 'src/app/products/product-list/product-list.component';
import { ProductCreateComponent } from 'src/app/products/product-create/product-create.component';

const routes: Routes = [
  { path: '', component: ProductListComponent},
  { path: 'create', component: ProductCreateComponent},
  { path: 'edit/:productId', component: ProductCreateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
