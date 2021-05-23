import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listnotification',
  templateUrl: './listnotification.component.html',
})
export class ListnotificationComponent implements OnInit {
  
  constructor() { }

  ngOnInit(): void {
  }
  
  public showN:boolean = false;

  showNoti(): void{
    this.showN = !this.showN;
  }

}
