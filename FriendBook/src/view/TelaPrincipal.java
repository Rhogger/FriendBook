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
 *
 * @author rhogg
 */
public class TelaPrincipal extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel logoApp;
    private MenuBar menuBar;

	public TelaPrincipal() {
		getContentPane().setBackground(new Color(31, 31, 31));
		setResizable(false);
		setSize(new Dimension(820, 640));
		
		menuBar = new MenuBar();
		setJMenuBar(menuBar);

		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("FriendBook");
		setBackground(new java.awt.Color(49, 49, 49));

//		MenuBar menuBar = new MenuBar();
		menuBar.initComponents();
		
		logoApp = new javax.swing.JLabel();

		logoApp.setIcon(new ImageIcon("C:\\Users\\rhogg\\git\\FriendBook\\FriendBook\\assets\\FriendBook-logo.png")); // NOI18N

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(253)
						.addComponent(logoApp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(252)));
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
