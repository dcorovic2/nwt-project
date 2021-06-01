import { Component, Input, OnInit } from '@angular/core';
import { ActionService } from 'src/app/shared/services/action.service';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-viewrequest',
  templateUrl: './viewrequest.component.html'
})
export class ViewrequestComponent implements OnInit {
  isLoadingOne = false;
  isLoadingTwo = false;

  @Input() popupData: any;
  @Input() employeeName: any;
  constructor(private api: ApiserviceService, private action: ActionService) { }
  public hide:boolean = true;

  ngOnInit(): void {
    console.log(this.popupData);
  }

  hidePopUp(): void {
    this.isLoadingOne = true;
    let comment = (<HTMLInputElement>document.getElementById('comment')).value;
    this.api.patch('leaverequest/request/'+this.popupData.requestId, {}, {notificationsId: 3, reason: comment, statusId:3})
    .subscribe((data:any)=>
    {
      this.isLoadingOne = false;
      this.hide=!this.hide;
    });
  }
  approve(){
    this.isLoadingTwo = true;
    let comment = (<HTMLInputElement>document.getElementById('comment')).value;
    this.api.patch('leaverequest/request/'+this.popupData.requestId, {}, {notificationsId: 2, reason: comment, statusId:2})
   .subscribe((data:any)=>{
    this.action.dismissNotification(this.popupData.notificationid));
    this.isLoadingTwo = false;
    this.hide=!this.hide;
   });
   
  }

}
