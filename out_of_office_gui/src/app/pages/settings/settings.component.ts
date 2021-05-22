import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
 public settings = true;
  constructor() { }

  ngOnInit(): void {
  }

}
