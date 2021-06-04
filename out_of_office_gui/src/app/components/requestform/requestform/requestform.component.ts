import { EventEmitter, Input, Output } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-requestform',
  templateUrl: './requestform.component.html'
})
export class RequestformComponent implements OnInit {
  @Output() show = new EventEmitter<boolean>();
  @Input() id: any;
  @Input() allowance: any;
  isLoadingOne = false;
  public hide:boolean = true;
  public typeId: any;
  selectControl:FormControl = new FormControl();
  public types: any;
  public option1Error: boolean = false;
  public date1Error: boolean = false;
  public date2Error: boolean = false;
  constructor(private api: ApiserviceService, private message1: NzMessageService) { }

  hidePopUp(): void {
    this.show.emit(false);
  }
  ngOnInit(): void {
    this.api.get('leaverequest/leavetypes').subscribe((data)=> this.types = data);
  }

  createRequest(){
    this.isLoadingOne = true;
    this.option1Error = false;
    this.date1Error = false;
    this.date2Error = false;
    let num = (<HTMLInputElement>document.getElementById('num')).value;
    let start = (<HTMLInputElement>document.getElementById('start')).value;
    let comment = (<HTMLInputElement>document.getElementById('comment')).value;
    if(num == "") this.date1Error = true;
    if(num > this.allowance) this.date2Error = true;
    if(this.selectControl.value == null){
      this.option1Error = true
      this.isLoadingOne = false;
    }
    else this.api.post('leaverequest/request', {}, 
    {comment:comment, daysNum: num, employeeId: this.id, startDate: start, typeId: this.selectControl.value}).subscribe((data)=>{ 
        this.isLoadingOne = false; 
        this.show.emit(false);
        this.message1.create('success', `You submitted request successfully!`);
      });
  }

}
