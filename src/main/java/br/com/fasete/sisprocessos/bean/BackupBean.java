package br.com.fasete.sisprocessos.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JFileChooser;

import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.util.MySQLBackup;

@ManagedBean
@SessionScoped
public class BackupBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private MySQLBackup backup = new MySQLBackup();
	private String caminho;

	public MySQLBackup getBackup() {
		return backup;
	}

	public void setBackup(MySQLBackup backup) {
		this.backup = backup;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	@PostConstruct
	public void iniciar() {
		backup = new MySQLBackup();
	}

	@SuppressWarnings("static-access")
	public void fazerBackup() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int opcao = jfc.showSaveDialog(null);

		if (opcao == jfc.CANCEL_OPTION) {
			return;
		} else {
			caminho = jfc.getSelectedFile().getPath();

			try {
				backup.backup(caminho);
				Messages.addGlobalInfo("Backup realizado com sucesso!");
				Messages.addGlobalInfo("Tempo de execução: " + backup.getTime() + "ms");
			} catch (IOException e) {
				e.printStackTrace();
				Messages.addGlobalError("Não foi possível realizar o backup!");
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public void restaurarBackup() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int opcao = jfc.showOpenDialog(null);

		if (opcao == jfc.CANCEL_OPTION) {
			return;
		} else {
			caminho = jfc.getSelectedFile().getPath();
			try {
				backup.restore(caminho);
				Messages.addGlobalInfo("Restauração realizada com sucesso!");
				Messages.addGlobalInfo("Tempo de execução: " + backup.getTime() + "ms");
			} catch (IOException e) {
				e.printStackTrace();
				Messages.addGlobalError("Não foi possível restaurar o backup!");
			}
		}
	}
}
