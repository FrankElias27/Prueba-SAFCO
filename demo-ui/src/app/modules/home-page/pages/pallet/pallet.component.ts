import { Component, OnInit, ViewChild } from '@angular/core';
import { DatePipe } from '@angular/common';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { PalletService } from '../../../../services/pallet.service';

@Component({
  selector: 'app-pallet',
  imports: [
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatDialogModule
  ],
  templateUrl: './pallet.component.html',
  styleUrl: './pallet.component.css'
})
export class PalletComponent implements OnInit {

  displayedColumns: string[] = ['Pallet', 'Codigo', 'Acciones'];
  dataSource = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  totalElements = 0;
  pageSize = 10;
  pageIndex = 0;

  constructor(
    private palletService: PalletService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadPallets();
  }

  loadPallets(): void {
    this.palletService.findAll(this.pageIndex, this.pageSize).subscribe({
      next: (res: any) => {

        const page = res?.data;

        if (page?.content) {
          this.dataSource.data = page.content;
        }

        console.log('📦 DataSource:', this.dataSource.data);

        this.totalElements = page?.totalElements ?? 0;
      },

      error: (err) => {
        console.error('❌ Error al cargar pallets', err);
      }
    });
  }

  onPageChange(event: any): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadPallets();
  }

  deletePallet(id: number): void {

    Swal.fire({
      title: '¿Estás seguro?',
      text: 'El pallet será desactivado',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {

      if (result.isConfirmed) {

        this.palletService.delete(id).subscribe({
          next: () => {

            Swal.fire(
              'Eliminado',
              'El pallet fue desactivado correctamente',
              'success'
            );

            this.loadPallets();
          },
          error: (err) => {
            console.error('Error al eliminar:', err);

            Swal.fire(
              'Error',
              'No se pudo eliminar el pallet',
              'error'
            );
          }
        });

      }

    });
  }
}
