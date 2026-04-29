import { Component } from '@angular/core';
import { KeycloakService } from '../../../../utils/services/keycloak.service';
import { initFlowbite } from 'flowbite';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  imports: [],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css'
})
export class SidebarComponent {
  isLoggedIn = false;
  user: any;

  constructor(
    private router: Router,
    private keycloakService:KeycloakService
  ){}

  ngAfterViewInit() {
  if (this.isLoggedIn) {
    initFlowbite();
  }
}

  async ngOnInit() {
      this.InitKeycloak();
    }

  async InitKeycloak() {
      this.isLoggedIn = this.keycloakService.isTokenValid;

      if (this.isLoggedIn) {
        const token = this.keycloakService.keycloak.tokenParsed;

        this.user = {
          nombre: token?.['given_name'],
          apellidoPaterno: token?.['family_name'],
          email: token?.['email'],
        };

        this.updateSidebar();
      }
    }

  updateSidebar() {
    if (this.isLoggedIn) {
      setTimeout(() => initFlowbite(), 100);
    }
  }

  goToHome() {
  this.router.navigate(['/home']);
}

  goToPallet() {
  this.router.navigate(['/home/pallet']);
}




}
