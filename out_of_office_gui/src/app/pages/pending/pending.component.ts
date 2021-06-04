import { Component, Input, OnInit } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ActionService } from 'src/app/shared/services/action.service';

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
  public loading : boolean = true;

  constructor(private api: ApiserviceService, private message1: NzMessageService, private action: ActionService) {}

  ngOnInit(): void {
      this.api.get('leaverequest/requests/employee', { employeeId: this.action.getId()}).subscribe((data) => {this.requests = data; this.loading = false;});
  }

  reset(): void {
    this.searchValue = '';
    this.search();
  }

  search(): void {
    this.visible = false;
  }

  deleteRequest(id: string): void {
    this.loading = true;
    this.api.delete('leaverequest/request/' + id, {}, {}).subscribe(data => {
      this.requests = this.requests.filter((d: any) => d.id !== id);
      this.loading = false;
      this.message1.create('success', `You canceled new leave request successfully!`);
    });
  }
}
