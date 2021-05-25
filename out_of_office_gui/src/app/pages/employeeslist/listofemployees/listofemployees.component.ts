import { Component, OnInit } from '@angular/core';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { Router } from '@angular/router';

interface DataItem {
  name: string;
  department: string;
}

@Component({
  selector: 'app-listofemployees',
  templateUrl: './listofemployees.component.html',
  styles: [
    `
      .search-box {
        padding: 8px;
      }

      .search-box input {
        width: 188px;
        margin-bottom: 8px;
        display: block;
      }

      .search-box button {
        width: 90px;
      }

      .search-button {
        margin-right: 8px;
      }
    `
  ]
})

export class ListofemployeesComponent implements OnInit {

  public hideDelete = true; 

  constructor(private route:Router) { }

  fullPage(): void {
    this.route.navigate(['employeeview']);
  }


  ngOnInit(): void {
  }

  searchValue = '';
  visible = false;
  listOfData: DataItem[] = [
    {
      name: 'John Brown',
      department: 'IT',
      
    },
    {
      name: 'Jim Green',
      department: 'Testing',
      
    },
    {
      name: 'Joe Black',
      department: 'HR',
     
    },
    {
      name: 'Jim Red',
      department: 'iOS',
     
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
