import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TempleEventService {
  private apiUrl = 'http://localhost:8080/api/temple-events';

  constructor(private http: HttpClient) {}

  createEvent(eventData: any): Observable<any> {
    return this.http.post(this.apiUrl, eventData);
  }

  getTodayEvents(): Observable<any[]> {
    const today = new Date();
    const yyyy = today.getFullYear();
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    const dd = String(today.getDate()).padStart(2, '0');
    // Assuming the backend supports filtering by date via query param
    return this.http.get<any[]>(`${this.apiUrl}?date=${yyyy}-${mm}-${dd}`);
  }
}