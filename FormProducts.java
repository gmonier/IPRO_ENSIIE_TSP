import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class FormProducts extends Panel implements ActionListener,ListSelectionListener{
	
	protected JList <String> laListe;
	protected DefaultListModel <String> listeModel;
	protected JTextField denom; 
	protected JTextField pricing;
	protected JTextField qty;
	protected String typeOfProduct ="Drink";
	private JFrame form ;
	private VendingMachine machine;

		
	public FormProducts(VendingMachine machine){
		super();
		this.machine=machine;
		form =  new JFrame();
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		form.setResizable(false);
		productsFill();
				
	}
		
		
		
	

	

	public void actionPerformed(ActionEvent e) {
		
		this.actionPerformedOnProductFill(e);
		
	}
		


	private void actionPerformedOnProductFill(ActionEvent e){
		//boucle pour récupérer l'action dans la JComboBox avec source
		
		if (e.getSource() instanceof JComboBox){
			JComboBox<String> menu = (JComboBox) e.getSource();
			//récupére l'item sélectionné dans la JComboBox
			typeOfProduct =(String) menu.getSelectedItem();
			
			
			}
		
		if(e.getActionCommand()=="Add"){
			String pricingString = this.pricing.getText().replace(',', '.');
			Boolean error = false;
			
			if((machine.getProduct(denom.getText())!=null)){
				error=true;
				JOptionPane.showMessageDialog(null,"The product already exist !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
				
			}
			if(denom.getText().length()==0 && !error){
				error=true;
				JOptionPane.showMessageDialog(null,"The product need a name !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
				
			}
			if(pricing.getText().length()==0 && !error){
				error=true;
				JOptionPane.showMessageDialog(null,"The product need a price !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
				
			}
			if(pricing.getText().length()>0 && !error){
				
				BigDecimal i = null;
				try{
					i=new BigDecimal(pricingString);
					if(i.compareTo(new BigDecimal(0))!=1){
						JOptionPane.showMessageDialog(null,"The price must be a positive/not null Integer !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
						error=true;
					}
				}
				catch(NumberFormatException except){
					JOptionPane.showMessageDialog(null,"The price must be an Integer !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
					error=true;
				}
				
			}
			if(qty.getText().length()==0 && !error){
				error=true;
				JOptionPane.showMessageDialog(null,"The product need a quantity !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
				
			}
			if(qty.getText().length()>0 && !error){
				
				Integer i = null;
				try{
					i=Integer.parseInt(qty.getText());
					
					if(i<=0){
						JOptionPane.showMessageDialog(null,"The quantity must be positive !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
						error=true;
					}
				}
				catch(Exception except){
					JOptionPane.showMessageDialog(null,"The quantity must be an Integer !!!","Erreur de Saisie",JOptionPane.WARNING_MESSAGE);
					error=true;
				}
				
			}
			//Conditions respectées adjonction Produits
			if (!error){

                if (typeOfProduct == "Drink") {
                    this.machine.addDrink(denom.getText(),new BigDecimal(pricingString), Integer.parseInt(qty.getText()));
                }
                else if (typeOfProduct == "Food") {
                    this.machine.addFood(denom.getText(),new BigDecimal(pricingString), Integer.parseInt(qty.getText()));
                }
                else if (typeOfProduct == "Other") {
                    this.machine.addProduct(denom.getText(),new BigDecimal(pricingString), Integer.parseInt(qty.getText()));
                }

				listeModel.addElement(typeOfProduct +" : "+denom.getText()+" : price = "+pricing.getText()+"  : qty= "+qty.getText());
			}
			
		}
		
		
		if(e.getActionCommand().equals("Delete")){
			String str[] = this.laListe.getSelectedValue().split(" : ");
			machine.deleteProduct(str[1]);
			int taille=this.laListe.getSelectedIndices().length;
			for(int i=taille-1;i>=0;i--){
				listeModel.remove(laListe.getSelectedIndices()[i]);
			}
			
		}
			
		
		if(e.getActionCommand().equals("Launch")){
			
			if(!machine.getProducts().isEmpty()){
				Distrib distibuteur = new Distrib(machine);
				form.setVisible(false);
			}
			else{
				JOptionPane.showMessageDialog(null,"The Vending machine can't be empty!!!","Settings",JOptionPane.WARNING_MESSAGE);
			}
			machine.printVendingMachine();
			machine.printVendingMachineDetails();
			

			
		}
	}
	

	private void productsFill(){
		form.repaint();
		form.setTitle("TOTO");
		form.setPreferredSize(new Dimension(650,430));
		
		
		
		JPanel product = new JPanel();
		BoxLayout panel = new BoxLayout(product,BoxLayout.Y_AXIS);
		product.setLayout(panel);
		product.setBorder(BorderFactory.createTitledBorder("Product List"));
		product.setPreferredSize(new Dimension(275,390));
		//Création du crollpane et de la liste
		ScrollPane scrollPane = new ScrollPane();
		listeModel = new DefaultListModel<String>();
		laListe = new JList<String>(this.listeModel);
		laListe.addListSelectionListener(this);
		//ajout de la liste au scrollpane
		scrollPane.add(laListe);
		//scrollPane.setForeground(Color.GRAY);
		product.add(scrollPane);
		
		
		JPanel fill = new JPanel(new BorderLayout());
		fill.setBorder(BorderFactory.createTitledBorder("Adding Products"));
		fill.setPreferredSize(new Dimension(275,390));
		
		JPanel button = new JPanel(new FlowLayout());
		JButton valid = new JButton("Add");
		valid.addActionListener(this);
		JButton delete = new JButton("Delete");
		delete.addActionListener(this);
		JButton launch = new JButton("Launch");
		launch.addActionListener(this);
		button.add(valid);
		button.add(delete);
		button.add(launch);
		
		fill.add(button,BorderLayout.SOUTH);
		
		JPanel saisie = new JPanel();
		BoxLayout panel2 = new BoxLayout(saisie,BoxLayout.Y_AXIS);
		saisie.setLayout(panel2);
		saisie.add(new JLabel("Name (20 carac. max)"));
		denom = new JTextField(20);
		saisie.add(denom);
		saisie.add(new JLabel("Price"));
		pricing = new JTextField(20);
		saisie.add(pricing);
		saisie.add(new JLabel("Quantity"));
		qty= new JTextField(20);
		saisie.add(qty);
		String[] choiceProduct = {"Drink","Food","Other"};
		JComboBox menuProduct=new JComboBox(choiceProduct);
		menuProduct.addActionListener(this);
		

		saisie.add(menuProduct);
		fill.add(saisie,BorderLayout.CENTER);
		fill.add(menuProduct,BorderLayout.NORTH);
		
		
		
		JPanel pos = new JPanel(new BorderLayout());
		pos.add(product,BorderLayout.EAST);
		pos.add(fill,BorderLayout.CENTER);
		
		
		this.add("Product",pos);
		form.add(this);
		

		
		form.pack();
		form.setVisible(true);

	}
	

	





	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

