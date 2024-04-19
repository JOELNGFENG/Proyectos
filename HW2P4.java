//links used https://stackoverflow.com/questions/16867976/how-do-you-add-music-to-a-jframe
//Profe de verdad yo vi los videos de los que usted puso en el asignacion, pero como quiera no emtiendo y que no me estar funcionando como se agrregar una MP3 en JAVA:((((((((((((((())))))))))))))

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HW2P4 extends JFrame implements ActionListener {
    private List<String> songs;
    private int currentSong;
    private JLabel songL;
    private JButton playB;
    private JButton forwardB;
    private JButton backwardB;

    public HW2P4() {
        super("Music Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        songs = new ArrayList<>();
        songs.add("1 - River Flows In You");
        songs.add("2 - Love me");
        songs.add("3 - If I could see you again");
        songs.add("4 - Before you go");
        songs.add("5 - Someone you loved");

        currentSong = 0;
        songL = new JLabel(songs.get(currentSong));
        playB = new JButton("Play");
        forwardB = new JButton("Forward");
        backwardB = new JButton("Backward");

        playB.addActionListener(this);
        forwardB.addActionListener(this);
        backwardB.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(songL);
        panel.add(playB);
        panel.add(forwardB);
        panel.add(backwardB);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playB) {
            playSong();
        } else if (e.getSource() == forwardB) {
            playNext();
        } else if (e.getSource() == backwardB) {
            playPrevious();
        }
    }

    private void playSong() {
        System.out.println("Playing: " + songs.get(currentSong));
    }

    private void playNext() {
        currentSong = (currentSong + 1) % songs.size();
        songL.setText(songs.get(currentSong));
        playSong();
    }

    private void playPrevious() {
        currentSong = (currentSong - 1 + songs.size()) % songs.size();
        songL.setText(songs.get(currentSong));
        playSong();
    }

    public static void main(String[] args) {
        new HW2P4();
    }
}
