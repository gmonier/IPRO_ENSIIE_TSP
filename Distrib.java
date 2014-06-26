import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.*;

	


public class Distrib extends JPanel implements ActionListener{
	
	private JPanel selec= new JPanel();
	private JPanel admin = new JPanel();
	private JPanel coins = new JPanel();
	private JLabel inputtedMoney = new JLabel();
	
	VendingMachine machine;

	
	Distrib(VendingMachine machine){
		super(new BorderLayout());
		this.machine=machine;
		JFrame cadre = new JFrame("Vending Machine");
		cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cadre.setResizable(true);
		

		

		BoxLayout layout1 = new BoxLayout(selec, BoxLayout.Y_AXIS);
		selec.setLayout(layout1);
		
		
		
		selec.setBorder(BorderFactory.createTitledBorder("Products"));
		ScrollPane scrollPane1 = new ScrollPane();
		initButtonProducts(machine.getProducts(),selec);	
		scrollPane1.add(selec);
		

		
		
		BoxLayout layout2 = new BoxLayout(coins, BoxLayout.Y_AXIS);
		coins.setLayout(layout2);
		coins.setBorder(BorderFactory.createTitledBorder("Coins"));
		ScrollPane scrollPane2 = new ScrollPane();
		System.out.println(machine.currency.getSymbol());
		this.initButtonCurrency(machine.getCurrencyStringValues(), coins);


		scrollPane2.add(coins);
		
		
		admin.setLayout(new BoxLayout(admin,BoxLayout.X_AXIS));
		//admin.setBorder(BorderFactory.createTitledBorder("Administration"));
		ScrollPane scrollPane3 = new ScrollPane();
		scrollPane3.add(admin);
		
		scrollPane3.getForeground();
		
		
		this.add(scrollPane1,BorderLayout.CENTER);
		this.add(scrollPane2,BorderLayout.EAST);
		this.add(scrollPane3,BorderLayout.SOUTH);
		
		cadre.add(this);
		cadre.setPreferredSize(new Dimension(400,440));
		cadre.pack();
		cadre.setVisible(true);
		
	}
	
	
	void initButtonProducts(ArrayList<String> list,JPanel panel){
		
		for (String name : list){
			JButton button = new JButton(name+" : "+String.valueOf(machine.getProductPrice(name))+this.machine.currency.getSymbol());
			panel.add(button);
			button.addActionListener(this); // implémenter un listener pour les produits

		}
		setProductQuantity(list);
	}
	
	void initButtonCurrency(ArrayList<String> list,JPanel panel){
		
		for(String name : list){
			
			JButton button = new JButton(name+" "+this.machine.currency.getSymbol());
			panel.add(button);
			button.addActionListener(this);
		}
		panel.add(inputtedMoney);
		JButton giveBack = new JButton("Return");
		giveBack.addActionListener(this);
		panel.add(giveBack);
		setCoinsQuantity(list);
	}
	
	void setProductQuantity(ArrayList<String> list){
		int i=0;
		JPanel ProductQty = new JPanel(new GridLayout(8,1));
		admin.removeAll();
		ProductQty.removeAll();
		ProductQty.setBorder(BorderFactory.createTitledBorder("Products Quantity"));
		for (String name : list){
			ProductQty.add(new JLabel(name+" : qty="+String.valueOf(machine.getProductAmount(name)))).setLocation(i,0);
			i++;
			
		}
		admin.add(ProductQty);
	}
	
	void setCoinsQuantity(ArrayList<String> list){
		int i=0;
		JPanel CoinsQty = new JPanel(new GridLayout(0,1));
		CoinsQty.setBorder(BorderFactory.createTitledBorder("Products Quantity"));
		for(String value : list){
			CoinsQty.add(new JLabel(value+this.machine.currency.getSymbol()+" : qty="+this.machine.getCoinsAmount(new BigDecimal(value)))).setLocation(i,1);;
			i++;
		}
		admin.add(CoinsQty);
	}
		

	public void actionPerformed(ActionEvent e) {
		String str[] = e.getActionCommand().split(" : ");

			System.out.println(e.getActionCommand());

			System.out.println(" Price : "+String.valueOf(machine.getProductPrice(str[0])));

			if(machine.getProduct(str[0])!= null){
				switch(machine.buyProduct(str[0])){
					
					case 0 : 
						JOptionPane.showMessageDialog(null,"Success !!! Machine give you back "+String.valueOf(machine.getChangeValue())+machine.currency.getSymbol(),"Transaction",JOptionPane.INFORMATION_MESSAGE);
						setProductQuantity(this.machine.getProducts());
						setCoinsQuantity(machine.getCurrencyStringValues());
						BigDecimal money = this.machine.giveBackMoney();
						inputtedMoney.setText(String.valueOf("  " + String.valueOf(this.machine.getEnteredSum()) + " " + this.machine.currency.getSymbol()));
					break;
					
					case 1 : 
						JOptionPane.showMessageDialog(null,"The Product doesn't exist !!!","Transaction",JOptionPane.INFORMATION_MESSAGE);
					break;
					
					case 2 :
						JOptionPane.showMessageDialog(null,"You didn't insert enought money !!!","Transaction",JOptionPane.INFORMATION_MESSAGE);
					break;
					
					case 3 : 
						JOptionPane.showMessageDialog(null,"I'm not able to give you the change !!!","Transaction",JOptionPane.INFORMATION_MESSAGE);
					break;
					
					case 4 :
						JOptionPane.showMessageDialog(null,"The product is not longer available !!!","Change your choice",JOptionPane.WARNING_MESSAGE);
					break;
				}
				
				
			}
			
			
		
		
		String value[] = e.getActionCommand().split(" ");
		if(this.machine.currency.getStringValues().contains(value[0])){
			this.machine.addEnteredCoin(value[0]);
			inputtedMoney.setText(String.valueOf("  "+String.valueOf(this.machine.getEnteredSum())+" "+this.machine.currency.getSymbol()));
		
		}
		
		if(e.getActionCommand()=="Return"){
			BigDecimal money = this.machine.giveBackMoney();
			inputtedMoney.setText(String.valueOf("  "+String.valueOf(this.machine.getEnteredSum())+" "+this.machine.currency.getSymbol()));
			JOptionPane.showMessageDialog(null,"The machine give you back : "+String.valueOf(money)+this.machine.currency.getSymbol(),"Money Given Back",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
	}
	
	


}
