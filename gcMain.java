//Die ausführbare Klasse, die das Fenster startet
public class gcMain extends gcWindow{
    private static final long serialVersionUID = -5265913855637863804L;
    
    //Programmstart springt in diese Funktion
    public static void main(String[] args) {
        gcMain wnd = new gcMain();
        wnd.setVisible(true);
    }
}
