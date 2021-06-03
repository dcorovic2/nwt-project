import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-notificationpopup',
  templateUrl: './notificationpopup.component.html'
})
export class NotificationpopupComponent implements OnInit {
  @Input() text: any;
  @Input()status: any;
  @Input() admin: any;
  @Output() show = new EventEmitter<boolean>();
  constructor() { }

  public hide:boolean = true;
  ngOnInit(): void {
  }

  hidePopUp(): void {
    this.show.emit(false);
  }
}