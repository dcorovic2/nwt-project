import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-deleteemployee',
  templateUrl: './deleteemployee.component.html'
})
export class DeleteemployeeComponent implements OnInit {

  constructor() { }
  public hide:boolean = true;
  hidePopUp(): void {
      this.hide=!this.hide;
    }
  ngOnInit(): void {
  }

}
