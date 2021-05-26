import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-viewrequest',
  templateUrl: './viewrequest.component.html'
})
export class ViewrequestComponent implements OnInit {
  @Input() popupData: any;
  constructor() { }
  public hide:boolean = true;
hidePopUp(): void {
    this.hide=!this.hide;
  }
  ngOnInit(): void {
    console.log(this.popupData);
  }

}
