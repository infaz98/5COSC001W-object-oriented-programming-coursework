import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MatchesComponent} from "./matches.component";
import {FootballComponent} from "../football/football.component";

const routes: Routes = [
  { path: 'matches/list', component: MatchesComponent }
];

@NgModule({
  imports: [ RouterModule.forChild(routes) ],
  exports: [ RouterModule ]
})
export class MatchesRoutingModule {

}
