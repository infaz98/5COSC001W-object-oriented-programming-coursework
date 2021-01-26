import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { DataService } from './data.service';
import {SortingService} from "./sorting.service";

@NgModule({
    imports: [ HttpClientModule ],
  providers:  [ DataService, SortingService ]
})
export class CoreModule { }
