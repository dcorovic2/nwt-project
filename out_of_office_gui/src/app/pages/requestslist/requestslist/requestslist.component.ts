import { Component, Input, OnInit } from '@angular/core';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';

interface DataItem {
  name: string;
  type: string;
  dates: string;
}

@Component({
  selector: 'app-requestslist',
  templateUrl: './requestslist.component.html',
})
export class RequestslistComponent implements OnInit {
  @Input() id: any;
  public user: any = {};
  public requests: any = [];
  searchValue = '';
  public loading = true;
  visible = false;
  constructor(private api: ApiserviceService, private action: ActionService) {}
  public show: boolean = false;
  public popupData: any = {};

  public doSomething(): void {
    this.show = !this.show;
  }
  ngOnInit(): void {
    this.getRequests();
    this.action.set('getRequests', ()=>this.getRequests());
  }

  reset(): void {
    this.searchValue = '';
    this.search();
  }
  getRequests(){
    this.loading=true;
    this.api
    .get('leaverequest/requests', { status_id: 1 })
    .subscribe((data) => {
      this.requests = data;
      this.loading = false;
    });
  }
  search(): void {
    this.visible = false;
    //   this.listOfDisplayData = this.listOfData.filter((item: DataItem) => item.name.indexOf(this.searchValue) !== -1);
  }

  openPopup(request: any) {
    Object.assign(this.popupData, {
      type: request.leave_type.displayName,
      startDate: request.startDate,
      endDate: request.endDate,
      reason: request.leave_status.reason,
      employeeName: request.employeeName,
      employeeId: request.employeeId,
      requestId: request.id,
    });
  }
}
