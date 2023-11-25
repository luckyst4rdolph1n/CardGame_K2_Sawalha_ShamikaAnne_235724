public class SimpleApp {
    
    public static void main(String[] args) {
        SimpleGUI gui = new SimpleGUI(800, 600);
        gui.setUpGUI();
        gui.gameListeners();
    }
}
