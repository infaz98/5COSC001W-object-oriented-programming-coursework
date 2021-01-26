import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { AppRoutingModule} from "./app-routing.module";

import { AppComponent } from './app.component';
import { FootballModule } from './football/football.module';
import { CoreModule } from './core/core.module';

import {MatchesModule} from "./matches/matches.module";
import {AddMatchModule} from "./AddMatch/addMatch.module";

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'Csrf-Token',
      headerName: 'Csrf-Token',
    }),
    FootballModule,
    MatchesModule,
    CoreModule,
    AddMatchModule,
    AppRoutingModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
