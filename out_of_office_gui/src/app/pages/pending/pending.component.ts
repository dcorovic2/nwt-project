import { Component, Input, OnInit } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-pending',
  templateUrl: './pending.component.html',
})
export class PendingComponent implements OnInit {
  @Input() id: any;
  public user: any = {};
  public requests: any = [];
  searchValue = '';
  visible = false;

  constructor(private api: ApiserviceService, private message1: NzMessageService) {}

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
        this.api
          .get('leaverequest/requests/employee', { employeeId: this.user.id })
          .subscribe((data) => {this.requests = data;});
      });
  }

  reset(): void {
    this.searchValue = '';
    this.search();
  }

  search(): void {
    this.visible = false;
  }

  deleteRequest(id: string): void {
    this.api.delete('leaverequest/request/' + id, {}, {}).subscribe(data => {
      this.requests = this.requests.filter((d: any) => d.id !== id);
      this.message1.create('success', `You canceled new leave request successfully!`);
    });
  }
}
