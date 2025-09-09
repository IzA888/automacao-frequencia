

import com.app.automacaofrequencia.model.Aluno;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomacaoNotas {
    public void lancarNotas(List<Aluno> alunos) {
        System.setProperty("webdrivrt.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try{

            driver.get("https://siteitesite.com/login");
            driver.findElement(By.id("usuario")).sendKeys("meuuser");
            driver.findElement(By.id("senha")).sendKeys("senhasenha");
            driver.findElement(By.id("btn_login")).click();

            try { Thread.sleep(3000); } catch (Exception e) {}

            for (Aluno aluno : alunos) {
                WebElement campo = driver.findElement(By.xpath("//input[@data-aluno='" + aluno.getMatricula() + "'']"));
                campo.clear();
                campo.sendKeys(String.valueOf(aluno.getNota()));
                
                driver.findElement(By.id("btn_salvar")).click();
            }

        } finally {
            driver.quit();
        }
    }
}
