
package sistema_cadastro;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import javax.swing.JOptionPane;

public class Sistema_Cadastro {
        
    
    public static void main(String[] args) {
    
        Pessoa user = new Pessoa();
        ObjectContainer dbo;
        
        JOptionPane.showMessageDialog(null,"Seja bem-vindo ao Sistema de Cadastro !"); 

        
        int opcao=0;
        
        while(opcao!=5){
        
        opcao= Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção desejada:"
            + "\nOpção 1: Insert.\nOpção 2: Update."
            + "\nOpção 3: Delete.\nOpção 4: Select."
            + "\nOpção 5: Sair."));
    
        //INSERT
        if(opcao ==1){
        dbo = Db4o.openFile("Cadastro.dbo");
         
        user.setNome(JOptionPane.showInputDialog("Insira o nome: "));
        
        user.setIdade(JOptionPane.showInputDialog("Insira a idade: "));
 
        user.setCpf(JOptionPane.showInputDialog("Insira o CPF: "));
        
        user.setEndereco(JOptionPane.showInputDialog("Insira o endereço: "));        
        
        user.setTelefone(JOptionPane.showInputDialog("Insira o telefone: "));
        
        user.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira o ID: "))); 
        
        dbo.store(user);
        dbo.close();
       
        JOptionPane.showMessageDialog(null,"Inserção feita com sucesso ! "); 
        }
        
        //UPDATE
        else if (opcao ==2){
        dbo = Db4o.openFile("Cadastro.dbo");

        Query busca = dbo.query();
        busca.constrain(Pessoa.class);
        user.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira o ID para realizar o update:")));
        busca.descend("id").constrain(user.getId());
        ObjectSet<Pessoa> resultado = busca.execute();

        if (!resultado.isEmpty()) {
           Pessoa dadosupdate = resultado.next();
            
           int Update= Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção desejada para update no ID "
           +user.getId()+":"+ "\nOpção 1: Nome.\nOpção 2: Idade."
           + "\nOpção 3: CPF.\nOpção 4: Endereço."
           + "\nOpção 5: Telefone."));
           
           switch (Update) {
            case 1:
                dadosupdate.setNome(JOptionPane.showInputDialog("Insira o novo nome: "));
                break;
            case 2:
                dadosupdate.setIdade(JOptionPane.showInputDialog("Insira a nova idade: "));
                break;
            case 3:
                dadosupdate.setCpf(JOptionPane.showInputDialog("Insira o novo CPF: "));
                break;
            case 4:
                dadosupdate.setEndereco(JOptionPane.showInputDialog("Insira o novo endereço: "));
                break;
            case 5:
                dadosupdate.setTelefone(JOptionPane.showInputDialog("Insira o novo telefone: "));
                break;
                  
            } 

            dbo.store(dadosupdate);
        
            JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso !");
            
            JOptionPane.showMessageDialog(null, "Dados Atualizados:"
            + "\nID: " +dadosupdate.getId()+ "\nNome: " + dadosupdate.getNome() +"\nIdade: "
            + dadosupdate.getIdade()+ "\nCPF: "+ dadosupdate.getCpf() +"\nEndereço: "
            + dadosupdate.getEndereco() + "\nTelefone: " +dadosupdate.getTelefone());        

            }else {
              JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para o ID " + user.getId(), "Mensagem", JOptionPane.ERROR_MESSAGE);
            }
        dbo.close();
        }
        
        //DELETE
        else if (opcao == 3) {
        dbo = Db4o.openFile("Cadastro.dbo");

        Query busca = dbo.query();
        busca.constrain(Pessoa.class);

        user.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira o ID para excluir:")));
        
        int Delete = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro com ID " 
        + user.getId() + "?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);

        if (Delete == JOptionPane.YES_OPTION) {
            busca.descend("id").constrain(user.getId());
            ObjectSet<Pessoa> resultado = busca.execute();
            
            if (!resultado.isEmpty()) {
            dbo.delete(resultado.next());
            JOptionPane.showMessageDialog(null, "Dados deletados com sucesso.");
            } else {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para o ID fornecido.", "Mensagem", JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Exclusão cancelada.");
        }

        dbo.close();
        }
        
        //SELECT
        else if (opcao ==4){
        dbo = Db4o.openFile("Cadastro.dbo");
        Query busca = dbo.query();
        busca.constrain(Pessoa.class);
        user.setId(Integer.parseInt(JOptionPane.showInputDialog("Insira o ID para realizar a consulta:")));
        busca.descend("id").constrain(user.getId());
        
        ObjectSet<Pessoa> lista = busca.execute();
        
        if(!lista.isEmpty()){
        user = lista.next();
        
        JOptionPane.showMessageDialog(null, "Dados Inseridos:"
            + "\nID: " +user.getId()+ "\nNome: " + user.getNome() +"\nIdade: "
            + user.getIdade()+ "\nCPF: "+ user.getCpf() +"\nEndereço: "
            + user.getEndereco() + "\nTelefone: " +user.getTelefone());
        
        
        }else{
        JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para o ID fornecido.", "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        
        dbo.close();
        }
        
        //ENCERRAR PROGRAMA
        else if (opcao ==5){
        JOptionPane.showMessageDialog(null,"Programa encerrado.");
        }
        
        //COMANDO INVÁLIDO
        else if (opcao > 5){
        JOptionPane.showMessageDialog(null, "Comando inválido, tente novamente.", "ERRO!!", JOptionPane.ERROR_MESSAGE);
        }
        
        }//WHILE OPCAO==5         
         
    }//METODO MAIN
    
}//CLASS SISTEMA_CADASTRO
