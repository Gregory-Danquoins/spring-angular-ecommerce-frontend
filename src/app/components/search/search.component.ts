import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css',
})
export class SearchComponent {
  constructor(private router: Router, private productService: ProductService) {}

  doSearch(search: string) {
    this.router.navigateByUrl(`/search/${search}`);
  }
}
