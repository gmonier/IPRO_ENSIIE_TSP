import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class FormCurrency extends Panel implements ActionListener,ListSelectionListener {


	protected String typeofdevise="Euro";
	private JFrame form ;
	private VendingMachine machine = new VendingMachine<Euro>(new Euro());
	private JPanel devisePanel = new JPanel(new BorderLayout());
	
	public FormCurrency(){
		
		super();
		form =  new JFrame();
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.setResizable(false);
		deviseFill();
	}
	
	private void deviseFill(){
		
		form.setPreferredSize(new Dimension(650,430));
		
		JPanel menuChoiceDevise = new JPanel();
		String[] choiceDevise = {"Euro","Dollar","Peso","Yen"};
		JComboBox menuDevise= new JComboBox(choiceDevise);
		menuDevise.addActionListener(this);
		menuChoiceDevise.add(menuDevise);
		devisePanel.add(menuChoiceDevise,BorderLayout.NORTH);
		devisePanel.add(new JPanel(),BorderLayout.WEST);
		devisePanel.add(new JPanel(),BorderLayout.EAST);
		
		JPanel menuChoiceDeviseButton = new JPanel();
		JButton next = new JButton("Next =>");
		next.addActionListener(this);
		menuChoiceDeviseButton.add(next,BorderLayout.SOUTH);
		devisePanel.add(menuChoiceDeviseButton,BorderLayout.SOUTH);
		this.setDeviseQuantity(this.machine.getDeviseStringValues());
		
		form.add(devisePanel);
		form.pack();
		form.setVisible(true);
			
	}
	

	
 	public void actionPerformed(ActionEvent e) {
 		
		if(e.getSource() instanceof JComboBox){
			JComboBox<String> menu = (JComboBox) e.getSource();
			typeofdevise=(String)menu.getSelectedItem();
		
			switch(typeofdevise){
				case "Euro" : 
				this.machine=new VendingMachine<Euro>(new Euro());
					break;
				case "Dollar" : 
					this.machine=new VendingMachine<Dollar>(new Dollar());
					break;
				case "Peso" : 
					this.machine=new VendingMachine<Peso>(new Peso());
					break;
				case "Yen" : 
					this.machine=new VendingMachine<Yen>(new Yen());
					break;
				default : 
					this.machine=new VendingMachine<Euro>(new Euro());
					break;
			}
			
			this.setDeviseQuantity(this.machine.getDeviseStringValues());
		}
		if(e.getActionCommand()=="Next =>"){
			System.out.println("Next");
			new FormProducts(machine);
			form.dispose();
			
		}
		
	}
 	
	void setDeviseQuantity(ArrayList<String> list){
		
		JPanel panelCoinsQty = new JPanel(new GridLayout(0,2));
		panelCoinsQty.setPreferredSize(new Dimension(500,200));
		
		panelCoinsQty.removeAll();
		for(String value : list){
			panelCoinsQty.add(new JLabel(value+" "+this.machine.currency.getSymbol()));
			JTextField quantityCoins = new JTextField(6);
			panelCoinsQty.add(quantityCoins);
			devisePanel.add(panelCoinsQty,BorderLayout.CENTER);
			form.add(devisePanel);
			form.pack();
			form.setVisible(true);
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
