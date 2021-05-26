import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { ApiserviceService } from '../../shared/services/apiservice.service';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms'
 
import  jwt_decode from 'jwt-decode';
import { JwtToken } from 'src/app/shared/interfaces/jwt-token';


@Component({ 
  selector: 'app-loginform',
  templateUrl: './loginform.component.html'
})

export class LoginformComponent implements OnInit {
  isLoadingOne = false;

  loadOne(): void {
    this.isLoadingOne = true;
    setTimeout(() => {
      this.isLoadingOne = false;
    }, 5000);
  }


  validateForm: FormGroup;
  public  usernameError: boolean=false;
  public passwordError: boolean=false;

  constructor(private route:Router, private api: ApiserviceService, private fb: FormBuilder) {
    this.validateForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }
  

  ngOnInit(): void {
  }


  submitForm(): void {
    if (this.validateForm.valid) {
      let username = (<HTMLInputElement>document.getElementById('username')).value;
      let password = (<HTMLInputElement>document.getElementById('password')).value;
      let params: HttpParams = new HttpParams();

      params = params.append('username', username);
        this.api.post('users/signin', {username:username, password:password}).subscribe(data=>{
        localStorage.setItem('token', data);
        const token = jwt_decode<JwtToken>(data);
        localStorage.setItem('role', token.auth[0].authority);
        localStorage.setItem('username', token.sub);

        setTimeout(()=>this.route.navigate(['dashboard']),5000);
      });
 
    } else {
      console.log('There is a problem with the form');

      if((<HTMLInputElement>document.getElementById('username')).value.length == 0)
      {
       this.usernameError = true;
      }

      if((<HTMLInputElement>document.getElementById('password')).value.length == 0) {
        this.passwordError = true;
        console.log("uslo");
      }
    }
  }
      

}
    

  