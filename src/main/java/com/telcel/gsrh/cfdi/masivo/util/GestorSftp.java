package com.telcel.gsrh.cfdi.masivo.util;

import com.jcraft.jsch.*;
import com.telcel.gsrh.cfdi.masivo.domain.Credencial;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.*;

import org.apache.commons.io.IOUtils;

public class GestorSftp {

	private static final int PORT = 22;
	private static final String REMOTE_PATH = "/coldview/nom/cv_reposit/work/lbs/backup";
		
	@SuppressWarnings("deprecation")
	public void obtenerArchivoCfdiZipeado(String path, String nbArchivoRemotoZip, Credencial credencial) throws Exception {
		JSch jsch = new JSch();
		Session session = jsch.getSession(credencial.getUsuario(), credencial.getIp(), PORT);
		
		//El usuario y password se proporcionan via la interface UserInfo
		UserInfo ui = new MyUserInfo(credencial.getContrasennia());
		session.setUserInfo(ui);
		session.connect();
		Channel channel = session.openChannel("sftp");
		channel.connect();
		ChannelSftp c = (ChannelSftp) channel;
		
		//Posicionamos en la ruta en que se depositara el archivo
		c.cd(REMOTE_PATH);
		System.out.println(String.format("Posicionados en la ruta %s", REMOTE_PATH));
		
		//Transfiere el archivo
		SftpProgressMonitor monitor = new MyProgressMonitor();
		int mode = ChannelSftp.OVERWRITE;
		
		//inicia ejemplo para obtener el archivo
		InputStream inputFile = c.get(nbArchivoRemotoZip, monitor, mode);
		
		File archivoLocal = new File(path);
		//archivoLocal.setWritable(true);
		OutputStream salida = new FileOutputStream(archivoLocal);
		IOUtils.copy(inputFile, salida);
		
		salida.flush();
		salida.close();
		
		System.out.println(String.format("Archivo %s transferido", nbArchivoRemotoZip));
		session.disconnect();
	}
	
	class MyUserInfo implements UserInfo, UIKeyboardInteractive {
		
		private String password = "";
		
		public MyUserInfo(String password) {
			this.password = password;
		}
		
		public String getPassword() {
			return password;
		}

		public boolean promptYesNo(String str) {
			return true;
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			return true;
		}

		public void showMessage(String message) {
			//Se comenta para que no muestre el mensaje obtenido del servidor
			//JOptionPane.showMessageDialog(null, message);
		}

		final GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0);
		private Container panel;

		public String[] promptKeyboardInteractive(String destination,
				String name, String instruction, String[] prompt, boolean[] echo) {
			panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			gbc.weightx = 1.0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridx = 0;
			panel.add(new JLabel(instruction), gbc);
			gbc.gridy++;

			gbc.gridwidth = GridBagConstraints.RELATIVE;

			JTextField[] texts = new JTextField[prompt.length];
			for (int i = 0; i < prompt.length; i++) {
				gbc.fill = GridBagConstraints.NONE;
				gbc.gridx = 0;
				gbc.weightx = 1;
				panel.add(new JLabel(prompt[i]), gbc);

				gbc.gridx = 1;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weighty = 1;
				if (echo[i]) {
					texts[i] = new JTextField(20);
				} else {
					texts[i] = new JPasswordField(20);
				}
				panel.add(texts[i], gbc);
				gbc.gridy++;
			}

			if (JOptionPane.showConfirmDialog(null, panel, destination + ": "
					+ name, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
				String[] response = new String[prompt.length];
				for (int i = 0; i < prompt.length; i++) {
					response[i] = texts[i].getText();
				}
				return response;
			} else {
				return null; // cancel
			}
		}
	}

	class MyProgressMonitor implements SftpProgressMonitor {
		ProgressMonitor monitor;
		long count = 0;
		long max = 0;

		public void init(int op, String src, String dest, long max) {
			this.max = max;
			monitor = new ProgressMonitor(null,
					((op == SftpProgressMonitor.PUT) ? "put" : "get") + ": "
							+ src, "", 0, (int) max);
			count = 0;
			percent = -1;
			monitor.setProgress((int) this.count);
			monitor.setMillisToDecideToPopup(1000);
		}

		private long percent = -1;

		public boolean count(long count) {
			this.count += count;

			if (percent >= this.count * 100 / max) {
				return true;
			}
			percent = this.count * 100 / max;

			monitor.setNote("Completed " + this.count + "(" + percent
					+ "%) out of " + max + ".");
			monitor.setProgress((int) this.count);

			return !(monitor.isCanceled());
		}

		public void end() {
			monitor.close();
		}
	}
}
