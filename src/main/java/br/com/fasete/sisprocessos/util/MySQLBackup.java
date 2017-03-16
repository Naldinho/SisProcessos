package br.com.fasete.sisprocessos.util;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class MySQLBackup {

	// Constantes da classe
	private static String SEPARATOR = File.separator;

	// C:\Program Files\MySQL\MySQL Server 5.7\bin
	private static String MYSQL_PATH = "C:" + SEPARATOR + "Arquivos de programas" + SEPARATOR + "MySQL" + SEPARATOR
			+ "MySQL Server 5.7" + SEPARATOR + "bin" + SEPARATOR;

	private static String DATABASE = "sisprocessos";

	private String USUARIO = "root"; // UsuárioDB
	private String SENHA = "root"; // Senha do UsuárioDB
	private long time;

	public MySQLBackup() {
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void backup(String caminho) throws IOException {
		String comando = MYSQL_PATH + "mysqldump.exe";

		// Tempo
		long time1, time2;

		// Início
		time1 = System.currentTimeMillis();

		ProcessBuilder pb = new ProcessBuilder(comando, "--user=" + USUARIO, "--password=" + SENHA, DATABASE,
				"--result-file=" + caminho + SEPARATOR + DATABASE + ".sql");

		pb.start();

		// Fim
		time2 = System.currentTimeMillis();

		// Tempo total da operação
		time = time2 - time1;

	}

	public void restore(String caminhoArquivo) throws IOException {
		String comando = MYSQL_PATH + "mysql.exe";

		// Tempo
		long time1, time2;

		// Início
		time1 = System.currentTimeMillis();

		// Processo
		String[] executeCmd = new String[] { comando, "--user=" + USUARIO,
				"--password=" + SENHA, DATABASE, "-e", "source " + caminhoArquivo };
		Runtime.getRuntime().exec(executeCmd);

		// Fim
		time2 = System.currentTimeMillis();

		// Tempo total da operação
		time = time2 - time1;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		MySQLBackup app = new MySQLBackup();

		/*
		 * JFileChooser x = new JFileChooser();
		 * x.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); int resultado
		 * = x.showSaveDialog(null); //x.showOpenDialog(null);
		 * 
		 * if(resultado == x.CANCEL_OPTION){ System.out.println("cancelado");
		 * }else{ String caminho = x.getSelectedFile().getPath();
		 * 
		 * System.out.println("caminho: " + caminho);
		 * 
		 * try { app.backup(caminho);
		 * System.out.println("backup realizado com sucesso!"); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

		JFileChooser x = new JFileChooser();
		x.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int resultado = x.showOpenDialog(null);

		if (resultado == x.CANCEL_OPTION) {
			System.out.println("cancelado");
		} else {
			String caminho = x.getSelectedFile().getPath();

			System.out.println("caminho: " + caminho);

			try {
				app.restore(caminho);
				System.out.println("restore realizado com sucesso!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
