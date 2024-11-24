package practica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ConsultaArticulos extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Datos datos = new Datos();

	public ConsultaArticulos() 
	{
		setTitle("Artículos -> Consulta");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(28, 11, 375, 196);
		textArea.append(datos.consultaArticulos());
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(171, 218, 89, 32);
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
