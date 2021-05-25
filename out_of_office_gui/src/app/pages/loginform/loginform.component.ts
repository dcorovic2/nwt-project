import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ApiserviceService} from '../../shared/services/apiservice.service';
import * as queryParse from 'query-string';

import  jwt_decode from 'jwt-decode';
@Component({
  selector: 'app-loginform',
  templateUrl: './loginform.component.html'
})
export class LoginformComponent implements OnInit {

  constructor(private route:Router, private api: ApiserviceService) { }

  ngOnInit(): void {
  }

  proceed(){
    let username = (<HTMLInputElement>document.getElementById('username')).value;
    let password = (<HTMLInputElement>document.getElementById('password')).value;
    let params: HttpParams = new HttpParams();
    params = params.append('username', username);
    this.api.post('users/signin',{username:username, password:password}).subscribe(data=>{
    localStorage.setItem('token', data);
    console.log(jwt_decode(data));
    this.api.get('notification/all_notification_types').subscribe(data=>console.log(data));
    this.api.get('holiday/getlistofholidays').subscribe(data=>console.log(data));
    this.api.get('leaverequest/requests',{status_id: 0}).subscribe(data=>console.log(data));
    this.api.get('employee/allemployees').subscribe((data=>console.log(data)))
  });
    
   // this.route.navigate(['dashboard']);
  }

}
