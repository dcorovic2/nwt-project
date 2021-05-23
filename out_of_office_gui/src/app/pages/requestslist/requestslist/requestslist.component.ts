import { Component, OnInit } from '@angular/core';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';

interface DataItem {
  name: string;
  type: string;
  dates: string;
}

@Component({
  selector: 'app-requestslist',
  templateUrl: './requestslist.component.html'
})
export class RequestslistComponent implements OnInit {

  constructor() { }
  public show:boolean = false;

  public doSomething(): void {
    this.show = !this.show;
  }
  ngOnInit(): void {
  }

  searchValue = '';
  visible = false;
  listOfData: DataItem[] = [
    {
      name: 'John Brown',
      type: 'Holiday',
      dates: 'From 02.03.2021 to 06.03.2021'
      
    },
    {
      name: 'Jim Green',
      type: 'New Year',
      dates: 'From 02.03.2021 to 06.03.2021'
    },
    {
      name: 'Joe Black',
      type: 'Sick leave',
      dates: 'From 02.03.2021 to 06.03.2021'
    },
    {
      name: 'Jim Red',
      type: 'Funeral',
      dates: 'From 02.03.2021 to 06.03.2021'
    }
  ];
  listOfDisplayData = [...this.listOfData];

  reset(): void {
    this.searchValue = '';
    this.search();
  }

  search(): void {
    this.visible = false;
    this.listOfDisplayData = this.listOfData.filter((item: DataItem) => item.name.indexOf(this.searchValue) !== -1);
  }

}
