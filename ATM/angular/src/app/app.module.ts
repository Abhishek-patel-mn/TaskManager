import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';

const appRoutes = [
	{ path: '', component: HomeComponent},
	{ path: 'home', component: HomeComponent},
	{ path: 'about', component: AboutComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
	RouterModule.forRoot(appRoutes, {useHash: true})
  ],
  providers: [],
  bootstrap: [
	AppComponent
  ]
})
export class AppModule { }
