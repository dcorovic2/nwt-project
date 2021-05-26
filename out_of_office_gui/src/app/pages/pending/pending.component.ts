import { Component, Input, OnInit } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-pending',
  templateUrl: './pending.component.html'
})
export class PendingComponent implements OnInit {
  @Input() id: any;
  public user: any={};
  public requests: any = [];
  searchValue = '';
  visible = false;

  constructor(private api: ApiserviceService) { }

  ngOnInit(): void {
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id: data.id});
      this.api.get('leaverequest/requests/employee',{employeeId: this.user.id}).subscribe((data)=>this.requests = data);
    })


  }

  reset(): void {
    this.searchValue = '';
    this.search();
  }


  search(): void {
    this.visible = false;
  }

}
