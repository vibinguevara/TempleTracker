import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TempleListService {

  private apiUrl = 'http://localhost:8080/api/temples/';

  constructor(private http: HttpClient) {}

  getPaginatedTemples(page: number, size: number): Observable<any> {
    return this.http.get(`${this.apiUrl}paginated?page=${page}&size=${size}`);
  }

  getTempleById(id: number): Observable<any> {
    return this.http.get<any>(`http://localhost:8080/api/temples/${id}`);
  }

  addTemple(temple: any): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/temples`, temple);
  }

  updateTemple(id: number, temple: any): Observable<any> {
    return this.http.put<any>(`http://localhost:8080/api/temples/${id}`, temple);
  }

  searchTemples(keyword: string, page: number, size: number): Observable<any> {
  return this.http.get(`${this.apiUrl}search?keyword=${keyword}&page=${page}&size=${size}`);
}

}
