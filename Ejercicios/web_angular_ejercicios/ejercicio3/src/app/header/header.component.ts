import { Component, OnInit } from '@angular/core';
import { User } from '../core/models/user.model';
import { StorageService } from '../core/services/storage.service';
import { AuthenticationService } from '../login/shared/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public user: User;

  constructor(    public storageService: StorageService,
    private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.user = this.storageService.getCurrentUser();
  }


  public logout(): void{
    this.authenticationService.logout().subscribe(
        response => {if(response) {this.storageService.logout();}}
    );
  }
}
