import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {
  @Input() settings: any;
  @Input() user: any;

  public show:boolean = false;
  constructor() { }

  ngOnInit(): void {
  }
  
  public doSomething(): void {
    this.show = !this.show;
  }
}
