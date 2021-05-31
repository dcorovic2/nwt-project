import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-notificationpopup',
  templateUrl: './notificationpopup.component.html'
})
export class NotificationpopupComponent implements OnInit {
  @Input() text: any;
  @Input()status: any;
  @Input() admin: any;
  constructor() { }

  public hide:boolean = true;
  ngOnInit(): void {
  }

  hidePopUp(): void {
    this.hide=false;
  }
}