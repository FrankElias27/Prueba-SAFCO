import { CommonModule } from '@angular/common';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-ubicaciones',
  imports: [
    CommonModule
  ],
  templateUrl: './ubicaciones.component.html',
  styleUrl: './ubicaciones.component.css'
})
export class UbicacionesComponent implements OnInit {

  asientos: any[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any[]
  ) { console.log(data)}

  ngOnInit(): void {
    this.generarAsientos();
  }

  generarAsientos() {
    const ocupados = this.data || [];

    this.asientos = Array.from({ length: 24 }, (_, i) => {
      const numero = i + 1;

      const encontrado = ocupados.find(
        (u: any) => u.numeroAsiento === numero
      );

      return {
        numero,
        ocupado: !!encontrado,
        data: encontrado || null
      };
    });
  }

  getAsientoClass(asiento: any): string {
    if (asiento.ocupado) {
      return 'ocupado';
    }
    return 'libre';
  }
}
