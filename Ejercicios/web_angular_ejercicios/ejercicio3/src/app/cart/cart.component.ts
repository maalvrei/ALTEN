import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import listaProductos from '../../assets/data/products.json';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  public productos: any = listaProductos;
  public productoElegido:any;
  public quantity: number;

  constructor(private _route: ActivatedRoute) { }

  ngOnInit(): void {
    let id = +this._route.snapshot.paramMap.get('id');
    this.quantity = +this._route.snapshot.paramMap.get('quantity');

    listaProductos.forEach(element => {
      if(element.id === id){
        this.productoElegido = element;
      }
    });
  }

  precioTotal(){
    return (this.productoElegido.precioRebajado * this.quantity).toFixed(2);
  }

}
