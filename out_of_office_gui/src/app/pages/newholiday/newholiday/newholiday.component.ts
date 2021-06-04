import { Component, OnInit, Input } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { FormControl } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-newholiday',
  templateUrl: './newholiday.component.html',
})
export class NewholidayComponent implements OnInit {
  isLoadingOne = false;
  @Input() user = {};
  public addemp = true;
  public loading = true;
  public employees: any;
  public employees2: any;
  public employeeId: any;
  public types: any;
  public dvaaa: any;
  public date1Error: boolean = false;
  public option1Error: boolean = false;
  public option2Error: boolean = false;
  public newholii: boolean = false;
  selectControl: FormControl = new FormControl();
  selectControl1: FormControl = new FormControl();
  validateForm: FormGroup;

  constructor(
    private api: ApiserviceService,
    private fb: FormBuilder
  ) {
    this.validateForm = this.fb.group({
      date1: ['', [Validators.required]],
      date2: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.api.get('holiday/listHolidayTypes').subscribe((data)=> {this.types = data; this.dvaaa=data;});
  }

  submitForm(): void { 
    this.date1Error = false;
    this.option1Error = false;
    this.option2Error = false;
    this.newholii = false;

    let date1 = (<HTMLInputElement>document.getElementById('date1')).value;
    let date2 = (<HTMLInputElement>document.getElementById('date2')).value;

    let newDate = new Date(date1);
    let newDate1 = new Date(date2);

    if(newDate1 < newDate || JSON.stringify(newDate) == "null" || JSON.stringify(newDate1) == "null") this.date1Error = true;
    if(this.selectControl.value == null) this.option1Error = true;
    if(this.selectControl1.value == null) this.option2Error = true;
    else {
      if(this.selectControl1.value == "not for all") {
        this.api.post('holiday/holiday/' + this.selectControl.value, {}, {"endDate": date2, "startDate": date1}).subscribe(data=>{this.newholii = true});
      }
      if(this.selectControl1.value == "for all") {
        this.api.post('holiday/defaultHoliday/' + this.selectControl.value, {}, {"endDate": date2, "startDate": date1}).subscribe(data=>{this.newholii = true});
      }
    }
  }

  
  loadOne(): void {
    this.isLoadingOne = true;
    setTimeout(() => { this.isLoadingOne = false}, 5000);
  }

  resetinputs(): void {
    (<HTMLInputElement>document.getElementById('date1')).value = '';
    (<HTMLInputElement>document.getElementById('date2')).value = '';
    this.date1Error = false;
    this.option1Error = false;
    this.option2Error = false;
    this.newholii = false;
  }
}
