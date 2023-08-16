import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //create frame
    JFrame frame;
    //create menu bar for the text editor
    JMenuBar menuBar ;

    //create menu item
    JMenu file , edit ;

    //create file menu option
    JMenuItem newFile , openFile , saveFile ;

    //create edit menu option
    JMenuItem cut,copy , paste , selectAll ,close ;

    //create text area for writing
    JTextArea textArea;

    //constructor
    TextEditor(){

        // initializing frame , menu bar and text area
       frame = new JFrame("Notepad");
       menuBar = new JMenuBar();
       textArea = new JTextArea();

       //initializing menu items

       file = new JMenu("File");
       edit = new JMenu("Edit");

       newFile = new JMenuItem("New File");
       openFile = new JMenuItem("Open ");
       saveFile = new JMenuItem("Save");

       // adding action listener to operator on commands to the file option

       newFile.addActionListener(this);
       openFile.addActionListener(this);
       saveFile.addActionListener(this);

       //adding menu item in the file menu

       file.add(newFile);
       file.add(openFile);
       file.add(saveFile);

       // initalizing edit menu item
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste ");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // adding action listener to operator on commands to the edit option
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

       edit.add(cut);
       edit.add(paste);
       edit.add(selectAll);
       edit.add(close);

       //adding menu option in the menu bar
       menuBar.add(file);
       menuBar.add(edit);

       // setting the bounds of the frame

        frame.setBounds(100,100,400,400);

        //adding menubar to the frame
        frame.setJMenuBar(menuBar);

        // create content pane
        JPanel panel  = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout());

        //add text area to panel
        panel.add(textArea , BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane  = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        //add srcoll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

     //   frame.add(textArea);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==cut){
           //perform cut operation
        textArea.cut();
        }
        if(e.getSource()==copy){
            //perform copy operation
          textArea.copy();
        }
        if(e.getSource()==paste){

           textArea.paste();
        }
        if(e.getSource()==selectAll){
           textArea.selectAll();
        }
        if(e.getSource()==close) {
            System.exit(0);
        }
        if(e.getSource()==openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
             //if we have clicked on Open Button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting selected file
                File file = fileChooser.getSelectedFile();
                // get the path of the selected file
                String filePath = file.getPath();

                try{
                    //initailze file reader
                    FileReader fileReader = new FileReader(filePath);
                    // initalize buffer reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate ="" , output="";
                    //read content of file line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output+=intermediate+"/n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                } catch(IOException fileNotFoundException){
                    //handling io and file not fount expection
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(e.getSource()==saveFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");

            int chooseOption = fileChooser.showSaveDialog(null);

             //if we have clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Get the selected file with path +.txt
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");

                try{
                    //writing the text area into file
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    //adding the content to the text area
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    //handling io exception
                    ioException.printStackTrace();
                }
            }
        }
        if(e.getSource()==newFile){
            //create obj of the constructor so pop th new window
             TextEditor newTextEditor = new TextEditor();
        }
    }
    public  static void main(String args[]){
       TextEditor textEditor = new TextEditor();
    }
}
