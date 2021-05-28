import { Component, OnInit } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { ApiserviceService } from '../../shared/services/apiservice.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
})
export class SettingsComponent implements OnInit {
  isLoadingOne = false;
  public settings = true;
  public user  = {id:"", firstnameLastName:"", email:""};
  validateForm: FormGroup;
  public usernameError: boolean = false;
  public passwordError: boolean = false;

  constructor(
    private route: Router,
    private api: ApiserviceService,
    private fb: FormBuilder
  ) {
    this.validateForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }
  resetinputs(): void{
    (<HTMLInputElement>document.getElementById('password')).value = "";
    (<HTMLInputElement>document.getElementById('newpassword')).value = "";
    (<HTMLInputElement>document.getElementById('confnewpass')).value = "";
  }

  loadOne(): void {
    this.isLoadingOne = true;
    setTimeout(() => {
      this.isLoadingOne = false;
    }, 5000);
  }
  ngOnInit(): void {
    this.api
      .get('employee/employee/username', {
        username: localStorage.getItem('username'),
      })
      .subscribe((data: any) => {
        Object.assign(this.user, {
          firstnameLastName: data.firstnameLastName,
          email: data.email,
          id: data.id,
        });
      });
  }

  submitForm(): void {
    let username = (<HTMLInputElement>document.getElementById('username'))
      .value;
    let password = (<HTMLInputElement>document.getElementById('password'))
      .value;
    let params: HttpParams = new HttpParams();

    params = params.append('username', username);
    this.api
      .post('users/username', { username: username })
      .subscribe((data) => {
        console.log(data.length);
        if (data.length != 0) this.usernameError = false;
        else this.usernameError = true;

        console.log(JSON.parse(data).password);
        //setTimeout(()=>this.route.navigate(['dashboard']),5000);
      });
  }
}
