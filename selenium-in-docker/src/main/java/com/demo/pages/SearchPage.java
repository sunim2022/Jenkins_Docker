package com.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.demo.pages.common.BasePage;

public class SearchPage extends BasePage {

	@FindBy(css = "a >button.btn")
	private WebElement getStartedBtn;

	//TODO: not very efficient...
	@FindBy(xpath = "//div[2]/div/span")
	private WebElement selectSearchText;

	@FindBy(linkText = "No thanks")
	private WebElement signInIgnoreButton;

	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchTextInput;

	public SearchPage(WebDriver argDriver) {
		super(argDriver);
	}

	public SearchResults enterText(String searchText) {
		this.searchTextInput.sendKeys(searchText);
		this.searchTextInput.submit();

		return new SearchResults(getDriver());
	}

	public void ignoreSignIn()  {
		
		this.getDriver().switchTo().activeElement();
		signInIgnoreButton.click();
	}

	public SearchResults getStarted() {

		selectSearchText.click();

		return new SearchResults(getDriver());
	}

}
