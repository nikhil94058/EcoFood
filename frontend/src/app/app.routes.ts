import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { Layout } from './core/layout/layout';
import { Login } from './features/auth/login/login';
import { Signup} from './features/auth/signup/signup';
export const routes: Routes = [
  {
    path: '',
    component: Layout,
    children: [
      { path: 'login', component: Login }, // login inside layout
      { path: 'signup', component: Signup},
      // other child routes
    ],
  },
  // optional: redirect empty path to home
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
