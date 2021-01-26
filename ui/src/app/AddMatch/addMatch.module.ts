import { NgModule }      from '@angular/core';
import { FormsModule }      from '@angular/forms';
import { CommonModule } from '@angular/common';
import {AddMatchComponent} from "./addMatch.component";
import {AddMatchRoutingModule} from "./addMatch-routing.module";

@NgModule({
  imports:        [ CommonModule, FormsModule, AddMatchRoutingModule ],
  declarations:   [ AddMatchComponent ],
  exports:        [ AddMatchComponent ]
})

export class AddMatchModule {}
