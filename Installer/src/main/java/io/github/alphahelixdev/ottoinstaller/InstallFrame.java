package io.github.alphahelixdev.ottoinstaller;

import io.github.alphahelixdev.helius.file.text.TextFile;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

public class InstallFrame extends JFrame {
	private JFileChooser pathChooser = new JFileChooser();
	private final Image backgroundImage = new ImageIcon(getClass().getResource("/background.jpg")).getImage();
	
	private InstallFrame() {
		setTitle("O(tto)P(rogramming)L(anguage) R(untime)E(nvironment) Installer");
		
		pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		pathChooser.setFileHidingEnabled(true);
		pathChooser.setApproveButtonText("Install");
		
		pathChooser.addActionListener(e -> {
			if(e.getActionCommand().equals("ApproveSelection")) {
				try {
					install(pathChooser.getSelectedFile());
				} catch(IOException e1) {
					e1.printStackTrace();
				}
			}
			
			dispose();
		});
		
		JPanel contentPane = new JPanel(new BorderLayout()) {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, null);
			}
		};
		
		contentPane.add(pathChooser);
		
		add(contentPane);
		
		setContentPane(contentPane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void install(File path) throws IOException {
		create(path);
		moveJar(path);
		
		String os = System.getProperty("os.name");
		
		File jarExec = new File(path, "otto" + (os.equals("Windows") ? ".bat" : ".sh"));
		create(jarExec);
		
		write(jarExec, "java -jar " + path.getAbsolutePath() + "/OttoPL.jar");
		
		if(os.equals("Windows"))
			new ProcessBuilder(new String[]{"set", "PATH=%PATH%;" + jarExec.getAbsolutePath()}).start();
		else {
			TextFile bashProfile = new TextFile(System.getProperty("user.home"), ".bash_profile");
			
			create(bashProfile);
			
			if(!bashProfile.getContent().contains("source ~/.bashrc"))
				bashProfile.write("source ~/.bashrc");
			
			TextFile bashrc = new TextFile(System.getProperty("user.home"), ".bashrc");
			
			create(bashrc);
			
			if(!bashrc.getContent().contains("alias otto='" + jarExec.getAbsolutePath() + "'"))
				bashrc.write("alias otto='" + jarExec.getAbsolutePath() + "'");
		}
	}
	
	private void moveJar(File toPath) {
		try {
			File currentJar = Objects.requireNonNull(
					new File(InstallFrame.class.getProtectionDomain().getCodeSource().getLocation().toURI())
							.getParentFile().listFiles(pathname -> pathname.getName().endsWith("OttoPL.jar")))[0];
			
			create(currentJar);
			create(toPath);
			
			File newJar = new File(toPath, "OttoPL.jar");
			
			create(newJar);
			
			currentJar.renameTo(newJar);
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private void create(File file) {
		file.setExecutable(true);
		file.setWritable(true);
		file.setReadable(true);
		
		if(!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void write(File file, String toWrite) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(toWrite);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		InstallFrame installFrame = new InstallFrame();
		
		installFrame.setPreferredSize(new Dimension(600, 300));
		installFrame.setLocationRelativeTo(null);
		
		installFrame.pack();
		installFrame.setVisible(true);
	}
}
