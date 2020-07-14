import { Injectable } from '@angular/core';
import {Employee} from './Employee';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmployeeServiceService {

  constructor(private http : HttpClient) { }
  selectedEmployee:Employee;
  employees:Employee[];

  baseURL = "http://localhost:8080/company/employees";

    regEmployee(emp:Employee){
        return this.http.post(this.baseURL,emp);
    }

    getEmpolyees(){
      return this.http.get(this.baseURL);
    }

    putEmployee(emp : Employee){
      return this.http.put(this.baseURL + '/' + emp.id,emp);
    }

    empDelete(_id:string){
      console.log(_id)

      let url = new URL( this.baseURL + '/' + _id)
      return this.http.delete(url.toString());
    }


}
