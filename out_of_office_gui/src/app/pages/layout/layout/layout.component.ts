import { Component, OnInit } from '@angular/core';
import { ActionService } from 'src/app/shared/services/action.service';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  public user  = {id:"", firstnameLastName:"", email:""};
  constructor(private api: ApiserviceService, private action: ActionService) { }
  public role: any;
  public notif: any = [];
  public numOfnotifications: any;
  public show: boolean = false;

  ngOnInit(): void {
    this.action.set('getNotifications', ()=>{this.getNotifications()});
    console.log(this.action);
    switch(localStorage.getItem('role')){
      case "ROLE_ADMIN": this.role = 'admin'; break;
      case "ROLE_CLIENT": this.role = 'employee'; break;
    }
    this.api.get('employee/employee/username', {username: localStorage.getItem('username')}).subscribe((data:any)=>{
      Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id:data.id});})
     this.getNotifications();
  }
  event(event:any){
    this.show = event;
  }
  public getNotifications(){
    setTimeout(()=>{
      this.api.get('notification/notifications/'+this.user.id).subscribe((data:any)=>{
        this.notif = data.map((notification:any)=>{
          return localStorage.getItem('role')=='ROLE_CLIENT'?{displayName: notification.notifications_type.displayName,
                  employee: notification.employees[0].firstNameLastName,
                  text: notification.text,
                  id: notification.id,
                  admin: notification.employeeName
  
          } :{
            displayName: notification.requestType,
            employee: notification.employeeName,
            id: notification.id,
            requestId: notification.requestId

          }
        })
        this.numOfnotifications = this.notif.length;
      });
    },3000)
  }

}
