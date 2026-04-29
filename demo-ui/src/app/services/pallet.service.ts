import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PalletService {

  private apiUrl = 'http://localhost:8088/api/v1/pallet';

  constructor(private http: HttpClient) {}

  // 🔹 Crear pallet
  create(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }

  // 🔹 Actualizar pallet
  update(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, data);
  }

  // 🔹 Obtener por ID
  findById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Eliminar (desactivar)
  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Listar con paginación
  findAll(page: number = 0, size: number = 10): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=${page}&size=${size}`);
  }
}
