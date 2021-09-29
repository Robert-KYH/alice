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
    var lista = new HashMap<String, Integer>();

    while (in.hasNextLine()) {
      String[] ord = in.nextLine().toLowerCase().split("[^a-z]+");
      ++n_rader;

      if (ord.length == 1  &&  ord[0].equals(""))  continue;
      ++n_textrader;

      for (String o:ord) {
        ++n_ord;
        if (o.length() > längsta.length())  längsta = o;
        lista.put(o, lista.getOrDefault(o, 0)+1);
      }
    }

    in.close();
    System.out.println(String.format("%d rader, varav %d med text", n_rader, n_textrader));
    System.out.println(String.format("%d ord, varav %d unika", n_ord, lista.size()));

    lista.remove("i");
    lista.remove("a");
    lista.remove("an");
    lista.remove("the");

    String vanligaste = "";
    int n_vanligaste = 0;
    for (Map.Entry<String, Integer> e:lista.entrySet()) {
      if (e.getValue() > n_vanligaste) {
        vanligaste = e.getKey();
        n_vanligaste = e.getValue();
      }
    }

    System.out.println(String.format("Det längsta ordet är '%s', och det mest förekommande är '%s' (%d gånger)", längsta, vanligaste, n_vanligaste));
  }
}
