import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {SensorComponent} from "./sensor/sensor.component";
import {SensorsNameComponent} from "./sensors-name/sensors-name.component";


const routes: Routes = [
  {path: 'all', component: SensorComponent},
  {path: 'sensors', component: SensorsNameComponent}
]
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
