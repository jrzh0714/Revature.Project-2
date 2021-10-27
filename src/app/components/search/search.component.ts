import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AfterViewInit, ElementRef} from '@angular/core';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchterm!:string;
  constructor(private router:Router,private elementRef:ElementRef) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.querySelector('.searchbar')
                                .addEventListener('keyup', this.onEnter.bind(this));
  }

  onEnter(event:any) {
    if (event.key === "Enter") {
      window.location.href = '/search/'+this.searchterm;

    }
  }

}
