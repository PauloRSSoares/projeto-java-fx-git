package br.com.senac.aula.java.fx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationFx {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

}
