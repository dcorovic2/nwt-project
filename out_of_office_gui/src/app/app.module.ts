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
    PendingComponent
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
    BrowserAnimationsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },ApiserviceService,MatDatepickerModule, AuthService 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
