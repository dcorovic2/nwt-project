import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActionService } from 'src/app/shared/services/action.service';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-viewrequest',
  templateUrl: './viewrequest.component.html'
})
export class ViewrequestComponent implements OnInit {
  isLoadingOne = false;
  isLoadingTwo = false;
  @Output() show = new EventEmitter<boolean>();
  @Input() popupData: any;
  @Input() employeeName: any;
  constructor(private api: ApiserviceService, private action: ActionService) { }
  public hide:boolean = true;
  public notif : any;

  ngOnInit(): void {
    this.notif = this.action.getNotif();
  }

  hidePopUp(): void {
    this.isLoadingOne = true;
    let comment = (<HTMLInputElement>document.getElementById('comment')).value;
    this.api.patch('leaverequest/request/'+this.popupData.requestId, {}, {notificationsId: 3, reason: comment, statusId:3})
    .subscribe((data:any)=>
    {
      this.isLoadingOne = false;
      this.hide=!this.hide;
      this.show.emit(false);
      if(this.popupData.notificationid){this.action.dismissNotificationList(this.popupData.notificationid);}
      else {
       this.action.getRequests();
       this.action.getNotificationList()?this.action.dismissNotificationList(this.notif.find((notif:any)=> notif.requestId == this.popupData.requestId).id):this.action.dismissNotification(this.notif.find((notif:any)=> notif.requestId == this.popupData.requestId).id);
      }
    });
  }

  emit(){
    this.show.emit(false)
  }
  approve(){
    this.isLoadingTwo = true;
    let comment = (<HTMLInputElement>document.getElementById('comment')).value;
    this.api.patch('leaverequest/request/'+this.popupData.requestId, {}, {notificationsId: 2, reason: comment, statusId:2})
   .subscribe((data:any)=>{
     if(this.popupData.notificationid){this.action.dismissNotificationList(this.popupData.notificationid);}
     else {
      this.action.getRequests();
      this.action.getNotificationList()?this.action.dismissNotificationList(this.notif.find((notif:any)=> notif.requestId == this.popupData.requestId).id):this.action.dismissNotification(this.notif.find((notif:any)=> notif.requestId == this.popupData.requestId).id);
     }
    this.isLoadingTwo = false;
    this.hide=!this.hide;
   });

   
  }

}
