import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';

interface DataItem {
  name: string;
  department: string;
}

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styles: [
    `
      .search-box {
        padding: 8px;
      }

      .search-box input {
        width: 188px;
        margin-bottom: 8px;
        display: block;
      }

      .search-box button {
        width: 90px;
      }

      .search-button {
        margin-right: 8px;
      }
    `
  ]
})
export class AdminDashboardComponent implements OnInit {
  @Input() user = {};
  public hideDelete = true; 
  public employees: any;
  public employees2: any;
  public employeeId: any;
  
  public show:boolean = false;

  public doSomething(): void {
    this.show = !this.show;
  }
  constructor(private route:Router, private api: ApiserviceService, private action: ActionService) { }
 
  ngOnInit(): void {
    this.action.set('getEmployees', ()=>{
      this.api.get('employee/allemployees').subscribe((data)=>{this.employees = data; this.employees2 = [...this.employees]});
    });
     this.api.get('leaverequest/employees').subscribe((data)=>{this.employees = data; this.employees2 = [...this.employees]});
  }

  searchValue = '';
  visible = false;
  listOfData: DataItem[] = [
    {
      name: 'John Brown',
      department: 'IT',
      
    },
    {
      name: 'Jim Green',
      department: 'Testing',
      
    },
    {
      name: 'Joe Black',
      department: 'HR',
     
    },
    {
      name: 'Jim Red',
      department: 'iOS',
     
    }
  ];
  listOfDisplayData = [...this.listOfData];


  reset(): void {
    this.searchValue = '';
    this.search();
  }


  search(): void {
    this.visible = false;
    this.employees = this.employees2.filter((item: any) => item.firstnameLastName.indexOf(this.searchValue) !== -1);
  }
}
