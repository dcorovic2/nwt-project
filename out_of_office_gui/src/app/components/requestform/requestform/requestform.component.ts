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
  public types: any;
  constructor(private api: ApiserviceService) { }

  hidePopUp(): void {
    this.hide=!this.hide;
  }
  ngOnInit(): void {
    this.api.get('leaverequest/leavetypes').subscribe((data)=> this.types = data);

  }

  createRequest(){
    let num = (<HTMLInputElement>document.getElementById('num')).value;
    let start = (<HTMLInputElement>document.getElementById('start')).value;
    let comment = (<HTMLInputElement>document.getElementById('comment')).value;
    let typeId = 1;
    console.log(this.selectControl.value);
    this.api.post('leaverequest/request', {}, {comment:comment, daysNum: num, employeeId: this.id, startDate: start, typeId: this.selectControl.value}).subscribe((data)=>console.log(data));

  }

}
