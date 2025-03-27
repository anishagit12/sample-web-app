import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit, OnDestroy {

  constructor() { }

  ngOnInit(): void {
      document.body.classList.add('home-background');
  }

  ngOnDestroy(): void {
      document.body.classList.remove('home-background');
  }

}