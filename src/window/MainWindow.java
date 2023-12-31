package window;
import enums.Hands;
import enums.Status;

import javax.swing.*;

public class MainWindow {
    
    private final JFrame frame;

    private final JLabel messageLabel;

    private final JButton rockButton;

    private final JButton scissorsButton;

    private final JButton paperButton;

    private Status playState;

    private Hands opponentHand;

    public MainWindow() {
        this.frame = new JFrame("いんじゃんほい！");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBounds(200, 200, 600, 400);

        var pane = this.frame.getContentPane();

        var canvas = new JPanel();

        canvas.setLayout(null);

        this.messageLabel = new JLabel("いんじゃん・・・");
        this.messageLabel.setBounds(20, 20, 400, 30);
        canvas.add(this.messageLabel);
        
        //-----------------    
        // ボタンを作成する
        //-----------------
        
        this.rockButton = new JButton(Hands.Rock.getDisplay());
        this.rockButton.setBounds(100, 100, 100, 40);
        this.rockButton.addActionListener((e) -> this.selectHand(Hands.Rock));
        canvas.add(this.rockButton);
        
        
        this.scissorsButton = new JButton(Hands.Scissors.getDisplay());
        this.scissorsButton.setBounds(250, 100, 100, 40);
        this.scissorsButton.addActionListener((e) -> this.selectHand(Hands.Scissors));
        canvas.add(this.scissorsButton);


        this.paperButton = new JButton(Hands.Paper.getDisplay());
        this.paperButton.setBounds(400, 100, 100, 40);
        this.paperButton.addActionListener((e) -> this.selectHand(Hands.Paper));
        canvas.add(this.paperButton);

        pane.add(canvas);
    }


    public void show() {
        this.init();
        this.frame.setVisible(true);
    }

    public void init() {
        this.opponentHand = Hands.getRandomHand();
        this.playState = Status.Wait;
    }

    public void selectHand(Hands selected) {

        if (this.playState != Status.Wait) {
            return;
        }

        switch ((selected.getNumber() - 
                    opponentHand.getNumber() + 3 ) % 3) {
            case 0:
                this.messageLabel.setText("あーいこーで・・・");
                this.init();
                break;
            case 1:
                this.messageLabel.setText(String.format
                ("相手が出したのは「%s」なのであなたの負けです。", 
                this.opponentHand.getDisplay()));
                this.playState = Status.Done;
                break;
            case 2:
                this.messageLabel.setText(String.format
                ("相手が出したのは「%s」なのであなたの勝ちです。", 
                this.opponentHand.getDisplay()));
                this.playState = Status.Done;
                break;
        }


    }
}
