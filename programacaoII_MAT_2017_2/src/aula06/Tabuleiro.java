/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula06;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Kislansky
 */
public class Tabuleiro extends javax.swing.JPanel {

    /**
     * Creates new form Tabuleiro
     */
    ArrayList<Point> pecaBrancas;
    ArrayList<Point> pecaPretas;
    ArrayList<Localizacao> loc;
    ArrayList<JButton> pecas;
    private int jogador=0;
    private int click=0;
    int retornoAnt;
    Point locaAnterior = new Point();
    Point ponto = new Point();
    ImageIcon imaV = new ImageIcon(getClass().getResource(""));
    ImageIcon imaP = new ImageIcon(getClass().getResource("preta.PNG"));
    ImageIcon imaB = new ImageIcon(getClass().getResource("branca.PNG"));
    public Tabuleiro() {
        pecaBrancas = new ArrayList<>();
        pecaPretas = new ArrayList<>();
        loc = new ArrayList<>();
        pecas = new ArrayList<>();
        initComponents();
        adicionarPecas();
    }

    
    private void adicionarPecas(){
        JButton peca;
        for(int i=0;i<=400;i+=50){
            for(int j=0;j<=400;j+=50){
                peca=new Botao();
                peca.setLocation(new Point(i,j));
                if((i==j || j==i-100 || i==j-100 || i==j-200 || j==i-200 || i==j-300 || j==i-300) && i<400){
                    peca.setBackground(Color.red);
                    if(j<=350 && j>=250){
                        pecaBrancas.add(peca.getLocation());
                        peca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aula06/branca.PNG")));
                    }else if(j<=100){
                        pecaPretas.add(peca.getLocation());
                        peca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aula06/preta.PNG")));
                    }
                }else{
                    peca.setBackground(new Color(255,255,255));
                }
                peca.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        ActionPerformed(evt);
                    }
                }); 
                this.add(peca);
                pecas.add(peca);
                
            }
        }
        
    }
    
    public void ActionPerformed(java.awt.event.ActionEvent evt) {
        JButton bt=(JButton)evt.getSource();
        Localizacao  loca = new Localizacao();
        System.out.println("X: "+bt.getX()+"  Y: "+bt.getY());
        ponto=bt.getLocation();
        if((ponto.x==ponto.y || ponto.y==ponto.x-100 || ponto.x==ponto.y-100 || ponto.x==ponto.y-200 || ponto.y==ponto.x-200 || ponto.x==ponto.y-300 || ponto.y==ponto.x-300) && ponto.x<400){
            switch(jogador){
                case 0:
                    //pecas.add(bt);
                    loca.coorde = bt.getLocation();
                    loca.peca = bt;
                    loc.add(loca);
                    jogardorBranco(bt);
                    break;
                case 1:
                   // pecas.add(bt);
                    loca.coorde = bt.getLocation();
                    loca.peca = bt;
                    loc.add(loca);
                    jogardorPreto(bt);
                    break;
            }
        }
        
    }
    
    private void jogardorBranco(JButton bt){
        Point auxLoc =bt.getLocation();
        int retorno=verificaPecaBranca(auxLoc);
        int retornoP =verificaPecaPreta(auxLoc);
        if(retornoP != -1 && click !=0){
            JOptionPane.showMessageDialog(null,"jogada invalida");
            loc.clear();
            click=0;
        }else if(click == 0 && retorno!=-1){
            locaAnterior = bt.getLocation();
            retornoAnt=retorno;
            click++;
        }else if(click != 0 && locaAnterior.y-50 == bt.getY() && (locaAnterior.x+50 == bt.getX() || locaAnterior.x-50 == bt.getX())){
             if(retorno == -1){
                 posicaoBranca(retornoAnt);
                 loc.clear();
                 click=0;
                 jogador=1;
             }else{
                 JOptionPane.showMessageDialog(null,"jogada invalida");
                 loc.clear();
                 click=0;
             }
        }else if(click != 0 && locaAnterior.y-100 == bt.getY() && locaAnterior.x+100 == bt.getX()){
            Point veri;
            veri=locaAnterior;
            veri.y-=50;
            veri.x+=50;
            int retotnoP = verificaPecaPreta(veri);
            veri.y+=50;
            veri.x-=50;
           if(retotnoP != -1){
                comerPreta(veri,retotnoP,retorno);
                loc.clear();
                click=0;
                jogador=1;
            }else{
                JOptionPane.showMessageDialog(null,"jogada invalida");
                 loc.clear();
                 click=0;
            }
        }else if(click != 0 && locaAnterior.y-100 == bt.getY() && locaAnterior.x-100 == bt.getX()){
            Point veri;
            veri=locaAnterior;
            veri.y-=50;
            veri.x-=50;
            int retotnoP = verificaPecaPreta(veri);
            veri.y+=50;
            veri.x+=50;
            if(retotnoP != -1){
                comerPreta(veri,retotnoP,retorno);
                loc.clear();
                click=0;
                jogador=1;
            }else{
                JOptionPane.showMessageDialog(null,"jogada invalida");
                 loc.clear();
                 click=0;
            }
        }else{
            JOptionPane.showMessageDialog(null,"jogada invalida");
            loc.clear();
            click=0;
        }
        //( || locaAnterior.x-100 == bt.getX())
    }
    private int verificaPecaBranca(Point bt) {
        for(int i=0;i<pecaBrancas.size();i++){
            if(pecaBrancas.get(i).equals(bt)){
                return i;
            }
        }
        return -1;
    }
    private void posicaoBranca(/*JButton bt,*/ int retorno) {
        for(int i = 0;i<loc.size();i++){
            if( true == locaAnterior.equals(loc.get(i).coorde)){
                loc.get(i).peca.setIcon(imaV);
                pecaBrancas.remove(retorno);
            }
            if( true == ponto.equals(loc.get(i).coorde)){
                loc.get(i).peca.setIcon(imaB);
                pecaBrancas.add(new Point(ponto));
            }
        }
    }
    private void comerBranca(Point bt,int posicao,int retornop){
        for(int i = 0;i<pecas.size();i++){    
            if( true == bt.equals(pecas.get(i).getLocation())){
                pecas.get(i).setIcon(imaV);
                pecaPretas.remove(retornop);
            }
            if(true == pecaBrancas.get(posicao).equals(pecas.get(i).getLocation())){
                pecas.get(i).setIcon(imaV);
                pecaBrancas.remove(posicao);
            }
            if(true == pecas.get(i).getLocation().equals(ponto)){
                pecas.get(i).setIcon(imaP);
                pecaPretas.add(new Point (ponto));
            }
            
        }
    }
    
    private void jogardorPreto(JButton bt){
        Point auxLoc =bt.getLocation();
        int retorno=verificaPecaBranca(auxLoc);
        int retornoP =verificaPecaPreta(auxLoc);
        if(retorno != -1 && click !=0){
            JOptionPane.showMessageDialog(null,"jogada invalida");
            loc.clear();
            click=0;
        }else if(click == 0 && retornoP!=-1){
            locaAnterior = bt.getLocation();
            retornoAnt=retornoP;
            click++;
        }else if(click != 0 && locaAnterior.y+50 == bt.getY() && (locaAnterior.x+50 == bt.getX() || locaAnterior.x-50 == bt.getX())){
             if(retornoP == -1){
                 posicaoPreta(retornoAnt);
                 loc.clear();
                 click=0;
                 jogador=0;
             }else{
                 JOptionPane.showMessageDialog(null,"jogada invalida");
                 loc.clear();
                 click=0;
             }
        }else if(click != 0 && locaAnterior.y+100 == bt.getY() && locaAnterior.x+100 == bt.getX()){
            Point veri;
            veri=locaAnterior;
            veri.y+=50;
            veri.x+=50;
            int retotnoB = verificaPecaBranca(veri);
            veri.y-=50;
            veri.x-=50;
           if(retotnoB != -1){
                comerBranca(veri,retotnoB,retornoAnt);
                loc.clear();
                click=0;
                jogador=0;
            }else{
                JOptionPane.showMessageDialog(null,"jogada invalida");
                 loc.clear();
                 click=0;
            }
        }else if(click != 0 && locaAnterior.y+100 == bt.getY() && locaAnterior.x-100 == bt.getX()){
            Point veri;
            veri=locaAnterior;
            veri.y+=50;
            veri.x-=50;
            int retotnoB = verificaPecaBranca(veri);
            veri.y-=50;
            veri.x+=50;
            if(retotnoB != -1){
                comerBranca(veri,retotnoB,retornoAnt);
                loc.clear();
                click=0;
                jogador=0;
            }else{
                JOptionPane.showMessageDialog(null,"jogada invalida");
                 loc.clear();
                 click=0;
            }
        }else{
            JOptionPane.showMessageDialog(null,"jogada invalida");
            loc.clear();
            click=0;
        }
        //( || locaAnterior.x-100 == bt.getX())
    }
    private int verificaPecaPreta(Point bt) {
        for(int i=0;i<pecaPretas.size();i++){
            if(pecaPretas.get(i).equals(bt)){
                return i;
            }
        }
        return -1;
    }
    private void posicaoPreta(/*JButton bt,*/ int retorno) {
        for(int i = 0;i<loc.size();i++){
            if( true == locaAnterior.equals(loc.get(i).coorde)){
                loc.get(i).peca.setIcon(imaV);
                pecaPretas.remove(retorno);
            }
            if( true == ponto.equals(loc.get(i).coorde)){
                loc.get(i).peca.setIcon(imaP);
                pecaPretas.add(new Point(ponto));
            }
        }
    }
    private void comerPreta(Point bt,int posicao,int retorno){
        for(int i = 0;i<pecas.size();i++){    
            if( true == bt.equals(pecas.get(i).getLocation())){
                pecas.get(i).setIcon(imaV);
                pecaBrancas.remove(retorno);
            }
            if(true == pecaBrancas.get(posicao).equals(pecas.get(i).getLocation())){
                pecas.get(i).setIcon(imaV);
                pecaPretas.remove(posicao);
            }
            if(true == pecas.get(i).getLocation().equals(ponto)){
                pecas.get(i).setIcon(imaB);
                pecaBrancas.add(new Point (ponto));
            }
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 51, 255));
        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    

    

    

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
