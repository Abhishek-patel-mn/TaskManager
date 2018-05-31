import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {
  data:any;
  constructor(public http: HttpClient) { }
  
	getData(url) {
		this.data = this.http.get(url);
		console.log("Response: " + this.data);
		return this.data;
	}
}
