package com.example.gestionefile;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Gestore {

    public final String TAG = "Gestore";
    public String leggiFile(String nomeFile, Context c){

        StringBuilder sb = new StringBuilder();
        //Oggetto che serve a creare un unione di stringhe per poter creare un oggetto unico con tutte le
        // stringhe reate, avrà metodo append (32) che contatena stringa letto con altre, stringa che mi da tutto il contenuto del file

        try {
            BufferedReader fileDaLeggere =
                    new BufferedReader(
                            new InputStreamReader
                                    (c.openFileInput(nomeFile))); //da stream di byte a caratteri

            String testoDaRestituire;

            while((testoDaRestituire = fileDaLeggere.readLine()) != null)   //Ciclo che mi permette di leggere il file fino alla fine (null)
            {
                sb.append(testoDaRestituire + "\n");
            }
        } catch (FileNotFoundException e){
            Log.e("classeGestore", "Il file non esiste");   //Per vedere nel logcat l'eventuale errore del file qual'ora inesistente
        } catch (IOException e) {
            Log.e(TAG, "impossibile leggere il file");
        }

        return sb.toString() ;
    }

    public String scriviFile(String nomeFile, Context c){   //COntesto area di memoria dove può recuperare le risorse dell'activity

        FileOutputStream fileO; //Nome del file orientata ai byte
        String esito;   //Comunico all'utente quello che succede nel processo di lettura-scrittura e chiusura
        String frase = "Questo è quello che metto dentro il file";

        try {
            //Apro il file, Gli passo il nome del file ed il contesto, in questo caso usiamo i privato (non accessibile dall'esterno)
            fileO = c.openFileOutput(nomeFile, Context.MODE_PRIVATE);   //Per aprire il file in scrittura
            //Scrittura dei byte (serializzazione della stringa: prendo struttura dati più o meno complessa e vederla come sequenza di byte)
            fileO.write(frase.getBytes(StandardCharsets.UTF_8));   //Gli passo il testo da scrivere però vuole i Bytes e quindi devo usare questo metodo per trasformarlo
            //3) Chiusura file in maniera esplicita anche se in questo caso la classe si chiude da sola (autclosable)
        fileO.close();
        esito= "file scritto correttamente";
        }
        catch (FileNotFoundException e)
        {
            esito = "File non trovato!!";
            Log.e(TAG, "File non trovato!!" );

        }
        catch (IOException e)
        {
            esito = "Impossibile scrivere il file";
            Log.e(TAG, "File non scritto");
        }

        return esito;
    }

   /* public String writeFileBW(String nomeFile, Context c){
        String esito;

        BufferedWriter br = null; //Prende un oggetto di tipo Output stream che
        try {
            br = new BufferedWriter(new OutputStreamWriter(c.openFileOutput(nomeFile, Context.MODE_APPEND)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            br.write("mio testo");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return esito;
    }
*/
}
