import { Component, Input, OnInit, Output } from '@angular/core';
import { ActionService } from 'src/app/shared/services/action.service';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-listnotification',
  templateUrl: './listnotification.component.html',
})
export class ListnotificationComponent implements OnInit {
  @Input() notif : any = [];
  public text: any;
  public dismis: boolean=false;
  public id : any;
  public showR: boolean = false;
  public showN:boolean = false;
  public status:any;
  public admin:any;
  public employeeName: any;
  public popupData: any;

  constructor(private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {
    this.action.set('dismissNotification',(id:any)=>this.dismiss(id))
  }


  showNoti(notification:any): void{
    this.text = notification.text;
    this.status = notification.displayName;
    this.admin = notification.admin;
    console.log(notification);
    if(notification.requestId){
      this.api.get('leaverequest/request/'+notification.requestId).subscribe((data)=>{
        this.popupData = {
          employeeName: notification.employee,
          reason: data.comment,
          type: data.leave_type.displayName,
          startDate: data.startDate,
          endDate: data.endDate,
          notificationid: notification.id,
          requestId: notification.requestId
        }
        this.showR = !this.showR;
      })
    }
    localStorage.getItem('role')=='ROLE_CLIENT'?this.showN = !this.showN: false;
  }
  dismiss(id:any){
    this.id = id;
    this.dismis = true;
    this.api.patch('notification/notifications/'+id, {dismiss: 1}). subscribe((data)=>{
      this.action.getNotifications();
    })
  }

}
