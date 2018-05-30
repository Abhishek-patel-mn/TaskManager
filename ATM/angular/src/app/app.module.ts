import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router'

import { AppComponent } from './app.component';

const appRoutes = [
	
]

@NgModule({
  declarations: [
    AppComponent
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
