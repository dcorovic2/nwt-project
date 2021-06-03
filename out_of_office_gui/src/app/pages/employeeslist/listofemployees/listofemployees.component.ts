import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';
import { NzTablePaginationPosition, NzTablePaginationType} from 'ng-zorro-antd/table'; 

interface DataItem {
  name: string;
  department: string;
}

@Component({
  selector: 'app-listofemployees',
  templateUrl: './listofemployees.component.html',
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

export class ListofemployeesComponent implements OnInit {
  public user = {};
  public hideDelete = true; 
  public spinner = true;
  public loading = true; 
  public employees: any;
  public employees2: any;
  public employeeId: any;
  public position: NzTablePaginationPosition | undefined;
  public paginationType: NzTablePaginationType | undefined;

  constructor(private route:Router, private api: ApiserviceService, private action: ActionService) { }

  fullPage(username:string): void {
    this.action.showDrawer(username);
    //this.route.navigate(['layout/employeeview'], {queryParams:{username: username, id: id}});
  }

  ngOnInit(): void {
    this.action.set('getEmployees', ()=>{
      this.loading = true;
      this.api.get('employee/allemployees').subscribe((data)=>{ 
      let dataa1 = data.filter((dataFiltered: any) => dataFiltered.role.code != "ADMIN");
      this.employees = dataa1;  
      this.loading=false; 
      this.employees2 = [...this.employees]});
    });
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email});
     })

     this.api.get('employee/allemployees')
     .subscribe((data)=>{
      let dataa1 = data.filter((dataFiltered: any) => dataFiltered.role.code != "ADMIN");
      this.employees = dataa1; 
       this.loading=false; 
       this.employees2 = [...this.employees]
      });

  }
public hide(event:any){
  this.hideDelete=event;
}
  searchValue = '';
  visible = false;

  reset(): void {
    this.searchValue = '';
    this.search();
  }

  search(): void {
    this.visible = false;
    this.employees = this.employees2.filter((item: any) => item.firstnameLastName.indexOf(this.searchValue) !== -1);
  }
}
