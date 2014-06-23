import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javafx.scene.layout.Border;

import javax.swing.*;

	


public class Distrib extends JPanel implements ActionListener{
	
	private JPanel selec= new JPanel();
	private JPanel admin = new JPanel(new BorderLayout());
	
	VendingMachine machine;

	
	Distrib(VendingMachine machine){
		super(new BorderLayout());
		this.machine=machine;
		JFrame cadre = new JFrame("Vending Machine");
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadre.setResizable(false);
		

		

		BoxLayout layout1 = new BoxLayout(selec, BoxLayout.Y_AXIS);
		selec.setLayout(layout1);
		
		
		
		selec.setBorder(BorderFactory.createTitledBorder("Produits"));
		ScrollPane scrollPane1 = new ScrollPane();
		initButtonProducts(machine.getProducts(),selec);	
		scrollPane1.add(selec);
		

		
		JPanel coins = new JPanel();
		BoxLayout layout2 = new BoxLayout(coins, BoxLayout.Y_AXIS);
		coins.setLayout(layout2);
		coins.setBorder(BorderFactory.createTitledBorder("Coins"));
		coins.add(new JButton("2€"));
		
		admin.setLayout(new GridLayout(0,1));
		admin.setBorder(BorderFactory.createTitledBorder("Administration"));
		ScrollPane scrollPane2 = new ScrollPane();
		scrollPane2.add(admin);
		
		scrollPane2.getForeground();
		
		
		this.add(scrollPane1,BorderLayout.CENTER);
		this.add(coins,BorderLayout.EAST);
		this.add(scrollPane2,BorderLayout.SOUTH);
		
		cadre.add(this);
		cadre.setPreferredSize(new Dimension(400,400));
		cadre.pack();
		cadre.setVisible(true);
		
	}
	
	
	void initButtonProducts(ArrayList<String> list,JPanel panel){
		
		for (String name : list){
			JButton button = new JButton(name+" : "+String.valueOf(machine.getProductPrice(name)));
			panel.add(button);
			button.addActionListener(this); // implémenter un listener pour les produits

		}
		setProductQuantity(list);
	}
	
	void setProductQuantity(ArrayList<String> list){
		
		admin.removeAll();
		for (String name : list){
			admin.add(new JLabel(name+" : qty="+String.valueOf(machine.getProductAmount(name))));	
		}
		

	}

	public void actionPerformed(ActionEvent e) {
		String str[] = e.getActionCommand().split(" : ");
		if(machine.getProduct(str[0])!=null){
			if(machine.getProductAmount(str[0])!=0){
				System.out.println(e.getActionCommand());
				System.out.println(" Price : "+machine.getProductPrice(str[0]));
				
			}
			else{
				JOptionPane.showMessageDialog(null,"The product is not longer available !!!","Change your choice",JOptionPane.WARNING_MESSAGE);
				
			}
			//traitement pour la vente du produit
			/*
			 * if(getProductQuantity(e.getActionCommand())==0){
			 * 
			 * }
			 * */
			
		}
		
	}
	
	


}
