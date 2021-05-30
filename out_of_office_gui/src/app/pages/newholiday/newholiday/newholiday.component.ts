import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { ActionService } from 'src/app/shared/services/action.service';
import { HttpParams } from '@angular/common/http';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-newholiday',
  templateUrl: './newholiday.component.html',
})
export class NewholidayComponent implements OnInit {
  isLoadingOne = false;

  loadOne(): void {
    this.isLoadingOne = true;
    setTimeout(() => {
      this.isLoadingOne = false;
    }, 5000);
  }

  resetinputs(): void {
    (<HTMLInputElement>document.getElementById('code')).value = '';
    (<HTMLInputElement>document.getElementById('displayname')).value = '';
    (<HTMLInputElement>document.getElementById('#')).value = ' ';
    (<HTMLInputElement>document.getElementById('#')).value = '';
  }

  @Input() user = {};
  public addemp = true;
  public loading = true;
  public employees: any;
  public employees2: any;
  public employeeId: any;
  public types: any;
  public dvaaa: any;
  selectControl: FormControl = new FormControl();
  selectControl1: FormControl = new FormControl();

  constructor(
    private route: Router,
    private api: ApiserviceService,
    private action: ActionService
  ) {}

  ngOnInit(): void {
    this.api.get('holiday/listHolidayTypes').subscribe((data)=> {this.types = data; this.dvaaa=data;});
  }

  submitForm(): void { 
    let date1 = (<HTMLInputElement>document.getElementById('date1')).value;
    let date2 = (<HTMLInputElement>document.getElementById('date2')).value;
    console.log(date1);
    console.log(date2);

    if(this.selectControl1.value == "not for all") {
      this.api.post('holiday/holiday/' + this.selectControl.value, {}, {"endDate": date2, "startDate": date1}).subscribe(data=>{
        
      });
    }
    if(this.selectControl1.value == "for all") {
      this.api.post('holiday/defaultHoliday/' + this.selectControl.value, {}, {"endDate": date2, "startDate": date1}).subscribe(data=>{
        
      });
    }
  }
}
