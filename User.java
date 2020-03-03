public class User {
  
    private String Benutzer;
    private String Passcode;

    User (String Benutzer, String Passcode)
    {
        this.Benutzer = Benutzer;
        this.Passcode = Passcode;
    }

    String getBenutzer() {
    return Benutzer;
    
    }
  
    String getPasscode() {
    
    return Passcode ;
  
    }
  
  }
