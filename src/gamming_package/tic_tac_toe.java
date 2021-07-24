/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamming_package;

/**
 *
 * @author IceCReam
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class tic_tac_toe extends JFrame {

    public JTextField field[];
    public JButton clearbutton, finishButton;
    public JLabel symbol1, symbol2;
    public JTextField player1, player2;
    String winnerName="Player 1 and Player 2";
    public int arr[][];
    public final int X=1;
    public final int O=4;
    public int Change;
    Random rand;

    public tic_tac_toe() {
        try {
            this.setTitle("Tic Tac Toe");
            rand=new Random();
            rand.setSeed(System.currentTimeMillis());
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            Container con = this.getContentPane();
            con.setLayout(new BorderLayout(10, 10));
            con.add(getPanel(), BorderLayout.CENTER);
            con.add(getpanel2(), BorderLayout.SOUTH);
            con.add(getPaneltop(), BorderLayout.NORTH);
            URL resource = getClass().getResource("/Images/iconn.gif");
            BufferedImage image = ImageIO.read(resource);
            this.setIconImage(image);
            this.setSize(600, 500);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            actionSet();
        } catch (IOException ex) {
            Logger.getLogger(tic_tac_toe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public JPanel getPanel() {
        JPanel mainPanel = new JPanel();
        field = new JTextField[9];
        mainPanel.setLayout(new GridLayout(3, 3, 10, 10));
        for (int i = 0; i < 9; ++i) {
            field[i] = new JTextField();
            mainPanel.add(field[i]);
            field[i].setBackground(new Color(144, 238, 144));
            field[i].setFont(new Font("My space", Font.BOLD, 38));
            field[i].setHorizontalAlignment(JTextField.CENTER);
            field[i].setCaretColor(new Color(144, 238, 144));
            final JTextField temp=field[i];
            field[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    String ss = temp.getText();
                    int n = ss.length();
                    if (n == 1 && e.getExtendedKeyCode() != KeyEvent.VK_BACK_SPACE) {
                        temp.setEditable(false);
                    } else {
                        temp.setEditable(true);
                    }
    
                }
            });

        }
        mainPanel.setBorder(BorderFactory
                .createTitledBorder("Enter only a single \"X\" and single  \"O\"  Character mustbe Capital"));
        return mainPanel;
    }


    public void getValue(){
        arr=new int [3][3];
        int k=0;
        for(int i=0;i<3;++i)
        {
            for(int j=0;j<3;++j)
            {
                if(field[k].getText().equals("X")){
                    arr[i][j]=X;
                }else if(field[k].getText().equals("O")){
                    arr[i][j]=O;
                }else{
                    arr[i][j]=0;
                    
                }
                k++;
            }
        }

    }



    public JPanel getPaneltop() {
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BorderLayout(10, 10));
        player1 = new JTextField("Player1") {
            @Override
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                tip.setBackground(new Color(204, 255, 204));
                tip.setFont(new Font("Arial", Font.PLAIN, 13));
                return tip;
            }
        };
        player2 = new JTextField("Player2") {
            @Override
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                tip.setFont(new Font("Arial", Font.PLAIN, 13));
                tip.setBackground(new Color(204, 255, 204));
                return tip;
            }
        };
        symbol1 = new JLabel("Your Symbol- \" X \"");
        symbol2 = new JLabel("Your Symbol- \" O \"");
        player1.setColumns(10);
        player2.setColumns(10);

        player1.setFont(new Font("my space", Font.BOLD, 16));
        player2.setFont(new Font("my space", Font.BOLD, 16));
        symbol1.setFont(new Font("my space", Font.BOLD, 14));
        symbol2.setFont(new Font("my space", Font.BOLD, 14));
        player1.setCaretColor(Color.WHITE);
        player2.setCaretColor(Color.WHITE);

        player1.setToolTipText("Enter Your Nick Name");
        player2.setToolTipText("Enter Your Nick Name");
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        mainpanel.add(left, BorderLayout.WEST);
        mainpanel.add(right, BorderLayout.EAST);

        left.add(player1);
        left.add(symbol1);
        right.add(player2);
        right.add(symbol2);

        return mainpanel;
    }

    public JPanel getpanel2() {
        JPanel panel = new JPanel();
        clearbutton = new JButton("CLEAR");
        finishButton = new JButton("Finish");
        ImageIcon ii=new ImageIcon(getClass().getResource("/Images/sign.gif"));
        Image im=ii.getImage();
        Image ima=im.getScaledInstance(100,70, Image.SCALE_SMOOTH);
        ImageIcon icon=new ImageIcon(ima);
        JLabel label=new JLabel(icon);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        panel.add(clearbutton);
        panel.add(finishButton);
        panel.add(label);

        return panel;
    }

    public void Clear() {
        Change=rand.nextInt(8);
        for (int i = 0; i < 9; ++i) {
            field[i].setText("");
        }
        if(Change<=4)
        {
            symbol1.setText("Your Symbol- \" O \"");
            symbol2.setText("Your Symbol- \" X \"");
        }else{
            symbol1.setText("Your Symbol- \" X \"");
            symbol2.setText("Your Symbol- \" O \"");
        }
    }

    public void actionSet() {
        clearbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Clear();
            }
        });

        for (int i = 0; i < 9; ++i) {
            field[i].addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    Character ch = e.getKeyChar();
                    if (Character.isDigit(ch) || (KeyEvent.VK_X != ch && KeyEvent.VK_O != ch)) {
                        e.consume();
                    }

                }
            });

           final int j = i;

            field[i].addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    field[j].setText("");

                }

            });

        }

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                    getValue();
                    String temp=new gameCheck().Winner(arr);
                    String first=symbol1.getText();
                    String second=symbol2.getText();
                    if(first.contains(temp)){
                        new winning(player1.getText()+" WON THE MATCH!").setVisible(true);
                        Clear();
                    }else if(second.contains(temp)){
                        new winning(player2.getText()+" WON THE MATCH!").setVisible(true);
                        Clear();
                    }else{
                        for(int i=0;i<9;++i)
                        {
                            field[i].setText("MatchTied");
                        }
                    }
                    
                
            }
        });

        player1.addKeyListener(new KeyAdapter(){
        public void keyPressed(KeyEvent e) {
            String ss=player1.getText();
            int n=ss.length();
            if (n == 8 && e.getExtendedKeyCode() != KeyEvent.VK_BACK_SPACE) {
                player1.setEditable(false);
            } else {
                player1.setEditable(true);
            }
        }
      });

      player2.addKeyListener(new KeyAdapter(){
        public void keyPressed(KeyEvent e) {
            String ss=player2.getText();
            int n=ss.length();
            if (n == 8 && e.getExtendedKeyCode() != KeyEvent.VK_BACK_SPACE) {
                player2.setEditable(false);
            } else {
                player2.setEditable(true);
            }
        }
      });

    }



    public static void main(String args[]) {
        new tic_tac_toe().setVisible(true);

    }
}



class winning extends JDialog {
String name;
    public winning(String name) {
        this.name=name;
        this.setTitle("Congratulation");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setModal(true);
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        con.add(getpanel(), BorderLayout.CENTER);
        con.add(bottom(),BorderLayout.SOUTH);
        this.setSize(350, 330);
        this.setLocationRelativeTo(null);
    }

    public JPanel getpanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(getClass().getResource("/Images/backgroud.gif")));
        JLabel namelbl=new JLabel(name,JLabel.CENTER);
        namelbl.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(namelbl);
        panel.add(label);

        return panel;
    }
    
  

    public JPanel bottom(){
        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
        JButton button=new JButton("OK");
        panel.add(button);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
        return panel;
    }
  

}


class gameCheck{
    public int arr[][];
    public final int X=1;
    public final int O=4;
 
 
     public boolean isWin(int person){
         return (
             (arr[0][0]+arr[0][1]+arr[0][2]==person*3)||
             (arr[1][0]+arr[1][1]+arr[1][2]==person*3)||
             (arr[2][0]+arr[2][1]+arr[2][2]==person*3)||
             (arr[0][0]+arr[1][0]+arr[2][0]==person*3)||
             (arr[0][1]+arr[1][1]+arr[2][1]==person*3)||
             (arr[0][2]+arr[1][2]+arr[2][2]==person*3)||
             (arr[0][0]+arr[1][1]+arr[2][2]==person*3)||
             (arr[0][2]+arr[1][1]+arr[2][0]==person*3)
         );
     }
 
     public String Winner(int arr[][]){
         this.arr=arr;
         if(isWin(X)){
            
             return "X";
         }else if(isWin(O)){
            
             return "O";
         }else return "NO";
     }
 
 }
