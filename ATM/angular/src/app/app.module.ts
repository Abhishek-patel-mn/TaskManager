import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { UserComponent } from './user/user.component';
import { RoomRentComponent } from './room-rent/room-rent.component';
import { DataServiceService } from './shared-service/data-service.service';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TableModule} from 'primeng/table';

const appRoutes = [
	{ path: '', component: HomeComponent},
	{ path: 'home', component: HomeComponent},
	{ path: 'about', component: AboutComponent},
	{ path: 'users', component: UserComponent},
	{ path: 'room-rent', component: RoomRentComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    UserComponent,
    RoomRentComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
	HttpClientModule,
	TableModule,
	RouterModule.forRoot(appRoutes, {useHash: true})
  ],
  providers: [DataServiceService],
  bootstrap: [
	AppComponent
  ]
})
export class AppModule { }
