import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Country } from '../common/country';
import { State } from '../common/state';

@Injectable({
  providedIn: 'root',
})
export class Luv2ShopFormService {
  private countriesUrl = 'https://localhost:8443/api/countries';
  private statesUrl = 'https://localhost:8443/api/states';

  constructor(private httpClient: HttpClient) {}

  getCountries(): Observable<Country[]> {
    const countries = this.httpClient
      .get<GetResponseCountries>(this.countriesUrl)
      .pipe(map((response) => response._embedded.countries));

    return countries;
  }

  getStates(countryCode): Observable<State[]> {
    const states = this.httpClient
      .get<GetResponseStates>(
        `${this.statesUrl}/search/findByCountryCode?code=${countryCode}`
      )
      .pipe(map((response) => response._embedded.states));

    return states;
  }

  getCreditCardMonths(startMonth: number = 1): Observable<number[]> {
    let data: number[] = [];

    for (let month = startMonth; month <= 12; month++) {
      data.push(month);
    }
    return of(data);
  }
  getCreditCardYears(): Observable<number[]> {
    let data: number[] = [];
    const currentYear = new Date().getFullYear();

    for (let year = currentYear; year <= currentYear + 10; year++) {
      data.push(year);
    }
    return of(data);
  }
}

interface GetResponseCountries {
  _embedded: {
    countries: Country[];
  };
}

interface GetResponseStates {
  _embedded: {
    states: State[];
  };
}
