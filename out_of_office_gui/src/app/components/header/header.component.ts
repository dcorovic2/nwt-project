import { Component, Input, OnInit } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
  @Input() settings: any;
  @Input() user: any;
  public notif = [];
  public numOfnotifications: any; 

  public show:boolean = false;
  constructor(private api: ApiserviceService) { }

  ngOnInit(): void {
    setTimeout(()=>{
      this.api.get('notification/notifications/'+this.user.id).subscribe((data:any)=>{
        this.notif = data.map((notification:any)=>{
          return {displayName: notification.notifications_type.displayName,
                  employee: notification.employees[0].firstNameLastName,
                  text: notification.text
  
          }
        })
        this.numOfnotifications = this.notif.length;
      });
    },1000)
  }
  
  public doSomething(): void {
    this.show = !this.show;
  }
}
