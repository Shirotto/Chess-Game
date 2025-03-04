# Scacchi Binary
Per la realizzazione di questo gioco abbiamo scelto di utilizzare java swing,
Appena avviato avremo come prima cosa il menu principale dove poter scegliere diverse opzioni,


![1.png](src%2Fresources%2F1.png)


troviamo il giocatore vs giocatore,

![2.png](src%2Fresources%2F2.png)

all'avvio di questa modalità potremo giocare contro un altro player senza nessun problema, dureante la partita possiamo decidere
di salvare il gioco, di arrenderci(con popup interattivo)

![4.png](src%2Fresources%2F4.png)

e infine possiamo decidere di tornare indietro di qualche mossa(max 5), l'implementazione dell'undo
per mancanza di tempo è inaccurata(al primo click non viene effettuato l'undo, dal secondo click la pedina torna effettivamente indietro fino a un massimo di 5 pedine, fare attenzione a fare sempre almeno un mossa in piu rispetto a quanto si vuole tornare indietro, ex. se si vuole tornare indietro di 3 mosse bisogna farne 4),
troviamo anche il log delle mosse in tempo reale su schermo,

![3.png](src%2Fresources%2F3.png)

dove vedremo la pedina spostata, il colore della pedina spostata e infine la posizione da dove è stata spostata a dove è stata spostata,
gestiamo il log dei file anche in un file .txt che viene creato dentro la cartella listalog all'interno del progetto e rispetto al log mostrato su schermo è molto piu accurato e preciso.

Troviamo una seconda modalità di gioco, il giocatore vs computer(il bot che muove le mosse è elementare, fa mosse ripetitive, sposta avanti e indietro la sua torre che non bisogna mangiare altrimenti verrà sollevata un'eccezzione e il gioco andrà in blocco),

Terzo tasto nel menu principale è il bottone che aprirà un JfileChooser con la lista delle partite salvate,
troveremo una serie di file .chess con estensione personalizzata e un filtro in modo tale che si potranno selezionare solo file di tipo .chess.

![5.png](src%2Fresources%2F5.png)

selezionato il file e cliccato il tasto open potremo continuare la partita salvata in precedenza.

Infine troviamo il tasto ComparaPartite che permette di selezionare quanti file salvati si vuole, e di poter compararli in due diversi modi:


![6.png](src%2Fresources%2F6.png)

Il numero pedine stamperà su console il salvataggio con il numero di pedine maggiore 
ed infine il valore pedine stamperà su console il salvataggio con il valore delle pedine maggiore.

![chess.png](src%2Fresources%2Fchess.png)