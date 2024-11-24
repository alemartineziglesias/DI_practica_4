package practica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ModificacionArticulos extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDescripcion;
	private JTextField textFieldPrecio;
	private JTextField textFieldCantidad;
	private Datos datos = new Datos();

	public ModificacionArticulos(String id, String descripcion, String precio, String cantidad) 
	{
		setTitle("Artículos -> Modificación");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descripción:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(188, 11, 68, 14);
		contentPane.add(lblNewLabel);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setBounds(121, 37, 193, 20);
		textFieldDescripcion.setText(descripcion);
		contentPane.add(textFieldDescripcion);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrecio.setBounds(202, 68, 42, 14);
		contentPane.add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(121, 93, 193, 20);
		textFieldPrecio.setText(precio);
		contentPane.add(textFieldPrecio);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCantidad.setBounds(196, 124, 54, 14);
		contentPane.add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setColumns(10);
		textFieldCantidad.setBounds(121, 149, 193, 20);
		textFieldCantidad.setText(cantidad);
		contentPane.add(textFieldCantidad);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setBounds(79, 198, 89, 35);
		btnCambiar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					if(textFieldDescripcion.getText().trim().equals("") || textFieldPrecio.getText().trim().equals("") || textFieldCantidad.getText().trim().equals(""))
					{
						Dialogo dialogo = new Dialogo("Error: debe rellenar todos los campos");
						dialogo.setVisible(true);
					}
					else
					{
						BigDecimal precio = new BigDecimal(Float.parseFloat(textFieldPrecio.getText()));
						precio = precio.setScale(2);
						if(datos.modificacionArticulo(id, textFieldDescripcion.getText(), precio, Integer.parseInt(textFieldCantidad.getText())) == true)
						{
							setVisible(false);
							Dialogo dialogo = new Dialogo("Artículo editado");
							dialogo.setVisible(true);
						}
						else
						{
							Dialogo dialogo = new Dialogo("No se permiten caracteres especiales");
							dialogo.setVisible(true);
						}
					}
				}
				catch(NumberFormatException nfe)
				{
					Dialogo dialogo = new Dialogo("Error: Los campos no tienen el formato correcto");
					dialogo.setVisible(true);
				}
			}
		});
		contentPane.add(btnCambiar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(271, 198, 89, 35);
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
