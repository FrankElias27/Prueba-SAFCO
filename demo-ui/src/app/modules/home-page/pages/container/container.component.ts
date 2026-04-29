import { Component, OnInit, ViewChild } from '@angular/core';
import { DatePipe, NgFor } from '@angular/common';
import { initFlowbite } from 'flowbite';
import { Observable } from 'rxjs';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { ContenedorService } from '../../../../services/contenedor.service';
import { UbicacionService } from '../../../../services/ubicacion.service';
import { UbicacionesComponent } from '../../modals/ubicaciones/ubicaciones.component';

@Component({
  selector: 'app-container',
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    DatePipe,
    MatDialogModule
  ],
  templateUrl: './container.component.html',
  styleUrl: './container.component.css'
})
export class ContainerComponent implements OnInit {
  displayedColumns: string[] = ['Contenedor', 'Codigo', 'Configuracion', 'Acciones'];
  dataSource = new MatTableDataSource<any>();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;

  constructor(private containerService: ContenedorService,
    private ubicacionService:UbicacionService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.loadEvents();
  }

  loadEvents(): void {
  this.containerService.findAll(this.pageIndex, this.pageSize).subscribe({
    next: (res: any) => {

      const page = res?.data;

      if (page?.content) {
        this.dataSource.data = page.content;
      }

      console.log('📦 DataSource:', this.dataSource.data);

      this.totalElements = page?.totalElements ?? 0;
    },

    error: (err) => {
      console.error('❌ Error al cargar contenedores', err);
    }
  });
}


  onPageChange(event: any): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadEvents();
  }

  deleteContenedor(id: number): void {

  Swal.fire({
    title: '¿Estás seguro?',
    text: 'El contenedor será desactivado',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6',
    confirmButtonText: 'Sí, eliminar',
    cancelButtonText: 'Cancelar'
  }).then((result) => {

    if (result.isConfirmed) {

      this.containerService.delete(id).subscribe({
        next: () => {

          Swal.fire(
            'Eliminado',
            'El contenedor fue desactivado correctamente',
            'success'
          );

          // recargar tabla
          this.loadEvents();
        },
        error: (err) => {
          console.error('Error al eliminar:', err);

          Swal.fire(
            'Error',
            'No se pudo eliminar el contenedor',
            'error'
          );
        }
      });

    }

  });
}

  viewUbicacionesByContenedor(contenedorId: number) {
  this.ubicacionService.findByContenedorId(contenedorId).subscribe({
    next: (res) => {

      const ubicaciones = res?.data ?? res; // por si usas ApiResponseProvider

      const dialogRef = this.dialog.open(UbicacionesComponent, {
        width: '80vw',
        height: '80vh',
        maxWidth: 'none',
        data: ubicaciones
      });

      dialogRef.afterClosed().subscribe(() => {
        // No necesitas actualizar datasource porque solo es consulta
      });

    },
    error: () => {
      Swal.fire('Error', 'No se pudieron obtener las ubicaciones del contenedor.', 'error');
    }
  });
}


}
