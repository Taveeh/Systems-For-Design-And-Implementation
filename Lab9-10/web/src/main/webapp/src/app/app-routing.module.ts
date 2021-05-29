import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CatsComponent} from "./cats/cats.component";
import {RouterModule, Routes} from "@angular/router";
import {CatDetailComponent} from "./cat-detail/cat-detail.component";
import {FoodComponent} from "./food/food.component";
import {CustomerComponent} from "./customer/customer.component";

const routes: Routes = [
  {path: 'cat', component: CatsComponent},
  {path: 'cat/detail/:id', component: CatDetailComponent},
  {path: 'food', component: FoodComponent},
  {path: 'customer', component: CustomerComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
