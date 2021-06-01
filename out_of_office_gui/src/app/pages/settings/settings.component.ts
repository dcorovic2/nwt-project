import { Component, OnInit } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { ApiserviceService } from '../../shared/services/apiservice.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { identifierModuleUrl } from '@angular/compiler';
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
  public confirmnewpasswordError: boolean = false;
  public passwordchanged: boolean = false;


  constructor(
    private route: Router,
    private api: ApiserviceService,
    private fb: FormBuilder
  ) {
    this.validateForm = this.fb.group({
      password: ['', [Validators.required]],
      newpassword: ['', [Validators.required]],
      confnewpass: ['', [Validators.required]],
    });
  }

  resetinputs(): void{
    (<HTMLInputElement>document.getElementById('password')).value = "";
    (<HTMLInputElement>document.getElementById('newpassword')).value = "";
    (<HTMLInputElement>document.getElementById('confnewpass')).value = "";
    this.newpasswordError = false;
    this.confirmnewpasswordError = false;
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
    
    if (this.validateForm.valid) {
      console.log("USLO U METOD");
      let password = (<HTMLInputElement>document.getElementById('password')).value;
      let newpass = (<HTMLInputElement>document.getElementById('newpassword')).value;
      let confnewpass = (<HTMLInputElement>document.getElementById('confnewpass')).value;
      let username = localStorage.getItem('username');
      let params: HttpParams = new HttpParams();
      params = params.append('password', password);
      this.passwordError = false;
      this.newpasswordError = false;
      this.confirmnewpasswordError = false;

      if(newpass != confnewpass)  this.confirmnewpasswordError = true;

    this.api
      .post('users/password', { password: password, username: username  })
      .subscribe((data) => {

        if (data != null){
          this.passwordError = false;
          data=JSON.parse(data);
          console.log(data.id);
          console.log(data.password);
          console.log(confnewpass);
          this.api
          .patch(
          'users/' + data.id,
          {},
          {
            password: confnewpass
          }
          )
          .subscribe((data) => {
              this.passwordchanged = true;
              console.log(data);
              this.passwordError = false;
              this.newpasswordError = false;
              this.confirmnewpasswordError = false;
              this.passwordchanged = true;
          });
        }
        else {
          console.log("Uslo u else!")
          this.passwordError = true;
        }
       
        //setTimeout(()=>this.route.navigate(['dashboard']),5000);
      });

    }else{
      if((<HTMLInputElement>document.getElementById('password')).value.length == 0) this.passwordError = true;
      else this.passwordError = false;
      if((<HTMLInputElement>document.getElementById('newpassword')).value.length == 0) this.newpasswordError = true;
      else this.newpasswordError = false;
      if((<HTMLInputElement>document.getElementById('confnewpass')).value.length == 0) this.confirmnewpasswordError = true;
      else this.confirmnewpasswordError = false;
    }
  }
}
