import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { KeycloakService } from '../../../../utils/services/keycloak.service';
import { FooterComponent } from '../../components/footer/footer.component';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { SidebarComponent } from '../../components/sidebar/sidebar.component';

@Component({
  selector: 'app-main',
  imports: [
    RouterOutlet,
    NavbarComponent,
    FooterComponent,
    CommonModule,
    SidebarComponent
],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent implements OnInit {

  isLoggedIn = false;
  user: any;
  hasAdminSystemRole = false;
  hasUserRole = false;

  constructor(private keycloakService: KeycloakService) {}

  async ngOnInit() {
    this.InitKeycloak();
  }


  async InitKeycloak() {
      this.isLoggedIn = this.keycloakService.isTokenValid;

      if (this.isLoggedIn) {
        const token = this.keycloakService.keycloak.tokenParsed;

        const realmRoles: string[] = token?.['realm_access']?.roles || [];

        this.user = {
          nombre: token?.['given_name'],
          apellidoPaterno: token?.['family_name'],
          email: token?.['email'],
          roles: realmRoles
        };

        this.hasAdminSystemRole =
          this.user.roles.includes('ADMIN-SYSTEM') && this.user.roles.includes('USER');

        this.hasUserRole =
         this.user.roles.includes('USER') && !this.user.roles.includes('ADMIN-SYSTEM');


      }
    }

}
