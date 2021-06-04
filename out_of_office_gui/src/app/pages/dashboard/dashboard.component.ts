import {Component, OnInit} from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  public user  = {id:"", firstnameLastName:"", email:""};
  public info = {allowance: "", remainingDays: ""};
  public role: any;
  public leavehistory: any;

  constructor(private api: ApiserviceService){}
  ngOnInit(){
    switch(localStorage.getItem('role')){
      case "ROLE_ADMIN": this.role = 'admin'; break;
      case "ROLE_CLIENT": this.role = 'employee'; break;
    }
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id:data.id});
      this.info = data; 
      this.api.get('leaverequest/requestType/'+data.id).subscribe((data:any)=>{
        this.leavehistory = data;
      });
     })
  }
}
