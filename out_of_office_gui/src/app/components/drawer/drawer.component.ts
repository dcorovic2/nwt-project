import { Component, Input, OnInit } from '@angular/core';
import { ActionService } from 'src/app/shared/services/action.service';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.scss']
})
export class DrawerComponent implements OnInit {
  @Input() showDrawer = true;
  @Input() data: any;
  @Input() holidays: any;
  @Input() leavehistory: any;
  public loading: any;
  constructor(private api: ApiserviceService, private action: ActionService) { }

  ngOnInit(): void {}

  changeforview(){
    this.action.changeview();
  }

}
