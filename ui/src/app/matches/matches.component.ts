import { Component, OnInit } from '@angular/core';
import { IMatch, IMatchDate} from "../shared/interfaces";
import { DataService } from "../core/data.service";
import {SortingService} from "../core/sorting.service";

@Component({
  selector: 'app-matches',
  templateUrl: './matches.component.html',
  styleUrls: ['./matches.component.css']
})
export class MatchesComponent implements OnInit {

  constructor(private dataService: DataService, private sortingService: SortingService) { }

  title: string;
  iMatches: IMatch[];
  searchedMatches: IMatch[] = [];
  matchDay: IMatchDate = { date:0, month:0, year:0}

  ngOnInit(): void {

    this.title = "Match Information"

    this.dataService.getMatches()
      .subscribe((matches: IMatch[]) => this.iMatches = matches);
  }


  search(data: string): void {
    this.matchDay.year = Number(data.slice(0,4));
    this.matchDay.month = Number(data.slice(5,7));
    this.matchDay.date = Number(data.slice(8,10));

    for(let match of this.iMatches){
      if((match.matchDate.date === this.matchDay.date) && (match.matchDate.month === this.matchDay.month) && (match.matchDate.year === this.matchDay.year)){
            this.searchedMatches.push(match);
      }
    }
    this.iMatches = this.searchedMatches;
  }

  sort(prop: any){
    this.sortingService.sort(this.iMatches, prop);
  }

  previousMatches(): void {

  }

}
