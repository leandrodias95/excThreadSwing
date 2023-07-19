package br.com.excthreadswing.visao;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.swing.JButton;

public class TelaTimeDataEnvioEmail extends JDialog {
	
	private JPanel jPanel = new JPanel(new GridBagLayout()); //painel de componentes
	
	private JLabel descricaoHoraBrasil = new JLabel("Horário do Brasil: ");
	private JTextField mostraHoraBrasil = new JTextField();
	
	private JLabel descricaoHoraIngalterra = new JLabel("Horário da Inglaterra");
	private JTextField mostraHoraInglaterra = new JTextField();
	
	private JLabel descricaoEnvioEmail = new JLabel("Processo do envio de e-mail");
	private JTextField mostraEnvioEmail = new JTextField();
	
	private JButton jBtnStart = new JButton ("Iniciar");
	private JButton jBtnStop = new JButton("Pausar");
	private JButton jBtnCancel = new JButton("Cancelar");

	
	private Runnable thread1 = new Runnable() {
		
		@Override
		public void run() {
			
			while(true) {
				mostraHoraBrasil.setText( new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	private Thread threadTimeBrasil;
	private Runnable thread2 = new Runnable() {
		
		@Override
		public void run() {
			while(true) {
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.HOUR, 4);
				mostraHoraInglaterra.setText( new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(calendar.getTime()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		}
	};
	private Thread threadTimeInglaterra;
private Runnable thread3 = new Runnable() {
		
		@Override
		public void run() {
			for(int pos=1;pos<=100;pos++) {
				mostraEnvioEmail.setText("Envio de e-mail: "+pos);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		}
	};
	private Thread threadEnvioEmail;
	public TelaTimeDataEnvioEmail() {
		setTitle("Horário de Envio de E-mail");
		setSize(new Dimension(330, 300));
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints(); //controla o posicionamento dos componentes
		gridBagConstraints.gridx=0;
		gridBagConstraints.gridy=0;
		gridBagConstraints.gridwidth=3;
		gridBagConstraints.insets= new Insets(5,10,5,5); //pading da o espaço entre os componentes
		gridBagConstraints.anchor = gridBagConstraints.WEST;
		
		descricaoHoraBrasil.setPreferredSize(new Dimension(200, 25));
		jPanel.add(descricaoHoraBrasil, gridBagConstraints);
		
		mostraHoraBrasil.setPreferredSize(new Dimension(300, 25));
		gridBagConstraints.gridy++;
		mostraHoraBrasil.setEditable(false);
		jPanel.add(mostraHoraBrasil, gridBagConstraints);
		
		descricaoHoraIngalterra.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy++;
		jPanel.add(descricaoHoraIngalterra, gridBagConstraints);
		
		mostraHoraInglaterra.setPreferredSize(new Dimension(300, 25));
		gridBagConstraints.gridy++;
		mostraHoraInglaterra.setEditable(false);
		jPanel.add(mostraHoraInglaterra, gridBagConstraints);
		
		descricaoEnvioEmail.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy++;
		jPanel.add(descricaoEnvioEmail, gridBagConstraints);
		
		mostraEnvioEmail.setPreferredSize(new Dimension(300, 25));
		gridBagConstraints.gridy++;
		mostraEnvioEmail.setEditable(false);
		jPanel.add(mostraEnvioEmail, gridBagConstraints);
		
		
		
		gridBagConstraints.gridwidth=1;
		jBtnStart.setPreferredSize(new Dimension(90, 25));
		gridBagConstraints.gridy++;
		jPanel.add(jBtnStart, gridBagConstraints);
		
		jBtnStop.setPreferredSize(new Dimension(90, 25));
		gridBagConstraints.gridx++;
		jPanel.add(jBtnStop, gridBagConstraints);
		
		jBtnCancel.setPreferredSize(new Dimension(90, 25));
		gridBagConstraints.gridx++;
		jPanel.add(jBtnCancel, gridBagConstraints);
				
		
		jBtnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadTimeBrasil = new Thread(thread1);
				threadTimeBrasil.start();
				
				threadTimeInglaterra = new Thread(thread2);
				threadTimeInglaterra.start();
				
				threadEnvioEmail = new Thread(thread3);
				threadEnvioEmail.start();
				
				
				jBtnStart.setEnabled(false);
				jBtnStop.setEnabled(true);
			}
		});
		
		
		jBtnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadTimeBrasil.stop();
				threadTimeInglaterra.stop();
				threadEnvioEmail.stop();
				
				jBtnStart.setEnabled(true);
				jBtnStop.setEnabled(false);
				
			}
		});
		
		jBtnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadTimeBrasil.stop();
				threadTimeInglaterra.stop();
				threadEnvioEmail.stop();
				
				jBtnStart.setEnabled(false);
				jBtnStop.setEnabled(false);
				setVisible(false);
				System.exit(0);
				
			}
		});
		
		jBtnStop.setEnabled(false);
		add(jPanel, BorderLayout.WEST); //adiciona o componente swing ao painel, posicionando para oeste(esquerda)
		setVisible(true); //torna a tela visivel ao usuário
	}
}
