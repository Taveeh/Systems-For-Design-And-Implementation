import {Cat} from "./cat.model";
import {Customer} from "./customer.model";

export interface Purchase {
  cat: number;
  customer: number;
  price: number;
  dateAcquired: Date;
  review: number;
}
