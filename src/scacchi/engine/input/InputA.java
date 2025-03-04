package scacchi.engine.input;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import scacchi.data.pedine.Pezzo;
import scacchi.engine.service.ScacchieraService;

public abstract class InputA {

    public static ScacchieraService scacchieraS = new ScacchieraService();
    public Pezzo undo;
    public Logger logger = LogManager.getLogger(InputA.class);
    public String logMessageGiocatore;
    public String logMessageComputer;
    public static int x, y;


}
