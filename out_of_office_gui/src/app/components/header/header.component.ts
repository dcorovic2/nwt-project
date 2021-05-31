import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
  @Input() settings: any;
  @Input() user: any;
  @Input()  notif = [];
  @Input()  numOfnotifications: any; 
  @Output() show = new EventEmitter<boolean>();
  public display = false;
  constructor(private api: ApiserviceService) { }

  ngOnInit(): void {
  }
  
  public doSomething(event:any): void {
    this.display = !this.display;
    this.show.emit(this.display);
  }
}
