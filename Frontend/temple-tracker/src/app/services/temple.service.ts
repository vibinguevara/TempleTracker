import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TempleService {

  private apiUrl = 'http://localhost:8080/api/temples';

  constructor(private http: HttpClient) {}

  getAllTemples(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  getTempleById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  addTemple(temple: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, temple);
  }

  updateTemple(id: number, temple: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, temple);
  }
}
