import { Component, OnInit } from '@angular/core';
import { DataServiceService } from '../shared-service/data-service.service'

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  userdata:any=[];
  constructor(public ds : DataServiceService) { }
  
  ngOnInit() {
      this.ds.getData("secured/users").subscribe(data=>{
			this.userdata = data['data'];
	  });
   }

}
