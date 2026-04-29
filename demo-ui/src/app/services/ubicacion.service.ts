import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UbicacionService {

  private apiUrl = 'http://localhost:8088/api/v1/ubicacion';

  constructor(private http: HttpClient) {}

  // 🔹 Crear ubicación
  create(data: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, data);
  }

  // 🔹 Actualizar ubicación
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

  // 🔹 Listar ubicaciones por contenedor
findByContenedorId(contenedorId: number): Observable<any> {
  return this.http.get<any>(
    `${this.apiUrl}/${contenedorId}/ubicaciones`
  );
}
}
