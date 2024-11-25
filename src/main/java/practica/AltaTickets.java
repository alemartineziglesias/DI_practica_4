package practica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AltaTickets extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldFecha;
	private JTextField textFieldTotal;
	private Datos datos = new Datos();
	private ArrayList<String> articulos = new ArrayList<String>();

	public AltaTickets() 
	{
		setTitle("Tickets -> Alta");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 331);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Fecha:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(205, 11, 36, 14);
		contentPane.add(lblNewLabel);

		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(127, 36, 193, 20);
		textFieldFecha.setColumns(10);
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String hoy = fechaActual.format(formatter);
		textFieldFecha.setText(hoy);
		contentPane.add(textFieldFecha);


		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotal.setBounds(207, 67, 36, 14);
		contentPane.add(lblTotal);

		textFieldTotal = new JTextField();
		textFieldTotal.setColumns(10);
		textFieldTotal.setBounds(127, 92, 193, 20);
		textFieldTotal.setText("0.00");
		contentPane.add(textFieldTotal);

		JLabel lblArtculo = new JLabel("Artículo:");
		lblArtculo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArtculo.setBounds(203, 123, 45, 14);
		contentPane.add(lblArtculo);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(127, 148, 193, 20);
		String[] elementos = datos.rellenarArticulos();
		for(String elemento : elementos)
		{
			comboBox.addItem(elemento);
		}
		contentPane.add(comboBox);
		
		JButton btnAnadir = new JButton("Añadir");
		btnAnadir.setBounds(182, 192, 89, 35);
		btnAnadir.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				articulos.add(((String) comboBox.getSelectedItem()).split("-")[0]);
				Dialogo dialogo = new Dialogo("Artículo añadido");
				dialogo.setVisible(true);
			}
		});
		contentPane.add(btnAnadir);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(86, 246, 89, 35);
		btnAgregar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					int contador = 0;
					for(int i = 0; i < textFieldFecha.getText().length(); i++)
					{
						if(textFieldFecha.getText().charAt(i) == '/')
						{
							contador++;
						}
					}
					if(contador != 2)
					{
						Dialogo dialogo = new Dialogo("Error: la fecha es incorrecta");
						dialogo.setVisible(true);
						textFieldFecha.setText(hoy);
						textFieldFecha.requestFocus();
					}
					else if(textFieldFecha.getText().trim().equals("") || textFieldTotal.getText().trim().equals(""))
					{
						Dialogo dialogo = new Dialogo("Error: debe rellenar todos los campos");
						dialogo.setVisible(true);
					}
					else if(comboBox.getSelectedIndex() != 0)
					{
						String[] comprobacion = null;
						for(int i = 0; i < 3; i++)
						{
							comprobacion = textFieldFecha.getText().split("/");
						}
						int dia = Integer.parseInt(comprobacion[0]);
						int mes = Integer.parseInt(comprobacion[1]);
						int anio = Integer.parseInt(comprobacion[2]);
						boolean fechaCorrecta = Datos.fechaCorrecta(dia, mes, anio);
						if(fechaCorrecta == false)
						{
							Dialogo dialogo = new Dialogo("Error: la fecha es incorrecta");
							dialogo.setVisible(true);
							textFieldFecha.setText(hoy);
							textFieldFecha.requestFocus();
						}
						else
						{
							BigDecimal total = new BigDecimal(Float.parseFloat(textFieldTotal.getText()));
							total = total.setScale(2);
							if(datos.altaTicket(textFieldFecha.getText(), total, articulos) == true)
							{
								Dialogo dialogo = new Dialogo("Ticket creado");
								dialogo.setVisible(true);
								textFieldFecha.setText(hoy);
								textFieldTotal.setText("0.00");
								comboBox.setSelectedItem(0);
								textFieldFecha.requestFocus();
								articulos.clear();
							}
							else
							{
								Dialogo dialogo = new Dialogo("No se pudo crear el ticket");
								dialogo.setVisible(true);
							}
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
		contentPane.add(btnAgregar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(276, 246, 89, 35);
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