import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './header.html',
  styleUrls: ['./header.css'],
})
export class Header {
  menuOpen = false;
  profileOpen = false;

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  toggleProfile() {
    this.profileOpen = !this.profileOpen;
  }
}
