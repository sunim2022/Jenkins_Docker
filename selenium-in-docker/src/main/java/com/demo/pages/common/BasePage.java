package com.demo.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	private WebDriver driver;

	public BasePage(WebDriver argDriver) {
		

		this.driver = argDriver;
		PageFactory.initElements(this.driver, this);
	}
	
	public String getTitle() {
		return this.driver.getTitle();
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

}
