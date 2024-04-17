import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import listaProductos from '../../assets/data/products.json';


@Component({
  selector: 'app-productsingle',
  templateUrl: './productsingle.component.html',
  styleUrls: ['./productsingle.component.scss']
})
export class ProductsingleComponent implements OnInit {

  public productos: any = listaProductos;
  public productoElegido:any;
  public quantity = 1;
  public size = "S";

  constructor( private _route: ActivatedRoute, private router: Router) {

   }

  ngOnInit(): void {
    let id = +this._route.snapshot.paramMap.get('id');

    listaProductos.forEach(element => {
      if(element.id === id){
        this.productoElegido = element;
      }
    });
  }

  public addCart(){
      if(this.size === "L" || this.quantity > 3){
        this.router.navigate(['/404']);
      }else{
        this.router.navigate(['/cart' , this.productoElegido.id, this.quantity]);
      }
  }

}
