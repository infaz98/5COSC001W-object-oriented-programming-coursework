import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {MatchesRoutingModule} from "./matches-routing.module";
import {MatchesComponent} from "./matches.component";

@NgModule({
  imports: [CommonModule, FormsModule, MatchesRoutingModule],
  declarations: [MatchesComponent ],
  exports:[ MatchesComponent ]
})

export class MatchesModule {}


