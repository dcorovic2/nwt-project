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

  constructor(private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {
  }
  public showN:boolean = false;

  showNoti(text:any): void{
    this.text = text;
    this.showN = !this.showN;
  }
  dismiss(id:any){
    this.id = id;
    this.dismis = true;
    this.api.patch('notification/notifications/'+id, {dismiss: 1}). subscribe((data)=>{
      this.action.getNotifications();
    })
  }

}
