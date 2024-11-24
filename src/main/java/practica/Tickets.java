package practica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Tickets extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Tickets() 
	{
		setTitle("Tickets");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(180, 31, 89, 40);
		btnAlta.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AltaTickets alta = new AltaTickets();
				alta.setVisible(true);
			}
		});
		contentPane.add(btnAlta);
		
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.setBounds(180, 106, 89, 40);
		btnConsulta.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConsultaTickets consulta = new ConsultaTickets();
				consulta.setVisible(true);
			}
		});
		contentPane.add(btnConsulta);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(180, 183, 89, 40);
		btnVolver.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		contentPane.add(btnVolver);
	}

}
