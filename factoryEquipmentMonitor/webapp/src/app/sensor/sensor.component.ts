import { Component, OnInit } from '@angular/core';
import {Sensor} from "../sensor.model";
import {SensorService} from "../sensor.service";

@Component({
  selector: 'app-sensor',
  templateUrl: './sensor.component.html',
  styleUrls: ['./sensor.component.css']
})
export class SensorComponent implements OnInit {

  sensors: Sensor[];
  constructor(private sensorService: SensorService) { }

  getSensors() {
    this.sensorService.getSensors().subscribe(
      sensors => {
        console.log(sensors);
        this.sensors = sensors.sensors;
      }
    )
  }
  ngOnInit(): void {
    this.getSensors();
  }

}
