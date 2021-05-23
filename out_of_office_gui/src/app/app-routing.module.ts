import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginformComponent } from './pages/loginform/loginform.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { PendingComponent } from './pages/pending/pending.component';
import { AddemployeeComponent } from './pages/addemployee/addemployee/addemployee.component';
import { ListofemployeesComponent } from './pages/employeeslist/listofemployees/listofemployees.component';
import { RequestslistComponent } from './pages/requestslist/requestslist/requestslist.component';
import { FullemployeeviewComponent } from './pages/fullemployeeview/fullemployeeview/fullemployeeview.component';

const routes: Routes = [
  {
    path: '',
    component: LoginformComponent,
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    pathMatch: 'full',
  },
  {
    path: 'settings',
    component: SettingsComponent,
    pathMatch: 'full',

  },
  {
    path: 'pending',
    component: PendingComponent,
    pathMatch: 'full'
  },
  {
    path: 'addemployee',
    component: AddemployeeComponent,
    pathMatch: 'full'
  },
  {
    path: 'listofemployees',
    component: ListofemployeesComponent,
    pathMatch: 'full',
  },
  {
    path: 'listofrequests',
    component: RequestslistComponent,
    pathMatch: 'full'
  },
  {
    path: 'employeeview',
    component: FullemployeeviewComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
