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
  public passwordError: boolean = false;
  public newpasswordError : boolean = false;
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
    let password = (<HTMLInputElement>document.getElementById('password')).value;
    let newpassword = (<HTMLInputElement>document.getElementById('newpassword')).value;
    let confirmnewpassword = (<HTMLInputElement>document.getElementById('confnewpass')).value;
    let username = localStorage.getItem('username');
    let params: HttpParams = new HttpParams();

    params = params.append('password', password);
    this.api
      .post('users/password', { password: password, username: username  })
      .subscribe((data) => {
        console.log(data);
        if (data.length != 0) this.passwordError = false;
        else this.passwordError = true;
        //setTimeout(()=>this.route.navigate(['dashboard']),5000);
      });
  }
}
