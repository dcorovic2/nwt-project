import { Component, OnInit, Input} from '@angular/core';
import { Router } from '@angular/router';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';

@Component({
  selector: 'app-addemployee',
  templateUrl: './addemployee.component.html'
})

export class AddemployeeComponent implements OnInit {
  isLoadingOne = false;

  loadOne(): void {
    this.isLoadingOne = true;
    setTimeout(() => {
      this.isLoadingOne = false;
    }, 5000);
  }
  
  resetinputs(): void{
    (<HTMLInputElement>document.getElementById('firstname')).value = "";
    (<HTMLInputElement>document.getElementById('jobrole')).value = "";
    (<HTMLInputElement>document.getElementById('tel')).value = "";
    (<HTMLInputElement>document.getElementById('username')).value = "";
    (<HTMLInputElement>document.getElementById('alowance')).value = "";
    (<HTMLInputElement>document.getElementById('lastname')).value = "";
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

  constructor(private route:Router, private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id:data.id});
     })

    this.action.set('getEmployees', ()=>{
      this.api.get('employee/allemployees').subscribe((data)=>{this.employees = data; this.loading=false; this.employees2 = [...this.employees]});
    });
     this.api.get('employee/allemployees').subscribe((data)=>{this.employees = data; this.loading=false; this.employees2 = [...this.employees]});
  }

}
