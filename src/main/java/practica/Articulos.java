package practica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Articulos extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Articulos() 
	{
		setTitle("Artículos");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAlta= new JButton("Alta");
		btnAlta.setBounds(170, 20, 100, 35);
		btnAlta.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AltaArticulos alta = new AltaArticulos();
				alta.setVisible(true);
			}
		});
		contentPane.add(btnAlta);
		
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.setBounds(170, 66, 100, 35);
		btnConsulta.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				ConsultaArticulos consulta = new ConsultaArticulos();
				consulta.setVisible(true);
			}
		});
		contentPane.add(btnConsulta);
		
		JButton btnModificacion = new JButton("Modificación");
		btnModificacion.setBounds(170, 112, 100, 35);
		btnModificacion.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				SeleccionArticulos seleccion = new SeleccionArticulos();
				seleccion.setVisible(true);
			}
		});
		contentPane.add(btnModificacion);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.setBounds(170, 158, 100, 35);
		btnBaja.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				BajaArticulos baja = new BajaArticulos();
				baja.setVisible(true);
			}
		});
		contentPane.add(btnBaja);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(170, 204, 100, 35);
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