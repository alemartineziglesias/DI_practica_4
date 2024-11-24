package practica;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoBaja extends JDialog 
{
	private static final long serialVersionUID = 1L;
	private Datos datos = new Datos();

	public DialogoBaja(String id) 
	{
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Estás seguro?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 99, 414, 40);
		getContentPane().add(lblNewLabel);
		
		JButton btnSi = new JButton("Sí");
		btnSi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSi.setBounds(102, 177, 89, 40);
		btnSi.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
				if(datos.bajaArticulo(id) == false)
				{
					setVisible(false);
					Dialogo dialogo = new Dialogo("Artículo eliminado");
					dialogo.setVisible(true);
				}
				else
				{
					setVisible(false);
					Dialogo dialogo = new Dialogo("El artículo ya está relacionado con un ticket");
					dialogo.setVisible(true);
				}
			}
		});
		getContentPane().add(btnSi);
		
		JButton btnNo = new JButton("No");
		btnNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNo.setBounds(243, 177, 89, 40);
		btnNo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				BajaArticulos baja = new BajaArticulos();
				baja.setVisible(true);
				setVisible(false);
			}
		});
		getContentPane().add(btnNo);

	}
}
