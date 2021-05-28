import { Component, OnInit, Input } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

interface ItemData {
  id: any;
  holidayType: any;
  startDate: string;
  endDate: string;
}

@Component({
  selector: 'app-createholiday',
  templateUrl: './createholiday.component.html'
})
export class CreateholidayComponent implements OnInit {
  @Input() user = {};
  
  editCache: { [key: string]: { edit: boolean; data: ItemData } } = {};
  listOfData: ItemData[] = [];

  constructor(private api: ApiserviceService){
  }

  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: string): void {
    const index = this.listOfData.findIndex(item => item.id === id);
    this.editCache[id] = {
      data: { ...this.listOfData[index] },
      edit: false
    };
  }

  deleteHoliType(id: string): void {
    const index = this.listOfData.findIndex(item => item.id === id);
    let tmp = this.editCache[id].data;

    this.api.delete('holiday/holiday/' + tmp.id, {}, {}).subscribe(data=>{
      this.listOfData = this.listOfData.filter(d => d.id !== id);
    });
  }

  saveEdit(id: string): void {
    const index = this.listOfData.findIndex(item => item.id === id);
    Object.assign(this.listOfData[index], this.editCache[id].data);
    let tmp = this.editCache[id].data;

    this.api.patch('holiday/holiday/' + tmp.id, {}, {"startDate": tmp.startDate, "endDate": tmp.endDate}).subscribe(data=>{
      this.editCache[id].edit = false;
    });
  }

  updateEditCache(): void {
    this.listOfData.forEach(item => {
      this.editCache[item.id] = {
        edit: false,
        data: { ...item }
      };
      console.log(item.holidayType.displayName);
    });
  }

  ngOnInit(): void {
    this.api.get('holiday/getlistofholidays', {}, {}).subscribe((dataa: any) =>{
      this.listOfData = dataa;
      console.log(dataa);
      this.updateEditCache();
    });
  }
}
