import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ApiserviceService} from '../../shared/services/apiservice.service';
import * as queryParse from 'query-string';

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
   // this.api.get('/notification/all_notifications').subscribe(data=>console.log(data));
    this.api.get('employee/employee',{jmbg:"8523697414562"}).subscribe((data=>console.log(data)))
  });
    
   // this.route.navigate(['dashboard']);
  }

}
