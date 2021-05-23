import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginformComponent } from './pages/loginform/loginform.component';
import { MenuComponent } from './components/menu/menu.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AdminDashboardComponent } from './pages/dashboard/admin-dashboard/admin-dashboard.component';
import { EmployeeDashboardComponent } from './pages/dashboard/employee-dashboard/employee-dashboard.component';
import { ApiserviceService } from './shared/services/apiservice.service';
import {AuthInterceptor}from './shared/services/authinterceptor';
import {AuthService} from './shared/services/auth.service'
import { ToastrModule } from 'ngx-toastr';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { HeaderComponent } from './components/header/header.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule,MatFormFieldModule,MatInputModule} from '@angular/material';
import {DpDatePickerModule} from 'ng2-date-picker';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { SettingsComponent } from './pages/settings/settings.component';
import { PendingComponent } from './pages/pending/pending.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RequestformComponent } from './components/requestform/requestform/requestform.component';
import { ListofemployeesComponent } from './pages/employeeslist/listofemployees/listofemployees.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { fi_FI, en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import { NzIconModule } from 'ng-zorro-antd/icon';
import fi from '@angular/common/locales/fi';
import { FormsModule } from '@angular/forms';
import en from '@angular/common/locales/en';
import { DeleteemployeeComponent } from './components/deleteemployee/deleteemployee/deleteemployee.component';

registerLocaleData(fi);


@NgModule({
  declarations: [
    AppComponent,
    LoginformComponent,
    MenuComponent,
    DashboardComponent,
    AdminDashboardComponent,
    EmployeeDashboardComponent,
    HeaderComponent,
    SettingsComponent,
    PendingComponent,
    RequestformComponent,
    ListofemployeesComponent,
    DeleteemployeeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ToastrModule.forRoot({timeOut: 5000, positionClass: 'toast-bottom-right',preventDuplicates: true}),
    MatDatepickerModule,
    MatNativeDateModule ,
    MatFormFieldModule,
    MatInputModule,
    DpDatePickerModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),
    NgbModule,
    NzIconModule,
    BrowserAnimationsModule,
    FormsModule,
    NzTableModule,
    NzDropDownModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },ApiserviceService,MatDatepickerModule, AuthService, { provide: NZ_I18N, useValue: fi_FI } 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
