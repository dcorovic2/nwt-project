import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

@Component({
  selector: 'app-requestform',
  templateUrl: './requestform.component.html'
})
export class RequestformComponent implements OnInit {
  public hide:boolean = true;
  public typeId: any;
  selectControl:FormControl = new FormControl()
  @Input() id: any;
  @Input() allowance: any;
  public types: any;
  public option1Error: boolean = false;
  public date1Error: boolean = false;
  public date2Error: boolean = false;
  constructor(private api: ApiserviceService) { }

  hidePopUp(): void {
    this.hide=!this.hide;
  }
  ngOnInit(): void {
    this.api.get('leaverequest/leavetypes').subscribe((data)=> this.types = data);
    console.log(this.allowance);
  }

  createRequest(){
    //resetovanje validacija
    this.option1Error = false;
    this.date1Error = false;
    this.date2Error = false;
    let num = (<HTMLInputElement>document.getElementById('num')).value;
    let start = (<HTMLInputElement>document.getElementById('start')).value;
    let comment = (<HTMLInputElement>document.getElementById('comment')).value;
    
    if(num == "") {
      this.date1Error = true;
    }
    if(num > this.allowance){
      this.date2Error = true;
    }
    if(this.selectControl.value == null) {
      this.option1Error = true;
    } else {
      this.api.post('leaverequest/request', {}, {comment:comment, daysNum: num, employeeId: this.id, startDate: start, typeId: this.selectControl.value}).subscribe((data)=>console.log(data));
    }
  }

}
