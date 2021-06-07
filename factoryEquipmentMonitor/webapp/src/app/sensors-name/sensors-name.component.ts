import { Component, OnInit } from '@angular/core';
import {SensorService} from "../sensor.service";
import {Sensor} from "../sensor.model";

@Component({
  selector: 'app-sensors-name',
  templateUrl: './sensors-name.component.html',
  styleUrls: ['./sensors-name.component.css']
})
export class SensorsNameComponent implements OnInit {

  sensors: String[];
  constructor(private service: SensorService) { }
  checked: boolean = false;
  sensorNames: Map<String, Sensor[]> = new Map<String, Sensor[]>();
  getSensors() {
    this.service.getSensorNames().subscribe(
      sensors => {
        console.log(sensors);
        this.sensors = sensors;
      }
    )
  }


  pressButton(): void {
    this.checked = true;
    for (let name of this.sensors) {
      this.service.getSensorsForName(name).subscribe(
        sensors => {
          console.log(sensors)
          this.sensorNames.set(name, sensors.sensors);
        }
      )
    }
    setTimeout(() => {
      if (this.checked === true) {
        this.pressButton()
      }
    }, 5000)
  }
  ngOnInit(): void {
    this.getSensors();

  }

}
