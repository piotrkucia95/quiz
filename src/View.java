import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//actual GUI
public class View {
	
	private JFrame frame;
	private JPanel panel;
	private JButton start;
	private JButton next;
	private JButton wyjscie;
	private JButton poprzedni;
	private JRadioButton odpA;
	private JRadioButton odpB;
	private JRadioButton odpC;
	private ButtonGroup odp;
	private JLabel tresc;
	private JLabel info;
	private JLabel punkty;
	private Random liczba;
	private QuestionReader pyt;
	
	private String odpowiedz;
	private String trescPytania;
	private String odpowiedzA;
	private String odpowiedzB;
	private String odpowiedzC;
	private int punkt 		= 0;
	private int nr_pytania;
	private int poprzednie	= -1;
	private int ktore 		= 0;
	private int zakres 		= 484;
	private boolean sprawdz;
	private int[] p;
	
	//View constructor
	public View() {
		
		frame 		= new JFrame("Inżynieria Materiałowa - test");
		panel 		= new JPanel();
		start 		= new JButton("Start");
		wyjscie 	= new JButton("Zamknij");
		tresc 		= new JLabel("Pytania z egzaminu kierunkowego Inżynierii Materiałowej");
		odpA 		= new JRadioButton();
		odpB 		= new JRadioButton();
		odpC 		= new JRadioButton();
		odp 		= new ButtonGroup();
		liczba 		= new Random();
		next 		= new JButton("Dalej");
		poprzedni 	= new JButton("Wstecz");
		info 		= new JLabel("");
		punkty 		= new JLabel("Liczba punktów:   " +punkt +"/" +ktore);
		p 			= new int[zakres];
		
		//GUI organization
		odp.add(odpA);
		odp.add(odpB);
		odp.add(odpC);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocation(400,100);
		frame.setResizable(false);
		frame.add(panel);
		frame.setVisible(true);
		
		panel.setSize(500,500);
		panel.setLocation(0,0);
		panel.setLayout(null);
		panel.add(poprzedni);
		panel.add(odpA);
		panel.add(odpB);
		panel.add(odpC);
		panel.add(punkty);
		panel.add(info);
		panel.add(tresc);
		panel.add(wyjscie);
		panel.add(next);
		panel.add(start);
		
		wyjscie.setSize(90, 40);
		wyjscie.setLocation(390, 405);
		wyjscie.addActionListener(wyjdz);
		
		start.setSize(90, 40);
		start.setLocation(280, 405);
		start.addActionListener(zacznij);
		
		tresc.setSize(450, 100);
		tresc.setLocation(35, 180);
		
		next.setSize(90, 40);
		next.setLocation(280, 405);
		next.setVisible(false);
		next.addActionListener(nastepne);
		
		poprzedni.setSize(90, 40);
		poprzedni.setLocation(170, 405);
		poprzedni.setVisible(false);
		poprzedni.addActionListener(wstecz);
		
		info.setSize(300, 20);
		info.setLocation(50, 350);
		info.setVisible(false);
		
		punkty.setSize(300, 20);
		punkty.setLocation(50, 370);
		punkty.setVisible(false);
		
		odpA.setSize(450, 80);
		odpA.setLocation(30, 90);
		odpA.setVisible(false);
		
		odpB.setSize(450, 80);
		odpB.setLocation(30, 160);
		odpB.setVisible(false);
		
		odpC.setSize(450, 80);
		odpC.setLocation(30, 230);
		odpC.setVisible(false);

	}
	
	//This function draws question number
	public void losuj() {
		do {
			nr_pytania = liczba.nextInt(zakres)+1;
			sprawdz=true;
			for (int i=0; i<ktore; i++) {
				if (p[i]==nr_pytania) {
					sprawdz=false;
				}
			}
			p[ktore] = nr_pytania;
		}
		while(sprawdz==false);
	}
	
	//quiz-starting ActionListener
	ActionListener zacznij = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			losuj();
			pyt = new QuestionReader(nr_pytania);
			start.setVisible(false);
			next.setVisible(true);
			poprzedni.setVisible(true);
			info.setVisible(true);
			punkty.setVisible(true);
			tresc.setLocation(35, 20);
			trescPytania = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 350, pyt.getPytanie());
			tresc.setText(trescPytania);
			odpowiedzA = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getA());
			odpA.setText(odpowiedzA);
			odpowiedzB = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getB());
			odpB.setText(odpowiedzB);
			odpowiedzC = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getC());
			odpC.setText(odpowiedzC);
			odpA.setVisible(true);
			odpB.setVisible(true);
			odpC.setVisible(true);
		}
	};
	
	//closing ActionListener
	ActionListener wyjdz = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
		}
	};
	
	//next question ActionListener
	ActionListener nastepne = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(odp.isSelected(null)==true) {
				info.setText("ZAZNACZ ODPOWIEDŹ");
			} else {
				poprzednie = nr_pytania;
				ktore++;
				if(odpA.isSelected()==true) {
					odpowiedz ="a";
				} else if(odpB.isSelected()==true) {
					odpowiedz ="b";
				} else if(odpC.isSelected()==true) {
					odpowiedz ="c";
				}
				punkty.setText("Liczba punktów:   " +punkt +"/" +ktore);
				if (odpowiedz.equals(pyt.getPoprawna())) {
					info.setText("Odpowiedź prawidłowa! :)");
					punkt++;
					punkty.setText("Liczba punktów:   " +punkt +"/" +ktore);
				} else {
					info.setText("Odpowiedź nieprawidłowa! :(");
				}
				losuj();
				pyt = new QuestionReader(nr_pytania);
				trescPytania = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 350, pyt.getPytanie());
				tresc.setText(trescPytania);
				odpowiedzA = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getA());
				odpA.setText(odpowiedzA);
				odpowiedzB = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getB());
				odpB.setText(odpowiedzB);
				odpowiedzC = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getC());
				odpC.setText(odpowiedzC);
				odp.clearSelection();
			}
		}
	};
	
	//previous question AcrionListener
	ActionListener wstecz = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(poprzednie == -1) {
				tresc.setText("Pytania z egzaminu kierunkowego Inżynierii Materiałowej");
				tresc.setSize(450, 70);
				tresc.setLocation(35, 200);
				odpA.setVisible(false);
				odpB.setVisible(false);
				odpC.setVisible(false);
				info.setVisible(false);
				punkty.setVisible(false);
				next.setVisible(false);
				poprzedni.setVisible(false);
				start.setVisible(true);
			} else {
				pyt = new QuestionReader(poprzednie);
				trescPytania = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 350, pyt.getPytanie());
				tresc.setText(trescPytania);
				odpowiedzA = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getA());
				odpA.setText(odpowiedzA);
				odpowiedzB = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getB());
				odpB.setText(odpowiedzB);
				odpowiedzC = String.format("<html><div style=\"width:%dpx;\">%s</div><html>", 300, pyt.getC());
				odpC.setText(odpowiedzC);
				odp.clearSelection();
				nr_pytania = poprzednie;
			}
		}
	};
	
}