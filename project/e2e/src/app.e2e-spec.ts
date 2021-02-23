import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  // it('should display welcome message',  () => {
  //    page.navigateTo();
  //   // expect(await page.getTitleText()).toEqual('placeApp app is running!');
  // });

  it('should check for header content', ()=>{
     page.navigateTo();
    expect(page.isHeaderAvailable()).toBeTruthy();
  })
  it('should check for home element in header', ()=>{
     page.navigateTo();
    expect(page.isHomeElementAvailable()).toBeTruthy();
  })

  it('should check for login element in header', ()=>{
     page.navigateTo();
    expect(page.isLoginButtonAvailable()).toBeTruthy();
  })

  it('should navigate to login page on login button click', ()=>{
    let btn = page.getLoginElement();
    btn.click();
    browser.sleep(5000);
    expect(page.getMycurrenturl()).toContain('/login');
    browser.sleep(5000);

  })

  it('should check for login form',()=>{
    // page.navigateTo();
    expect(page.isLoginFormAvailableInLoginpage()).toBeTruthy();
  });
  it('Login page should have login button' , ()=>{
    expect(page.isLoginPresent()).toBeTruthy();   
  });

  it('initial textbox values should be empty',()=>{
    let intialobj=["",""];
    expect(page.getdefaultValue()).toEqual(intialobj);
    browser.sleep(1000); 
  }); 
  
  it('given valid credentials should navigate to home',()=>{
    page.setLoginCredential();
    browser.sleep(1000);
    let btn=page.getLoginbutton();
    btn.click();
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/home');
    browser.sleep(2000);
  });


  it('should check for FindJob element in header', ()=>{
     page.navigateTo();
    expect(page.isHomeElementAvailable()).toBeTruthy();
  })

  it('should navigate to find jobs page on find job button click', ()=>{
     page.navigateTo();
    let btn = page.getFindJobElement();
    btn.click();
    //browser.sleep(10000);
    expect(page.getMycurrenturl()).toContain('/search');
    browser.sleep(10000);

  })

  it('should check for Search element in FindJob page', ()=>{
    expect(page.isSearchButtonAvailable()).toBeTruthy();
  })



  


  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
