package pages;

import dto.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.enums.Fuel;

import java.io.File;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory
                (driver, 10), this);
    }

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//input[@id='make']")
    WebElement manufacture;
    @FindBy(xpath = "//input[@id='model']")
    WebElement model;
    @FindBy(xpath = "//input[@id='year']")
    WebElement year;
    @FindBy(xpath = "//select[@id='fuel']")
    WebElement inputFuel;
    @FindBy(xpath = "//input[@id='seats']")
    WebElement seats;
    @FindBy(xpath = "//input[@id='class']")
    WebElement car_class;
    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement inputSerialNumber;
    @FindBy(xpath = "//input[@id='price']")
    WebElement inputPrice;
    @FindBy(xpath = "//textarea[@id='about']")
    WebElement textAreaAbout;
    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement location;
    @FindBy(id = "photos")
    WebElement inputImage;

    public void typeAddNewCarForm(Car car) {
        location.sendKeys(car.getCity());
        manufacture.sendKeys(car.getManufacture());
        model.sendKeys(car.getModel());
        year.sendKeys(car.getYear());
        chooseFuel(car.getFuel());
        //seats.sendKeys(car.getSeats().toString());
        //seats.sendKeys(String.valueOf(car.getSeats()));
        //seats.sendKeys(car.getSeats()+"");
        seats.sendKeys(Integer.toString(car.getSeats()));
        car_class.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
        inputPrice.sendKeys(Double.toString(car.getPricePerDay()));
        textAreaAbout.sendKeys(car.getAbout());
    }

    private void chooseFuel(Fuel fuel) {
        inputFuel.click();
        driver.findElement(By.xpath(fuel.getLocator())).click();
    }

    public void downloadImage(String fileName) {
        inputImage.sendKeys(new File("src/test/resources/"
                + fileName).getAbsolutePath());
    }

    public void clickBtnSubmitWithJS() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\")" +
                ".removeAttribute('disabled')");
        btnSubmit.click();
    }
}
