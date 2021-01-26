import { Component, OnInit } from '@angular/core';
import { IFootball } from '../shared/interfaces';
import { DataService } from '../core/data.service';
import {SortingService} from "../core/sorting.service";

@Component({
  selector: 'app-football',
  templateUrl: './football.component.html',
  styleUrls: ['./football.component.css']
})

export class FootballComponent implements OnInit {

  title: string;
  iFootballs: any[];

  constructor(private dataService: DataService, private sorterService: SortingService) { }

  ngOnInit(): void {
    this.title = "Football Clubs"
    this.dataService.getFootballClubs()
      .subscribe((footballClubs: IFootball[]) => this.iFootballs = footballClubs);
  }

  sort(prop: string){
    this.sorterService.sort(this.iFootballs, prop);
  }
}
