import { NgModule }      from '@angular/core';
import { FormsModule }      from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FootballComponent } from './football.component';
import { FootballRoutingModule } from "./football-routing.module";

@NgModule({
    imports:        [ CommonModule, FormsModule, FootballRoutingModule ],
    declarations:   [ FootballComponent ],
    exports: [FootballComponent]
})

export class FootballModule {}
