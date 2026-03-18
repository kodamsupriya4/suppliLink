import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Day-22+ AuthService
 * You already have login, createUser, getToken...
 * We add helpers for role & userId used by Day-23 UI.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly base = typeof window !== 'undefined' && window.location
    ? window.location.origin
    : '';

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

  // Day 23 helpers
  getRole(): string | null {
    return localStorage.getItem('role');
  }
  setRole(role: string): void {
    localStorage.setItem('role', role);
  }
  getUserId(): number | null {
    const v = localStorage.getItem('userId');
    return v ? Number(v) : null;
  }
  setUserId(id: number): void {
    localStorage.setItem('userId', String(id));
  }
  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    localStorage.removeItem('userId');
  }
} 