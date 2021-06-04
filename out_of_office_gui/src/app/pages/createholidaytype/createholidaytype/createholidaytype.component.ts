import { Component, OnInit, Input} from '@angular/core';
import { Router } from '@angular/router';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';
import { HttpParams } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-createholidaytype',
  templateUrl: './createholidaytype.component.html'
})
export class CreateholidaytypeComponent implements OnInit {
  isLoadingOne = false;

  loadOne(): void {
    this.isLoadingOne = true;
    setTimeout(() => {
      this.isLoadingOne = false;
    }, 5000);
  }


  @Input() user = {};
  public addemp = true;
  public loading = true;  
  public employees: any;
  public employees2: any;
  public employeeId: any;
  public codeError: boolean = false;
  public codeError2: boolean = false;
  public displayNameError: boolean = false;
  public textError: boolean = false;
  public typeError: boolean = false;
  public newholiyday: boolean = false;
  validateForm: FormGroup;

  resetinputs(): void{
    (<HTMLInputElement>document.getElementById('code')).value = "";
    (<HTMLInputElement>document.getElementById('displayname')).value = "";
    (<HTMLInputElement>document.getElementById('text')).value = "";
    (<HTMLInputElement>document.getElementById('type')).value = "";
  }

  constructor(private route:Router, private api: ApiserviceService, private action: ActionService, private fb: FormBuilder) {
    this.validateForm = this.fb.group({
      code: ['', [Validators.required]],
      displayname: ['', [Validators.required]],
      text: ['', [Validators.required]],
      type: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id:data.id});
     })
  }

  submitForm(): void { 
    if (this.validateForm.valid) {
      let code = (<HTMLInputElement>document.getElementById('code')).value;
      let text = (<HTMLInputElement>document.getElementById('text')).value;
      let type = (<HTMLInputElement>document.getElementById('type')).value;
      let displayname = (<HTMLInputElement>document.getElementById('displayname')).value;

      if(code != "For all" && code != "Not for all") {
        this.codeError2 = true;
        this.codeError = false;
      } else {
        let params: HttpParams = new HttpParams();
        let jsonObj = {
          "code": code,
          "displayName": text,
          "text": type,
          "type": displayname
        }
          this.api.post('holiday/holidayType', {}, {"code": code, "displayName": text, "text": type, "type": displayname}).subscribe(data=>{
            console.log(data);
            this.newholiyday = true;
          });
      }
    } else {
      if((<HTMLInputElement>document.getElementById('code')).value.length == 0) this.codeError = true;
      if((<HTMLInputElement>document.getElementById('text')).value.length == 0) this.displayNameError = true;
      if((<HTMLInputElement>document.getElementById('type')).value.length == 0) this.textError = true;
      if((<HTMLInputElement>document.getElementById('displayname')).value.length == 0) this.typeError = true;
    }
  }
}
