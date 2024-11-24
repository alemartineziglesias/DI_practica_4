package practica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Menu extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Menu frame = new Menu();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() 
	{
		setTitle("Menú principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(133, 11, 173, 54);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona una opción:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(113, 62, 209, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton btnArticulos = new JButton("Artículos");
		btnArticulos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnArticulos.setBounds(69, 132, 89, 43);
		btnArticulos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Articulos articulos = new Articulos();
				articulos.setVisible(true);
			}
		});
		contentPane.add(btnArticulos);
		
		JButton btnTickets = new JButton("Tickets");
		btnTickets.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTickets.setBounds(265, 132, 89, 43);
		btnTickets.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Tickets tickets = new Tickets();
				tickets.setVisible(true);
			}
		});
		contentPane.add(btnTickets);
	}
}