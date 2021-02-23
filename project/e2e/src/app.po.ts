import { browser, by, element, ElementFinder, promise } from 'protractor';

export class AppPage {
  async navigateTo(): Promise<unknown> {
    return browser.get('/') as Promise<unknown>;
  }

  // async getTitleText(): Promise<string> {
  //   return element(by.css('app-root .content span')).getText();
  // }
  getMycurrenturl() {
    return browser.getCurrentUrl();
  }


  toHeader(): ElementFinder {
    return element(by.tagName('<app-header>'));
  }

  isHeaderAvailable(): promise.Promise<boolean>{
    return this.toHeader().isPresent();
  }

  getHomeElement() : ElementFinder{
    return element (by.className('nav-item home'));
  } 
  isHomeElementAvailable() : promise.Promise<boolean>{
    return this.getHomeElement().isPresent();
  }

  getFindJobElement() : ElementFinder{
    return element (by.className('nav-link findJob'));
  } 
  isFindJobElementAvailable() : promise.Promise<boolean>{
    return this.getFindJobElement().isPresent();
  }

  getSearchBtninFindJobPage() :ElementFinder{
    return element (by.className('btn btn-primary searchBtn'));
  } 
  isSearchButtonAvailable() : promise.Promise<boolean>{
    return this.getSearchBtninFindJobPage().isPresent();
  }

  getLoginElement() : ElementFinder{
    return element (by.css('#loginApp'));
  }
  isLoginButtonAvailable() : promise.Promise<boolean>{
    return this.getLoginElement().isPresent();
  }

  getLoginFormFromLognPage() : ElementFinder{
    return element (by.className('example-form'));
  }
  isLoginFormAvailableInLoginpage() : promise.Promise<boolean>{
    return this.getLoginFormFromLognPage().isPresent();
  }


  getEmailObject() : ElementFinder{
    return element(by.name("email"));
  }
  getPasswordObject() : ElementFinder{
    return element(by.name("pass"));
  }
  getLoginbutton() : ElementFinder{
    return element(by.className("btnlogin"));
  }
  isLoginPresent() : promise.Promise<boolean>{
    return this.getLoginbutton().isPresent();
  }
  getdefaultValue() : any{  
    let uname=this.getEmailObject().getAttribute('value');
    let pass=this.getPasswordObject().getAttribute('value');

    return Promise.all( [uname,pass]).then ( (res) => {return res;});
  }
  setLoginCredential(){
    this.getEmailObject().sendKeys("yo@yo.com");
    this.getPasswordObject().sendKeys("123456");
  }



}
