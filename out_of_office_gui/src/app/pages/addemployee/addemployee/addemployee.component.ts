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
  
  @Input() user = {};
  public addemp = true;
  public loading = true;  
  public employees: any;
  public employees2: any;
  public employeeId: any;

  constructor(private route:Router, private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {
    this.action.set('getEmployees', ()=>{
      this.api.get('employee/allemployees').subscribe((data)=>{this.employees = data; this.loading=false; this.employees2 = [...this.employees]});
    });
     this.api.get('employee/allemployees').subscribe((data)=>{this.employees = data; this.loading=false; this.employees2 = [...this.employees]});
  }

}
