import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private BASE_URL = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getFoods() {
    return this.http.get(`${this.BASE_URL}/foods`);
  }
}
