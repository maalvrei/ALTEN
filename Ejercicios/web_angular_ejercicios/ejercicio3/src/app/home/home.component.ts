
import {Component, OnInit} from "@angular/core";
import {StorageService} from "../core/services/storage.service";
import {User} from "../core/models/user.model";
import {AuthenticationService} from "../login/shared/authentication.service";
import listaProductos from '../../assets/data/products.json';
@Component({
  selector: 'home',
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
  public user: User;
  public productos: any = listaProductos;

  constructor(
    private storageService: StorageService,
    private authenticationService: AuthenticationService
  ) { }

  //Slider settings
  slideConfig = {"slidesToShow": 1, "slidesToScroll": 1} ;

  ngOnInit() {
    this.user = this.storageService.getCurrentUser();
  }



}
