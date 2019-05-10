import javax.swing.JFrame;

public class Main
{
    public Main()
    {
        JFrame frame = new JFrame("Snake Game");
        Gamepanel gamepanel = new Gamepanel();
        
        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setTitle("Snake Game");
        
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
    }
    
    public static void main(String args[])
    {
       new Main();
    }
}