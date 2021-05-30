import { Component, Input, OnInit, Output } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-listnotification',
  templateUrl: './listnotification.component.html',
})
export class ListnotificationComponent implements OnInit {
  @Input() notif : any = [];
  public text: any;

  constructor(private api: ApiserviceService) { }

  ngOnInit(): void {
  }
  public showN:boolean = false;

  showNoti(text:any): void{
    this.text = text;
    this.showN = !this.showN;
  }

}
