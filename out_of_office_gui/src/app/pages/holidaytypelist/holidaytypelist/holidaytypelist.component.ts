import { Component, OnInit, Input } from '@angular/core';
import { ApiserviceService } from 'src/app/shared/services/apiservice.service';

interface ItemData {
  id: any;
  code: string;
  displayName: string;
  text: number;
  type: string;
}

@Component({
  selector: 'app-holidaytypelist',
  templateUrl: './holidaytypelist.component.html',
})
export class HolidaytypelistComponent implements OnInit {
  @Input() user = {};
  public loading = true;
  editCache: { [key: string]: { edit: boolean; data: ItemData } } = {};
  listOfData: ItemData[] = [];

  constructor(private api: ApiserviceService) {}

  startEdit(id: string): void {
    this.editCache[id].edit = true;
  }

  cancelEdit(id: string): void {
    const index = this.listOfData.findIndex((item) => item.id === id);
    this.editCache[id] = {
      data: { ...this.listOfData[index] },
      edit: false,
    };
  }

  deleteHoliType(id: string): void {
    const index = this.listOfData.findIndex((item) => item.id === id);
    let tmp = this.editCache[id].data;

    this.api
      .delete('holiday/holidayType/' + tmp.id, {}, {})
      .subscribe((data) => {
        this.listOfData = this.listOfData.filter((d) => d.id !== id);
      });
  }

  saveEdit(id: string): void {
    const index = this.listOfData.findIndex((item) => item.id === id);
    Object.assign(this.listOfData[index], this.editCache[id].data);
    let tmp = this.editCache[id].data;

    this.api
      .patch(
        'holiday/holidayType/' + tmp.id,
        {},
        {
          code: tmp.code,
          displayName: tmp.text,
          text: tmp.type,
          type: tmp.displayName,
        }
      )
      .subscribe((data) => {
        this.editCache[id].edit = false;
      });
  }

  updateEditCache(): void {
    this.listOfData.forEach((item) => {
      this.editCache[item.id] = {
        edit: false,
        data: { ...item },
      };
    });
  }

  ngOnInit(): void {
    this.api
      .get('employee/employee/username', {
        username: localStorage.getItem('username'),
      })
      .subscribe((data: any) => {
        Object.assign(this.user, {
          firstnameLastName: data.firstnameLastName,
          email: data.email,
          id: data.id,
        });
      });

    this.api.get('holiday/listHolidayTypes', {}, {}).subscribe((dataa: any) => {
      this.listOfData = dataa;
      this.loading = false;
      console.log(dataa);
      this.updateEditCache();
    });
  }
}
