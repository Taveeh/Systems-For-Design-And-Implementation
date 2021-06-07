import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SensorService {

  sensorUrl = "http://localhost:8080/api/sensors"
  constructor(private httpClient: HttpClient) { }

  getSensors(): Observable<any> {
    return this.httpClient.get<any>(this.sensorUrl);
  }


  getSensorNames(): Observable<any> {
    return this.httpClient.get(`${this.sensorUrl}names`);
  }

  getSensorsForName(name: String): Observable<any> {
    return this.httpClient.get(`${this.sensorUrl}/${name}`)
  }

}
