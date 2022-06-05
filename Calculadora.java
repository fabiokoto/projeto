package calculadora;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class Calculadora extends JFrame{
    private JButton [] bOp = new JButton[4];  // Botões Operações
    private JButton [] bDig = new JButton[10];  // Botões dos dígitos
    private JButton bponto, bresult, blimpa, bexit;  // Botões resultado, ponto, limpar e Quit
    private JTextField tela;                 // Display Calculadora
    private JLabel autor;                     // Mensagem na Calculadora
    CalculatorEngine Engine = new CalculatorEngine();
         
    public Calculadora(){  // Método construtor da classe Calculadora
        inicializar();
        eventos();
    }
 
    private void inicializar(){
        // Definindo a janela da Calculadora
        setTitle("Calculadora");
        setBounds(200,200,300,400);
        setBackground(new Color(150,150,150));
     
        // Adicionando os botões dos dígitos da calculadora
        bDig[0] = new JButton("0");
        bDig[0].setBounds(50,250,50,25);
        add(bDig[0]);
     
        int k = 50, j =100;  // Variáveis auxiliares
        for (int i = 1; i<10; i++){    // Para cada botão de dígito:
            bDig[i] = new JButton(""+i);  // Colocar o texto do dígito
            bDig[i].setBounds(k,j,50,25); // e posicionar segundo as variáveis
            add(bDig[i]);                 // auxiliares
         
            if (i%3==0) { // Chegado ao extremo da direita
                k=50;     // (Botões 3,6,9), descer uma linha
                j+=50;    // e voltar para a esquerda
            }
            else k+=50; // Ou colocar o botão mais à direita
        }
        bOp[0] = new JButton("+"); bOp[1] = new JButton("-");
        bOp[2] = new JButton("*"); bOp[3] = new JButton("/");
        for (int i = 0; i<4; i++){
            bOp[i].setBounds(200,100+50*i,50,25); add(bOp[i]);
        }
     
        // Adicionando os demais botões do programa.
 
        bponto= new JButton("."); bponto.setBounds(150,250,50,25); add(bponto);
        bresult = new JButton("="); bresult.setBounds(100,250,50,25); add(bresult);
        blimpa = new JButton("C"); blimpa.setBounds(50,300,100,25); add(blimpa);
        bexit = new JButton("Quit"); bexit.setBounds(150,300,100,25); add(bexit);
     
        tela = new JTextField(); tela.setBounds(50,50,200,30); add(tela);
        autor = new JLabel("Fábio");
        autor.setBounds(25,325,200,30); add(autor);
        setLayout(null);
     
    }
 
    private void eventos(){
        for (int i = 0; i<10; i++){
            final int j = i;
            bDig[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    tela.setText(tela.getText()+j);
                    tela.setHorizontalAlignment(JTextField.RIGHT);
                }
            });
        }
        for (int i = 0; i<4; i++) {
            final int j = i;
            bOp[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Engine.Operation(j,tela.getText());
                    tela.setText("");
                }
            });
        }
        bponto.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tela.setText(tela.getText()+".");
                tela.setHorizontalAlignment(JTextField.RIGHT);
            }
        });
        bresult.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tela.setText(""+Engine.Display(tela.getText()));
            }
        });
        blimpa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tela.setText("");
            }
        });
        bexit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
 
    public static void main(String[] args) {
        JFrame frame = new Calculadora();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
 
}