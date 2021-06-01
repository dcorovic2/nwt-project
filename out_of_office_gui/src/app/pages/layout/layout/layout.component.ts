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
  public showDrawer: boolean = false;
  public notif: any = [];
  public numOfnotifications: any;
  public show: boolean = false;
  public data: any;
  public holidays:any = [];

  ngOnInit(): void {
    this.action.set('changeview', ()=>{this.showDrawer=false;});
    this.action.set('getNotifications', ()=>{this.getNotifications()});
    this.action.set('showDrawer', (username:any)=>this.showDraw(username))
    switch(localStorage.getItem('role')){
      case "ROLE_ADMIN": this.role = 'admin'; break;
      case "ROLE_CLIENT": this.role = 'employee'; break;
    }
    this.getEmployeeData(localStorage.getItem('username'),false);
     this.getNotifications();
  }
  event(event:any){
    this.show = event;
  }

  getEmployeeData(username:any,indicator:any){
    this.holidays = [];
    this.api.get('employee/employee/username', {username: username}).subscribe((data:any)=>{
      this.data = data;
      if (indicator) this.api.get('holiday/getlistofholidays', {}, {}).subscribe((dataa: any) =>{
        for(let i = 0; i < dataa.length; i++) {
          let emploteesList = dataa[i].employees;
          for(let j = 0; j < emploteesList.length; j++) {
            if(emploteesList[j].id == this.data.id) {
              this.holidays.push({title: dataa[i].holidayType.displayName, type: dataa[i].holidayType.code});
            }
          }
        }
      })
      indicator?this.showDrawer = !this.showDrawer: Object.assign(this.user, {firstnameLastName:data.firstnameLastName, email: data.email, id:data.id});
      console.log(this.showDrawer);
      })
  }

  showDraw(username:any){
    this.getEmployeeData(username,true);
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
        this.action.setNotif(this.notif);
      });
    },3000)
  }

}
