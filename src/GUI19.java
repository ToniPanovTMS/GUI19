import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class GUI19 extends JFrame {
    static JFrame jFrame = getFrame();//создаем форму
    static JTextField textBox = new JTextField();//создаем текстбокс
    static String meaning1="",meaning2="",operator="";
    private static double operation() {
        return switch (operator) {
            case "+" -> Double.parseDouble(meaning1) + Double.parseDouble(meaning2);
            case "-" -> Double.parseDouble(meaning1) - Double.parseDouble(meaning2);
            case "*" -> Double.parseDouble(meaning1) * Double.parseDouble(meaning2);
            case "/" -> Double.parseDouble(meaning1) / Double.parseDouble(meaning2);
            default -> 0;
        };
    }
    public static void main(String[] args) {

        JPanel jPanel1= getPanel1();
        JPanel jPanel2= getPanel2();
        jFrame.add(jPanel1);
        jFrame.add(jPanel2);

        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }
    static JFrame getFrame(){
        JFrame jFrame= new JFrame(){};
        jFrame.setTitle("GUI14");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setBounds(dim.width / 2 - 300 / 2, dim.height / 2 - 400 / 2, 300, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setLayout(new BorderLayout());
        jFrame.setResizable(false);
        return jFrame;
    }
    static JPanel getPanel1(){
        JPanel jPanel= new JPanel();
        jPanel.setBounds(0,0,285,70);
        jPanel.setLayout(new BorderLayout());
        jPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));//устанавливаем им границы

        textBox.setEnabled(false);//отключаем у него ввод
        textBox.setFont(new Font("TimesRoman", Font.PLAIN, 20));//меняем шрифт
        textBox.setDisabledTextColor(Color.BLACK);
        jPanel.add(textBox);

        return jPanel;
    }
    static JPanel getPanel2(){

        JPanel jPanel = new JPanel();
        jPanel.setBounds(0,70,285,295);
        jPanel.setLayout(new GridLayout(4,4,1,1));
        jPanel.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));

        var buttons = new ArrayList<JButton>();
        String [] nameButtons = new String[] {"1","2","3","+","4","5","6","-","7","8","9","*","C","0","=","/"};//создаем массив с названиями кнопок
        Font fontButton = new Font("TimesRoman", Font.BOLD, 35);//указываем шрифт для кнопок
        for (String name: nameButtons) {
            buttons.add(new JButton(name));//создаем кнопки и добавляем их в список
            JButton newButton = buttons.get(buttons.size() - 1);//вытаскиваем название объекта кнопки
            newButton.setFont(fontButton);//меняем шрифт
            jPanel.add(newButton);//добавляем на вторую панель
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String text = textBox.getText();
                    String presBatton = newButton.getText();
                    if(textBox.getText().equals("На ноль делить нельзя")){
                        textBox.setText("");
                    }
                    switch (presBatton){
                        case "C":
                            textBox.setText("");
                            meaning1="";
                            meaning2="";
                            operator="";
                            break;
                        case "+","-","*","/":
                            if (meaning1.length() > 0&&operator.length() == 0){//если есть первое число, но нет знака, то пишем знак
                                textBox.setText(text+presBatton);
                                operator=presBatton;
                            }
                            textBox.setText(text+presBatton);
                            operator=presBatton;
                            break;
                        case "=":
                            if (operator.equals("/")&&meaning2.equals("0")){
                                textBox.setText("На ноль делить нельзя");
                                break;}
                            if (meaning1.length() > 0&&operator.length() > 0&&meaning2.length() > 0){
                                double result = operation();
                                textBox.setText(result%1==0?Integer.toString((int) result): String.valueOf(result));
                                meaning1=textBox.getText();
                                meaning2="";
                                operator="";
                            }
                            break;
                        case "0":
                            if (meaning1.length()==0&&operator.length()==0) {
                                break;
                            }else if (meaning1.length()==0&&operator.length()==0) {
                                break;
                            }else if (operator.length()==0) {
                                meaning1+=presBatton;
                                textBox.setText(text+presBatton);
                            }else if(operator.length()!=0) {
                                meaning2 += presBatton;
                                textBox.setText(text + presBatton);
                            }
                            break;
                        default:
                            textBox.setText(text+presBatton);
                            if(operator.length() == 0&&!meaning1.equals("0")){
                                meaning1+=presBatton;
                                textBox.setText(text+presBatton);
                            }
                            if(operator.length() != 0&&!meaning2.equals("0")){
                                meaning2+=presBatton;
                                textBox.setText(text+presBatton);
                            }

                            break;

                    }
                }
            });
        }
        return jPanel;
    }

}