import { Component, OnInit } from '@angular/core';
import {IFootball, IMatch} from "../shared/interfaces";
import {DataService} from "../core/data.service";

@Component({
  selector: 'app-addMatch',
  templateUrl: './addMatch.component.html',
  styleUrls: ['./addMatch.component.css']
})

export class AddMatchComponent implements OnInit {

  randomMatch: IMatch;
  isMatchGenerated: boolean = false;

  constructor(private dataService: DataService) {}

  ngOnInit(): void {}

  random(){
    this.dataService.getRandomMatch()
      .subscribe((match: IMatch) => this.randomMatch = match);
      this.isMatchGenerated = true;
      console.log(this.randomMatch);
  }
}
