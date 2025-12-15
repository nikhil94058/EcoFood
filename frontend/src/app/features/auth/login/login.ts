import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // ✅ MUST IMPORT
import { AuthService } from '../../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule], // ✅ include FormsModule here
  templateUrl: './login.html', // make sure filename matches
  styleUrls: ['./login.css']
})
export class Login {
  email = '';
  password = '';

  constructor(private authService: AuthService) {}

  onLogin() {
    this.authService.login(this.email, this.password);
  }
}
