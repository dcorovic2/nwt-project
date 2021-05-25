import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ApiserviceService} from '../../shared/services/apiservice.service';
import * as queryParse from 'query-string';

import  jwt_decode from 'jwt-decode';
import { JwtToken } from 'src/app/shared/interfaces/jwt-token';
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
    const token = jwt_decode<JwtToken>(data);
    localStorage.setItem('role', token.auth[0].authority);
    localStorage.setItem('username', token.sub);
  });
  setTimeout(()=>this.route.navigate(['dashboard']),3000); 
}
    

  

}
