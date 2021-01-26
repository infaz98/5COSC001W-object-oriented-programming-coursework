export interface IFootball {

    clubName: string;
    clubLocation: string;
    matchesWon: number;
    matchesLost: number;
    leaguePoints: number;
    totalLeagueGoals: number;
    goalDifference: number;
}

export interface IMatch {

  teamOne: IFootball;
  teamTwo: IFootball;
  teamOneGoals: number;
  teamTwoGoals: number;
  matchDate: IMatchDate;

}

export interface IMatchDate {

  date : number;
  month: number;
  year: number;

}

