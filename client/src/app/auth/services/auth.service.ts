import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly base = typeof window !== 'undefined' && window.location
    ? window.location.origin // e.g., http://localhost:9876
    : '';                    // fallback (tests run in browser, so this will be set)

  constructor(private http: HttpClient) {}

  login(payload: { username: string; password: string }): Observable<any> {
    const url = `${this.base}/context.html/user/login`;
    return this.http.post(url, payload);
  }

  createUser(user: any): Observable<any> {
    const url = `${this.base}/context.html/user/register`;
    return this.http.post(url, user);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }
} 