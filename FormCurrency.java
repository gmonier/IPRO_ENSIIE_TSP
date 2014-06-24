import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class FormCurrency extends Panel implements ActionListener,ListSelectionListener {


	protected String typeOfCurrency ="Euro";
	private JFrame form ;
	private VendingMachine machine = new VendingMachine<Euro>(new Euro());
	private JPanel currencyPanel = new JPanel(new BorderLayout());
	private HashMap<String,JTextField> coinsQtyFromStringValues = new HashMap<String,JTextField>();
	private JPanel panelCoinsQty= new JPanel(new GridLayout(0,2));
	
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
		
		currencyPanel.add(menuChoiceDevise, BorderLayout.NORTH);
		currencyPanel.add(new JPanel(), BorderLayout.WEST);
		currencyPanel.add(new JPanel(), BorderLayout.EAST);
		
		JPanel menuChoiceDeviseButton = new JPanel();
		JButton next = new JButton("Next =>");
		next.addActionListener(this);
		menuChoiceDeviseButton.add(next,BorderLayout.SOUTH);
		currencyPanel.add(menuChoiceDeviseButton, BorderLayout.SOUTH);
		this.setCurrencyQuantity(this.machine.getCurrencyStringValues());
		
		form.add(currencyPanel);
		form.pack();
		form.setVisible(true);
			
	}
	

	
 	public void actionPerformed(ActionEvent e) {
 		
		if(e.getSource() instanceof JComboBox){
			JComboBox<String> menu = (JComboBox) e.getSource();
			typeOfCurrency =(String)menu.getSelectedItem();

            if (typeOfCurrency == "Euro"){
                this.machine=new VendingMachine<Euro>(new Euro());
            }
            else if (typeOfCurrency == "Dollar"){
                this.machine=new VendingMachine<Dollar>(new Dollar());
            }
            else if (typeOfCurrency == "Peso"){
                this.machine=new VendingMachine<Peso>(new Peso());
            }
            else if (typeOfCurrency == "Yen"){
                this.machine=new VendingMachine<Yen>(new Yen());
            }
            else{
                this.machine=new VendingMachine<Euro>(new Euro());
            }
			
			this.setCurrencyQuantity(this.machine.getCurrencyStringValues());
		}
		
		if(e.getActionCommand()=="Next =>"){
			System.out.println("Next");
			boolean error = false;
			
			for(String coin : (ArrayList<String>)this.machine.getCurrencyStringValues()){
				if(!error){
					try{
						Integer amount =  Integer.parseInt(coinsQtyFromStringValues.get(coin).getText());
						
						if(amount<0){
							JOptionPane.showMessageDialog(null,"The quantity of "+coin+" "+machine.currency.getSymbol()+ " must be positive !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
							error=true;
						}
						else{
							machine.addCoinsAmount(new BigDecimal(coin), amount );
						}
					}
					catch(Exception except){
						JOptionPane.showMessageDialog(null,"The quantity of "+coin+" "+machine.currency.getSymbol()+ " must be an Integer !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
						error=true;
					}
					System.out.println("coin"+coin+"qty"+machine.getCoinsAmount(new BigDecimal(coin)));			
				}
			}
			if(!error){
				new FormProducts(machine);
				form.dispose();
			}
			
		}
		
	}
 	
	void setCurrencyQuantity(ArrayList<String> list){
		
		currencyPanel.remove(this.panelCoinsQty);
		
		this.panelCoinsQty = new JPanel(new GridLayout(0,2));
		panelCoinsQty.setPreferredSize(new Dimension(500,200));
		panelCoinsQty.resetKeyboardActions();
		
		//currencyPanel.removeAll();

		
		for(String value : list){
			panelCoinsQty.add(new JLabel(value+" "+this.machine.currency.getSymbol()));
			JTextField quantityCoins = new JTextField(6);
			panelCoinsQty.add(quantityCoins);
			currencyPanel.add(panelCoinsQty, BorderLayout.CENTER);
			coinsQtyFromStringValues.put(value,quantityCoins);
			form.add(currencyPanel);
			form.pack();
			form.setVisible(true);
		}
		
	}
	
	
	
	

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
