package br.com.desafio.steps;

import java.io.File; 
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class PreencherFormularioSteps {
	private WebDriver driver;

@Dado("^que estou acessando a aplicacao$")
public void queEstouAcessandoAAplicacao() throws Throwable {
	System.setProperty("webdriver.chrome.driver",  "C:\\Cursos-Projetos\\navegadroDriver\\chromedriver.exe"); 	
	driver = new ChromeDriver();
	driver.get("https://docs.google.com/forms/d/e/1FAIpQLScQ7Ej_21M73p2Qf1SaRQt8LgUKGMmPcJt35K8odJKEXzCSMA/viewform?vc=0&c=0&w=1");
}

@Quando("^informo o nome \"([^\"]*)\"$")
public void informoONome(String arg1) throws Throwable {
	driver.findElement(By.xpath("//input[@name='entry.1643251653']")).sendKeys(arg1);
}

@Quando("^o email \"([^\"]*)\"$")
public void oEmail(String arg1) throws Throwable {
	driver.findElement(By.xpath("//input[@name='entry.17350902']")).sendKeys(arg1);
}

@Quando("^a cidade/estado \"([^\"]*)\"$")
public void aCidadeEstado(String arg1) throws Throwable {
	driver.findElement(By.xpath("//input[@name='entry.975579715']")).sendKeys(arg1);
}

@Quando("^a mensagem \"([^\"]*)\"$")
public void aMensagem(String arg1) throws Throwable {
	driver.findElement(By.xpath("//textarea[@name='entry.2005929968']")).sendKeys(arg1);
}

@Quando("^seleciono enviar$")
public void selecionoEnviar() throws Throwable {
	driver.findElement(By.xpath("//span[@class='quantumWizButtonPaperbuttonLabel exportLabel']")).click();
}

@Entao("^o formulario foi preenchido com sucesso$")
public void oformularioFoiPreenchidoComSucesso() throws Throwable {
	String texto = driver.findElement(By.xpath("//div[@class='freebirdFormviewerViewResponseConfirmationMessage']")).getText();
	Assert.assertEquals("Sua resposta foi registrada.", texto);
}
	
@Entao("^sou notificado que o campo obrigatorio nao foi preenchido$")
public void souNotificadoQueOCampoObrigatorioNaoFoiPreenchido() throws Throwable {
	String nome =  driver.findElement(By.xpath("//input[@name='entry.1643251653']")).getAttribute("value");
	String email = driver.findElement(By.xpath("//input[@name='entry.17350902']")).getAttribute("value");
	
	if(nome.equals("")|| nome == null && email != "") {
		String texto = driver.findElement(By.xpath("//div[@id='i.err.1762982292']")).getText();
		Assert.assertEquals("Esta pergunta é obrigatória", texto);	
	}else {
		String texto = driver.findElement(By.xpath("//div[@id='i.err.587771194']")).getText();
		Assert.assertEquals("Esta pergunta é obrigatória", texto);
	}
}

@After(order = 1)
public void screenshot(Scenario cenario) {
	File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		FileUtils.copyFile(file,new File("target/screenshot/"+cenario.getId()+".jpg"));
	}catch(IOException e) {
		e.printStackTrace();
	}

}

@After(order = 0)
public void fecharBrowser() {
	driver.quit();
}

}
