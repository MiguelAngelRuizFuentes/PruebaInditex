import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class AutomatizacionGoogle {

    public static void main(String[] args) {
        // Configuración del sistema para usar ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\Users\Miguel Angel\Desktop\drivers/chromedriver.exe");
        
        // Inicializar el navegador
        WebDriver driver = new ChromeDriver();
        
        try {
            // 1. Buscar en Google la palabra "automatización"
            driver.get("https://www.google.com");
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("automatización");
            searchBox.submit();
            
            // 2. Buscar el link de la Wikipedia resultante
            WebElement wikipediaLink = driver.findElement(By.partialLinkText("Wikipedia"));
            wikipediaLink.click();
            
            // 3. Comprobar en qué año se hizo el primer proceso automático
            WebElement content = driver.findElement(By.id("content"));
            String pageContent = content.getText();
            int yearIndex = pageContent.indexOf("primer proceso automático");
            String yearText = pageContent.substring(yearIndex - 10, yearIndex).trim();
            
            // Extraer el año del texto (esto puede variar según la página)
            String[] words = yearText.split(" ");
            int year = Integer.parseInt(words[words.length - 1]);
            System.out.println("El primer proceso automático se hizo en el año: " + year);
            
            // 4. Realizar un screenshot de la página de la Wikipedia
            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el navegador
            driver.quit();
        }
    }
}
