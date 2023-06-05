package br.com.senac.aula.java.fx.controller;

import br.com.senac.aula.java.fx.model.Cliente;
import br.com.senac.aula.java.fx.service.ClienteService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.jfr.Event;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("/main.fxml")
public class ClienteController {
    @FXML
    private TextField nome;

    @FXML
    private TextField documento;

    @FXML
    private TextField rg;

    private int index = -1;

    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaDocumento;

    @FXML
    private TableColumn<Cliente, String> colunaRg;

    @FXML
    public void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDocumento.setCellValueFactory(new PropertyValueFactory<>("documento"));
        colunaRg.setCellValueFactory(new PropertyValueFactory<>("rg"));

        // alteração para teste de commit

        List<Cliente> cliList = ClienteService.carregarClientes();

        tabelaClientes.getItems().addAll(cliList);

        tabelaClientes.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

            @Override
            public void handle(javafx.scene.input.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Cliente cli = tabelaClientes.getSelectionModel().getSelectedItem();
                    nome.setText(cli.getNome());
                    documento.setText(cli.getDocumento());
                    rg.setText(String.valueOf(cli.getRg()));

                    // index = tabelaClientes.getSelectionModel().getSelectedIndex();
                    // busca a posição a ser deletada, ou seja, não importa qual item esteja nesta posição.
                    index = cli.getId();
                    // busca o item exato a ser deletado.

                }
            }
        });
    }

    public void executarOk(){
        /*
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alerta");
        alert.setHeaderText("Nome: " + nome.getText() + "\nDocumento: " + documento.getText() + "\nRg: " + rg.getText());
        alert.show();
        */

        Cliente cli = new Cliente();
        cli.setDocumento(documento.getText());
        cli.setNome(nome.getText());
        if (!rg.getText().equals(""))
            cli.setRg(Integer.parseInt(rg.getText()));

        // atualiza item - resetar index
            if(index > -1) {
            //tabelaClientes.getItems().set(index, cli);
            ClienteService.atualizarCliente(index, cli);
            index = -1;
        }else {
            //inclui novo registro
            //tabelaClientes.getItems().add(cli)
            if (ClienteService.buscarClienteByDocumento(cli.getDocumento())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alerta");
                alert.setHeaderText("Documento: " + documento.getText() + " já existe na base");
                alert.show();
            } else {
                ClienteService.inserirCliente(cli);

            }
        }

        this.carregarListClientes();

        this.limparCampos();

    }

public void executarExcluir(){
        if(index > -1){
            // tabelaClientes.getItems().remove(index);
            ClienteService.deletarCliente(index);
            this.carregarListClientes();
            index = - 1;
            this.limparCampos();
        }
    }

    public void limparCampos(){
        nome.setText("");
        documento.setText("");
        rg.setText("");
    }

    public void carregarListClientes(){

        tabelaClientes.getItems().remove(0,tabelaClientes.getItems().size());

        List<Cliente> cliList = ClienteService.carregarClientes();

        tabelaClientes.getItems().addAll(cliList);
    }
}
