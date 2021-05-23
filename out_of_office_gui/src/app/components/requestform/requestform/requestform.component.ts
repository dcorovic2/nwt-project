import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-requestform',
  templateUrl: './requestform.component.html'
})
export class RequestformComponent implements OnInit {
  constructor() { }
  public hide:boolean = true;
hidePopUp(): void {
    this.hide=!this.hide;
  }
  ngOnInit(): void {
  }

}
