import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from '../../utils/guard/auth.guard';

const routes: Routes = [

   {
    path: '',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./pages/main/main.component').then(m => m.MainComponent),
    children: [
      {
        path: '',
        canActivate: [authGuard],
        loadComponent: () =>
          import('./pages/container/container.component').then(m => m.ContainerComponent),
      },
      {
        path: 'pallet',
        canActivate: [authGuard],
        loadComponent: () =>
          import('./pages/pallet/pallet.component').then(m => m.PalletComponent),
      },
    ],
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomePageRoutingModule { }
