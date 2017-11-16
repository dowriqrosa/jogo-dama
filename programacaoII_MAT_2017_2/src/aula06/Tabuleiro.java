/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;


/**
 *
 * @author Dowriqrosa
 */
public class Tabuleiro extends javax.swing.JPanel {

    /**
     * Creates new form Tabuleiro
     */
    Point ponto1 = new Point();
    private final Point pontoAnte = new Point();
    //Point posicao = new Point();
    int jogador=1;
    int contClickB=0;
    int contClickP=0;
    int pontoy=0;
    int pontox=0;
    private int quantiPecaB=13;
    private int quantiPecaP=13;
    ArrayList<JButton> pecas;
    ArrayList<Point> pecaPretas;
    ArrayList<Point> pecaBrancas;
    ArrayList<Localizacao> loc;
    public Tabuleiro() {
        initComponents();
        pecas=new ArrayList<>();
        pecaPretas=new ArrayList<>();
        pecaBrancas=new ArrayList<>();
        loc = new ArrayList<>();
        adicionarTabuleiro();
    }
    JButton peca; 
    
    private void adicionarTabuleiro(){
        for(int i=0;i<=400;i+=50){
            for(int j=0;j<=400;j+=50){
                peca=new Botao();
                if(i==j || j==i-100 || i==j-100 || i==j-200 || j==i-200 || i==j-300 || j==i-300){
                   peca.setBackground(Color.red);    
                }
                if((i==j || j==i-100 || i==j-100 || i==j-200 || j==i-200 || i==j-300 || j==i-300)&&(j<=350 && j>=250)){
                    peca.setBackground(Color.red);
                    peca.setLocation(new Point(i,j));
                    pecaBrancas.add(new Point(i,j));
                    peca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aula06/branca.PNG")));  
                    this.add(peca);
//                    peca.addActionListener(new java.awt.event.ActionListener() {
//                        public void actionPerformed(java.awt.event.ActionEvent evt) {
//                            ActionPerformed(evt);
//                        }
//                    }); 
                   // System.out.println(pecaBrancas);
                }else if((i==j || j==i-100 || i==j-100 || i==j-200 || j==i-200 || i==j-300 || j==i-300 || (i==400 && j==0)) && j<=100){
                    peca.setBackground(Color.red);
                    peca.setLocation(new Point(i,j));
                    peca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aula06/preta.PNG")));  
                    this.add(peca);
                    pecaPretas.add(new Point(i,j));
//                    peca.addActionListener(new java.awt.event.ActionListener() {
//                        public void actionPerformed(java.awt.event.ActionEvent evt) {
//                            ActionPerformed(evt);
//                        }
//                    }); 
                }else{
                    peca.setLocation(new Point(i,j));
                    this.add(peca);
                    pecas.add(peca);
                }
                peca.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ActionPerformed(evt);
                    }
                });
            }
        }
//        pecaBrancas.add(new Point(50,350));
//        pecaBrancas.add(new Point(150,350));
//        pecaBrancas.add(new Point(250,350));
//        pecaBrancas.add(new Point(350,350));
//        pecaBrancas.add(new Point(0,300));
//        pecaBrancas.add(new Point(100,300));
//        pecaBrancas.add(new Point(200,300));
//        pecaBrancas.add(new Point(300,300));
//        pecaBrancas.add(new Point(400,300));
//        pecaBrancas.add(new Point(50,250));
//        pecaBrancas.add(new Point(150,250));
//        pecaBrancas.add(new Point(250,250));
//        pecaBrancas.add(new Point(350,250));
//        System.out.println(pecaBrancas);
    }
    public void ActionPerformed(java.awt.event.ActionEvent evt) {
        Localizacao  loca = new Localizacao();
        JButton bt=(JButton)evt.getSource();
        System.out.println("X: "+bt.getX()+"  Y: "+bt.getY());
        ponto1.x = bt.getX();
        ponto1.y = bt.getY();
        loca.ponto=bt.getLocation();
        loca.peca=bt;
        loc.add(loca);
        if(jogador==1)
            jogadorBranco(bt);
        else
            jogadorPreto(bt);
    }
    
    public void jogadorPreto(JButton bt){
        if(ponto1.x==ponto1.y || ponto1.y==ponto1.x-100 || ponto1.x==ponto1.y-100 || ponto1.x==ponto1.y-200 || ponto1.y==ponto1.x-200 || ponto1.x==ponto1.y-300 || ponto1.y==ponto1.x-300||(ponto1.x==400 && ponto1.y==0)){
            if(contClickP==0 && -1!=verificaPecaPreta(ponto1)){
                ImageIcon ima = new ImageIcon(getClass().getResource(""));
                bt.setIcon(ima);
                contClickP++; 
                pontoAnte.y=ponto1.y;
                pontoAnte.x=ponto1.x;
            }else if((ponto1.y==pontoAnte.y+100)&&(ponto1.x==pontoAnte.x+100 /*|| ponto1.x==pontoAnte.x-100*/) && 0 != contClickP){
                Point ponto = new Point();
                ponto=pontoAnte;
                ponto.y+=50;
                ponto.x+=50;
                int posicao = verificaPecaBranca(ponto);
                if(posicao!=-1){
                    //pecaBrancas.get(posicao).;    
                    apagaPecaBranca(posicao);
                    jogador = 1;
                    jLabel1.setText("jogador "+jogador);
                    contClickP=0;
                    ImageIcon ima = new ImageIcon(getClass().getResource("preta.PNG"));
                    bt.setIcon(ima);
                    mudaLocalizacaoPreta();
                }else
                    JOptionPane.showMessageDialog(null,"jogada invalida");
            }else if((ponto1.y==pontoAnte.y+100)&&(/*ponto1.x==pontoAnte.x+100 ||*/ ponto1.x==pontoAnte.x-100) && 0 != contClickP){
                Point ponto = new Point();
                ponto=pontoAnte;
                ponto.y+=50;
                ponto.x-=50;
                int posicao = verificaPecaBranca(ponto);
                if(posicao!=-1){
                    apagaPecaBranca(posicao);
                    jogador = 1;
                    jLabel1.setText("jogador "+jogador);
                    contClickP=0;
                    ImageIcon ima = new ImageIcon(getClass().getResource("preta.PNG"));
                    bt.setIcon(ima); 
                    mudaLocalizacaoPreta();
                }else
                    JOptionPane.showMessageDialog(null,"jogada invalida"); 
            }else if((ponto1.y==pontoAnte.y+50)&&(ponto1.x==pontoAnte.x+50 || ponto1.x==pontoAnte.x-50) && 0 != contClickP){
                if(-1!=verificaPecaPreta(ponto1)){
                    JOptionPane.showMessageDialog(null,"jogada invalida");
                }else{
                    contClickP=0;
                    ImageIcon ima = new ImageIcon(getClass().getResource("preta.PNG"));
                    bt.setIcon(ima);  
                    jogador = 1;
                    jLabel1.setText("jogador "+jogador);
                    mudaLocalizacaoPreta();
                }   
            }else if(pontoAnte.y==ponto1.y &&  pontoAnte.x==ponto1.x){
                contClickP=0; 
                ImageIcon ima = new ImageIcon(getClass().getResource("preta.PNG"));
                bt.setIcon(ima);
            }
        }
    }
    public void jogadorBranco(JButton bt){
        if((ponto1.x==ponto1.y || ponto1.y==ponto1.x-100 || ponto1.x==ponto1.y-100 || ponto1.x==ponto1.y-200 || ponto1.y==ponto1.x-200 || ponto1.x==ponto1.y-300 || ponto1.y==ponto1.x-300)){
            if(contClickB==0 && -1!=verificaPecaBranca(ponto1)){
                bt.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));  
                contClickB++;
                pontoAnte.y=ponto1.y;
                pontoAnte.x=ponto1.x;
            }else if((ponto1.y==pontoAnte.y-50)&&(ponto1.x==pontoAnte.x-50 || ponto1.x==pontoAnte.x+50) && contClickB != 0){
                if(-1!=verificaPecaBranca(ponto1)){
                    JOptionPane.showMessageDialog(null,"jogada invalida");
                }
                else{
                    ImageIcon ima = new ImageIcon(getClass().getResource("branca.PNG"));
                    bt.setIcon(ima);
                    jogador = 2;
                    jLabel1.setText("jogador "+jogador);
                    contClickB=0;
                    mudaLocalizacaoBranca();
                }
            }else if(pontoAnte.y==ponto1.y &&  pontoAnte.x==ponto1.x && -1!=verificaPecaBranca(ponto1) && contClickB != 0){
                contClickB=0; 
                ImageIcon ima = new ImageIcon(getClass().getResource("branca.PNG"));
                bt.setIcon(ima);
            }
        }  
    }
    public int verificaPecaBranca(Point veri){
       for(int i=0;i<quantiPecaB;i++){
            if( true ==pecaBrancas.get(i).equals(veri)){
                return i;
            }
        }
        return -1;
    }
    public int verificaPecaPreta(Point veri){
       for(int i=0;i<pecaPretas.size();i++){
            if( true ==pecaPretas.get(i).equals(veri)){
                return i;
            }
        }
        return -1;
    }
    public void mudaLocalizacaoBranca(){
        for(int i=0;i<pecaBrancas.size();i++){
            if( true ==pontoAnte.equals(pecaBrancas.get(i))){
                pecaBrancas.remove(i);
                pecaBrancas.add(new Point(ponto1));
            }
        }   
    }
    public void mudaLocalizacaoPreta(){
        for(int i=0;i<pecaPretas.size();i++){
            if( true == pontoAnte.equals(pecaPretas.get(i))){
                pecaPretas.remove(i);
                pecaPretas.add(new Point(ponto1));
            }
        }
    }
    public void apagaPecaBranca(int posicao){
       for (int i=0; i<loc.size();i++){
           if(true == loc.get(i).ponto.equals(pecaBrancas.get(posicao))){
              ImageIcon ima = new ImageIcon(getClass().getResource(""));
              loc.get(i).peca.setIcon(ima);
               //ImageIcon ima = new ImageIcon(getClass().getResource(""));
               //loc.get(i).peca.setIcon(new javax.swing.ImageIcon(getClass().getResource("")));
               //loc.get(i).peca.setBackground(Color.yellow);
           }
       }
       pecaBrancas.remove(posicao);
       quantiPecaB--;
       if(quantiPecaB ==0){
           JOptionPane.showMessageDialog(null,"jogador "+jogador+" ganhou!");
       }
    }
    public void apagaPecaPreta(int posicao){
       pecaPretas.remove(posicao);
       quantiPecaP--;
       if(quantiPecaP ==0){
           JOptionPane.showMessageDialog(null,"jogador "+jogador+" ganhou!");
       }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 100));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 102));
        jLabel1.setText("jogador 1");
        add(jLabel1);
        jLabel1.setBounds(460, 170, 76, 22);

        jTextField1.setText("nome jogador 1");
        add(jTextField1);
        jTextField1.setBounds(450, 280, 120, 30);

        jTextField3.setText("nome jogador 2");
        add(jTextField3);
        jTextField3.setBounds(450, 30, 120, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void add(ArrayList<JButton> pecas) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
