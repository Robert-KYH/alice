package kyh.alice;

import java.io.*;
import java.util.*;


class Main {
  public static void main(String[] args) {

    Scanner in;

    try {
      in = new Scanner(new File("alice.txt"));
    } catch (IOException ex) {
      System.out.println("Filen kunde ej öppnas");
      return;
    }

    int n_rader = 0, n_textrader = 0, n_ord = 0;
    String längsta = "";
    SortedMap<String, Integer> lista = new TreeMap<String, Integer>();

    while (in.hasNextLine()) {
      String[] ord = in.nextLine().toLowerCase().replaceAll("[^a-z]+", " ").trim().split(" ");

      ++n_rader;
      if (!ord[0].isEmpty()) {
        ++n_textrader;
        n_ord += ord.length;
        for (String o:ord) {
            if (o.length() > längsta.length())  längsta = o;

            if (lista.containsKey(o))  lista.put(o, lista.get(o)+1);
            else                       lista.put(o, 1);
        }
      }

    }

    in.close();
    System.out.println(String.format("%d rader, varav %d med text", n_rader, n_textrader));
    System.out.println(String.format("%d ord, varav %d unika", n_ord, lista.size()));
    System.out.println(String.format("Det längsta ordet är '%s', och det mest förekommande är '%s' (%d gånger)", längsta, lista.firstKey(), lista.get(lista.firstKey())));
  }
}
