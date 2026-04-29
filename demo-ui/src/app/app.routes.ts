import { Routes } from '@angular/router';
import { authGuard } from './utils/guard/auth.guard';

export const routes: Routes = [

    {
    path: '',
    redirectTo:'home',
    pathMatch:'full'
  },
  {
    path: 'home',
    loadChildren:() => import('./modules/home-page/home-page.module').then(m => m.HomePageModule),
    canActivate:[authGuard],

  },

];
