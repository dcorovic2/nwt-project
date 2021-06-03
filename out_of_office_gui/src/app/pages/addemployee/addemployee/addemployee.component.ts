import { Component, OnInit, Input} from '@angular/core';
import { Router } from '@angular/router';
import { HttpParams } from '@angular/common/http';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-addemployee',
  templateUrl: './addemployee.component.html'
})

export class AddemployeeComponent implements OnInit {
  isLoadingOne = false;

  validateForm: FormGroup;
  public firstnameError: boolean = false;
  public jobroleError: boolean = false;
  public telError: boolean = false;
  public usernameError: boolean = false;
  public alowanceError: boolean = false;
  public jmbgError: boolean = false;
  public emailError: boolean = false;
  public departmentError: boolean = false;
  public passError: boolean = false;
  public confpassError: boolean = false;
  public newemployeeadded: boolean = false;

  loadOne(): void {
    this.isLoadingOne = true;
    setTimeout(() => {
      this.isLoadingOne = false;
    }, 5000);
  }
  
  resetinputs(): void{
    console.log(this.firstnameError);

    (<HTMLInputElement>document.getElementById('firstname')).value = "";
    (<HTMLInputElement>document.getElementById('jobrole')).value = "";
    (<HTMLInputElement>document.getElementById('tel')).value = "";
    (<HTMLInputElement>document.getElementById('username')).value = "";
    (<HTMLInputElement>document.getElementById('alowance')).value = "";
    (<HTMLInputElement>document.getElementById('jmbg')).value = "";
    (<HTMLInputElement>document.getElementById('email')).value = "";
    (<HTMLInputElement>document.getElementById('department')).value = "";
    (<HTMLInputElement>document.getElementById('pass')).value = "";
    (<HTMLInputElement>document.getElementById('confpass')).value = "";
  }
  @Input() user = {};
  public addemp = true;
  public loading = true;  
  public employees: any;
  public employees2: any;
  public employeeId: any;

  constructor(private route:Router, private api: ApiserviceService, private action: ActionService, private fb: FormBuilder) {
    this.validateForm = this.fb.group({
      firstname: ['', [Validators.required]],
      jobrole: ['', [Validators.required]],
      tel: ['', [Validators.required]],
      username: ['', [Validators.required]],
      alowance: ['', [Validators.required]],
      jmbg: ['', [Validators.required]],
      email: ['', [Validators.required]],
      department: ['', [Validators.required]],
      pass: ['', [Validators.required]],
      confpass: ['', [Validators.required]]
    });
   }

  ngOnInit(): void {
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id:data.id});
     })

    this.action.set('getEmployees', ()=>{
      this.api.get('employee/allemployees').subscribe((data)=>{this.employees = data; this.loading=false; this.employees2 = [...this.employees]});
    });
     this.api.get('employee/allemployees').subscribe((data)=>{this.employees = data; this.loading=false; this.employees2 = [...this.employees]});
  }

  submitForm(): void {
    let  firstname= (<HTMLInputElement>document.getElementById('firstname')).value;
      let  jobrole= (<HTMLInputElement>document.getElementById('jobrole')).value;
      let  tel= (<HTMLInputElement>document.getElementById('tel')).value;
      let  username= (<HTMLInputElement>document.getElementById('username')).value;
      let  allowance= (<HTMLInputElement>document.getElementById('alowance')).value;
      let  jmbg= (<HTMLInputElement>document.getElementById('jmbg')).value;
      let  email= (<HTMLInputElement>document.getElementById('email')).value;
      let  department= (<HTMLInputElement>document.getElementById('department')).value;
      let  pass= (<HTMLInputElement>document.getElementById('pass')).value;
      let  confpass= (<HTMLInputElement>document.getElementById('confpass')).value;

    if (this.validateForm.valid) {
      this.firstnameError = false;
      this.jobroleError = false;
      this.telError = false;
      this.usernameError = false;
      this.alowanceError = false;
      this.jmbgError = false;
      this.emailError = false;
      this.departmentError = false;
      this.passError = false;
      this.confpassError = false;

      this.api.get('users/' + username).subscribe((data)=>{
            this.usernameError = true;
          });
      this.usernameError?true:       
        this.api.post('employee/employee',{}, {
          allowance: allowance,
          departmentId: 2,
          email: email,
          firstnameLastName: firstname,
          hireDate: new Date(),
          jmbg: jmbg,
          jobRole: jobrole,
          password: pass,
          phoneNumber: tel,
          remainingDays: allowance,
          roleId: 2,
          username: username
        }).subscribe((data)=>this.newemployeeadded=true); 

    }else{
      if((<HTMLInputElement>document.getElementById('firstname')).value.length == 0) this.firstnameError = true;
      else this.firstnameError = false;
      if((<HTMLInputElement>document.getElementById('jobrole')).value.length == 0) this.jobroleError = true;
      else this.jobroleError = false;
      if((<HTMLInputElement>document.getElementById('tel')).value.length == 0) this.telError = true;
      else{
        let isnum = /^\d+$/.test(tel);
        if(!isnum){
          this.telError = true;
        }else{
          this.telError = false;
      }
      }
      if((<HTMLInputElement>document.getElementById('username')).value.length == 0) this.usernameError = true;
      else this.usernameError = false;
      if((<HTMLInputElement>document.getElementById('alowance')).value.length == 0) this.alowanceError = true;
      else{
        var y: number = +allowance;
        if(y < 21) {
          this.alowanceError = true;
        }else{
          this.alowanceError = false;
      }
      }
      if((<HTMLInputElement>document.getElementById('jmbg')).value.length == 0) this.jmbgError = true;
      else{
        let isnumjmbg = /^\d+$/.test(jmbg);
        if(!isnumjmbg){
          this.jmbgError = true;
        }else{
          this.jmbgError = false;
        }
        if(jmbg.length == 13){
          this.jmbgError = false;
        }else{
          this.jmbgError = true;
        }
      }
      if((<HTMLInputElement>document.getElementById('email')).value.length == 0) this.emailError = true;
      else{
        var regexp = new RegExp(/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/);
        var testemail = regexp.test(email);
        if(testemail){
          this.emailError = false;
        }else{
          this.emailError = true;
        }
      }
      if((<HTMLInputElement>document.getElementById('department')).value.length == 0) this.departmentError = true;
      else this.departmentError = false;
      if((<HTMLInputElement>document.getElementById('pass')).value.length == 0) this.passError = true;
      else this.passError = false;
      if((<HTMLInputElement>document.getElementById('confpass')).value.length == 0) this.confpassError = true;
      else{
        if(pass != confpass)  this.confpassError = true;
        else this.confpassError = false;
      }
    }
  }








}
