import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.html',
  styleUrls: ['./signup.css']
})
export class Signup {
  name = '';
  email = '';
  password = '';
  address = '';
  phoneNumber = '';
  selectedFile: File | null = null;
  imagePreview: string | ArrayBuffer | null = null;

  constructor(private authService: AuthService, private router: Router) {}

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      this.selectedFile = input.files[0];

      const reader = new FileReader();
      reader.onload = e => this.imagePreview = reader.result;
      reader.readAsDataURL(this.selectedFile);
    }
  }

  onSignup() {
    const formData = new FormData();
    formData.append('name', this.name);
    formData.append('email', this.email);
    formData.append('password', this.password);
    formData.append('address', this.address);
    formData.append('phoneNumber', this.phoneNumber);
    if (this.selectedFile) {
      formData.append('profileImage', this.selectedFile);
    }

    this.authService.signup(formData).subscribe({
      next: res => {
        console.log('Signup successful:', res);
        this.router.navigate(['/login']);
      },
      error: err => console.error('Signup error:', err)
    });
  }
}
