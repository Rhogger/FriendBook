package view;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import javax.swing.JMenuBar;

import view.components.MenuBar;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;

/**
 * @author rhogg
 */
public class TelaPrincipal extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel logoApp;

	public TelaPrincipal() {
		setMinimumSize(new Dimension(900, 680));
		setPreferredSize(new Dimension(900, 680));
		setMaximumSize(new Dimension(900, 680));
		getContentPane().setBackground(new Color(31, 31, 31));
		setResizable(false);
		setSize(new Dimension(900, 680));

		MenuBar menuBar = new MenuBar();
		JMenuBar jMenuBar = menuBar.initComponents();
		setJMenuBar(jMenuBar);

		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("FriendBook");
		setBackground(new java.awt.Color(49, 49, 49));

		logoApp = new javax.swing.JLabel();

		logoApp.setIcon(new ImageIcon("assets\\FriendBook-logo.png"));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(352)
						.addComponent(logoApp, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(352, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(230).addComponent(logoApp, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE).addGap(230)));
		getContentPane().setLayout(layout);

		pack();
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaPrincipal().setVisible(true);
			}
		});
	}
}
