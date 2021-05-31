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

  constructor(private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {
  }


  showNoti(text:any): void{
    this.text = text;
    localStorage.getItem('role')=='ROLE_CLIENT'?this.showN = !this.showN: this.showR=!this.showR;
  }
  dismiss(id:any){
    this.id = id;
    this.dismis = true;
    this.api.patch('notification/notifications/'+id, {dismiss: 1}). subscribe((data)=>{
      this.action.getNotifications();
    })
  }

}
