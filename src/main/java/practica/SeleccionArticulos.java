package practica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class SeleccionArticulos extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Datos datos = new Datos();

	public SeleccionArticulos() 
	{
		setTitle("Artículos -> Modificación");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Selecciona el artículo a modificar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(53, 49, 345, 25);
		contentPane.add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(96, 100, 246, 25);
		String[] elementos = datos.rellenarArticulos();
		for(String elemento : elementos)
		{
			comboBox.addItem(elemento);
		}
		contentPane.add(comboBox);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(96, 170, 89, 32);
		btnEditar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(comboBox.getSelectedIndex() == 0)
				{
					Dialogo dialogo = new Dialogo("Elige un artículo");
					dialogo.setVisible(true);
				}
				else
				{
					setVisible(false);
					ModificacionArticulos modificacion = new ModificacionArticulos(((String) comboBox.getSelectedItem()).split("-")[0], ((String) comboBox.getSelectedItem()).split("-")[1], ((String) comboBox.getSelectedItem()).split("-")[2], ((String) comboBox.getSelectedItem()).split("-")[3]);
					modificacion.setVisible(true);
				}
			}
		});
		contentPane.add(btnEditar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(253, 170, 89, 32);
		btnCancelar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		contentPane.add(btnCancelar);
	}
}