import { Component, OnInit } from '@angular/core';
import { JobService } from '../Services/job.service';
import { RoutingService } from '../Services/routing.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  countries = [
    {
      name: "India",
      locations: ["Banglore", "Chennai","Ahmedabad","Pune","Latur","Mumbai"]
    },
    {
      name: "Germany",
      locations: ["Alfeld", "Dessau","Aachen","Aalen"]
    },
    {
      name: "Japan",
      locations: ["Tateyama","Tokyo","Toki","Tokushima"]
    },
    {
      name: "Russia",
      locations: ["Abakan","Abaza","Abdulino","Tokushima"]
    }
  ]
  _selectedCountry: string = '';
  _selectedLocation: string = '';
  jobSearchData: Array<any>;

  jobcategories = [

    {
      'categoryname': 'Account Management',
      'selected': false
    },
    {
      'categoryname': 'Creative & Design',
      'selected': false
    },
    {
      'categoryname': 'Data Science',
      'selected': false
    },
    {
      'categoryname': 'Education',
      'selected': false
    },
    {
      'categoryname': 'Finance',
      'selected': false
    },
    {
      'categoryname': 'Legal',
      'selected': false
    },
    {
      'categoryname': 'Operations',
      'selected': false
    },
    {
      'categoryname': 'Retail',
      'selected': false
    },
    {
      'categoryname': 'Social Media & Community',
      'selected': false
    },
    {
      'categoryname': 'Business & Strategy',
      'selected': false
    },
    {
      'categoryname': 'Customer Service',
      'selected': false
    },
    {
      'categoryname': 'Editorial',
      'selected': false
    },
    {
      'categoryname': 'Engineering',
      'selected': false
    },
    {
      'categoryname': 'Fundraising & Development',
      'selected': false
    },
    {
      'categoryname': 'HR & Recruiting',
      'selected': false
    },
    {
      'categoryname': 'Marketing & PR',
      'selected': false
    },
    {
      'categoryname': 'Project & Product Management',
      'selected': false
    },
    {
      'categoryname': 'Sales',
      'selected': false
    }
  ];

  constructor(
    private service: RoutingService,
    private jobservice: JobService,
  ) { }




  ngOnInit(): void { }

  showLogin() {
    this.service.openLogin();
  }

  showRegister() {
    this.service.openRegister();
  }

  getLocations() {
    console.log(this._selectedCountry);
  }

  get locationsForSelectedCountries() {
    if (this._selectedCountry) {
      let selectedCountry = this.countries.filter(item => item.name == this._selectedCountry);
      return selectedCountry[0].locations;
    } else return [];
  }

  selectCategory(index) {
    this.jobcategories[index].selected = !this.jobcategories[index].selected;
  }

  search() {
    console.log(this.jobcategories)
    let selectedCategories = this.jobcategories.filter((data) =>
      data.selected
    )
    if (selectedCategories.length < 1) {
      selectedCategories = null;
    }
    let locationQuery = '';
    console.log(this._selectedCountry);
    if (this._selectedCountry != '' && this._selectedLocation != '') {
      locationQuery = locationQuery + this._selectedCountry + ', ' + this._selectedLocation;
    }
    
    console.log(locationQuery);
    this.jobservice.getJobDataFromApiAndShowCategories(selectedCategories, locationQuery).subscribe(
      (response: any) => {
        this.service.openSearchResults();
        //this.jobSearchData = response.results;
        //console.log(response.page);
        
    })
  }

}
