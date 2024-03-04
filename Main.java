import java.util.ArrayList;
import java.util.Scanner;


/* Membres du groupes : 1
 * Nom : Kheloufi
 * Prenom : Massin Aylan
 * Section et groupe : B2
 * Matricule : 212131052027
 */


// Structure du noeud de l'arbre binaire
class Node {
    String mot;
    Node left, right;

    public Node(String mot2) {
        mot = mot2; // Le mot du Node prend la valeur entre les parenthèses
        left = right = null; // Initialisation de left et right a NULL
    }
}

// structure du noeud de lc
class Nodelc {
    String mot;
    Nodelc next;
}

// Le programme principal
class Main {
    static int r = 0;
    static ArrayList<String> Arrie = new ArrayList<>();
    static ArrayList<String> Arrie2 = new ArrayList<>();
    static ArrayList<String> Arrie4 = new ArrayList<>();

    // Racine de l'arbre binaire
    Node root;

    // Constructeur
    Main() {
        root = null;
    }

    // Methode pour trouver la profondeur de l'arbre binaire
    int height(Node root) {
        if (root == null)
            return 0;
        else {
            // On prend la hauteur des deux arbres gauche et droit
            int lheight = height(root.left);
            int rheight = height(root.right);

            // On utilise la plus grande
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    // Methode pour transferer arbre binaire dans une liste chainee
    public static void balls2(Node A, Nodelc L) {
        if (A != null) {
            Nodelc L1 = new Nodelc();
            L1.mot = A.mot;
            L1.next = null;

            if (L == null) {
                L = L1;
            } else {
                Nodelc L2 = L;
                while (L2.next != null) {
                    L2 = L2.next;
                }
                L2.next = L1;
            }

            balls2(A.left, L);
            balls2(A.right, L);
        }
    }

    // Methode pour remplir l'arbre binaire avec la profondeur (La profondeur est egale a k div 3 - 1 tant que k mod 3 != 0)
    public static void Balls(Node A, int P) {
        if (P < 0) {
            A.mot = repC(A.mot);
            return;
        } else {
            A.left = new Node(repA(A.mot));
            A.right = new Node(repB(A.mot));

            Balls(A.left, P - 1);
            Balls(A.right, P - 1);
            A.mot = repC(A.mot);
        }
    }

    // Methode pour replacer A par aAaa
    public static String repA(String input) {
        return input.replace("A", "aAaa");
    }

    // Methode pour replacer A par bbAb
    public static String repB(String input) {
        return input.replace("A", "bbAb");
    }

    // Methode pour replacer A par c
    public static String repC(String input) {
        return input.replace("A", "c");
    }

    // Methode pour inverser l'ordre des lettres d'un mot
    public static String reverseString(String A) {
        StringBuilder sb = new StringBuilder(A);
        return sb.reverse().toString();
    }

    // Methode pour afficher les mots d'une profondeur donnee
    void printGivenLevel(Node root, int level, int inv) {
        if (root == null)
            return;
        if (level == 1) {
            if (inv == 2) {
                System.out.println("(k = " + root.mot.length() + ") " + reverseString(root.mot));
            } else {
                System.out.println("(k = " + root.mot.length() + ") " + root.mot);
            }
        } else if (level > 1) {
            printGivenLevel(root.left, level - 1, inv);
            printGivenLevel(root.right, level - 1, inv);
        }
    }

    // Methode pour afficher l'arbre binaire par niveau
    void printLevelOrder() {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++) {
            printGivenLevel(root, i, r);
            System.out.println();
        }
    }

    // Methode pour afficher le contenu de lc
    public static void printLinkedList(Nodelc head) {
        Nodelc current = head;
        while (current != null && current.mot != null) {
            System.out.println("(k = " + current.mot.length() + ") " + current.mot);
            current = current.next;
        }
    }

    // Methode pour copier liste chainee
    public static Nodelc copy(Nodelc head) {
        if (head == null) {
            return null;
        }

        Nodelc newHead = new Nodelc();
        newHead.mot = head.mot;
        Nodelc current = newHead;
        Nodelc originalCurrent = head;

        while (originalCurrent.next != null) {
            originalCurrent = originalCurrent.next;

            Nodelc newNode = new Nodelc();
            newNode.mot = originalCurrent.mot;
            current.next = newNode;
            current = newNode;
        }

        return newHead;
    }

    // Methode pour creer la liste chainee
    public static void runLinkedListExample(int p) {
        int taille = Arrie.size();
        Nodelc b = null;
        for(int j=0;j<taille;j++){
            Nodelc tempo = new Nodelc();
            tempo.mot = Arrie.get(j);
            tempo.next = b;
            b = tempo;
        }
        Nodelc e = new Nodelc();
        Nodelc c = e;
        Nodelc d = copy(b);

        while (p > 1) {
            for (String i : Arrie) {
                while (b != null && b.mot != null) {
                    c.mot = i + b.mot;
                    Nodelc tempoNode = new Nodelc();
                    c.next = tempoNode;
                    c = c.next;
                    b = b.next;
                }
                b = copy(d);
            }
            d = copy(e);
            b = copy(e);
            c = e;
            p--;
        }

        if (e != null) {
            printLinkedList(e);
        }
    }

    // Programme principal
    public static void main(String[] args) {
        int conf = 0;
            Main tree = new Main();
            Main tree2 = new Main();

            Scanner clavier = new Scanner(System.in);

            System.out.println("\nDonnez la longueur maximum des mots du langage initial k\n");

            int k = clavier.nextInt();

            while (k < 0) {
                System.out.println("Faux. Donnez une longueur valide.\n");
                k = clavier.nextInt();
                clavier.close();
            }

            int k2 = k / 3 - 1;

            if (k == 0) {
                tree.root = new Node("");
            }else{
                k2 = k / 3 - 1;
    
                if (k % 3 == 0) {
                    k2--;
                }
    
                tree.root = new Node("A");
    
                Balls(tree.root, k2);
                System.out.println("\nLes mots par niveau de L(G) sont :\n");
                System.out.println("\n(k = 0)\n");
                tree.printLevelOrder();
            }
            while (conf != 1) {
                
                Nodelc Laink4 = new Nodelc();
                Laink4.mot=tree.root.mot;
                Laink4.next = null;
                System.out.println("");
                balls2(tree.root, Laink4);
                Laink4 = Laink4.next;
                while(Laink4 != null)
                {
                    Arrie4.add(Laink4.mot);
                    Laink4=Laink4.next;
                }
                if (k != 0){
                Arrie4.add("");
                }
                System.out.println("L(G) = " + Arrie4);
                System.out.println("Il y a " + Arrie4.size() + " mots dans L(G).");

            System.out.println("\nChoisissez une option :");
            System.out.println("[ 1 ] Generer un nouveau L(G)");
            System.out.println("[ 2 ] Generer Lr(G) (Le langage miroir de L(G))");
            System.out.println("[ 3 ] Generer Ln(G) (Le langage puissance n de L(G))");
            System.out.println("[ 4 ] Cherchez si un mot w appartient au langage L(G).\n");

            clavier = new Scanner(System.in);
            r = clavier.nextInt();

                while (r != 1 && r != 2 && r!=3 && r!=4) {
                    System.out.println("Faux. Donnez une option valide.");
                    r = clavier.nextInt();
                }
                if (r == 1){
                    tree = new Main();
                    clavier = new Scanner(System.in);
                    System.out.println("Donnez la longueur maximum k des mots du langage.");
                    k = clavier.nextInt();
                    while (k < 0) {
                        System.out.println("Faux. Donnez une longueur valide.");
                        k = clavier.nextInt();
                        clavier.close();
                    }
                    if (k == 0) {
                        tree.root = new Node("");
                        System.out.println("\n(k = 0)\n");
                    }else{
                        k2 = k / 3 - 1;
            
                        if (k % 3 == 0) {
                            k2--;
                        }
            
                        tree.root = new Node("A");
            
                        Balls(tree.root, k2);
                        System.out.println("\nLes mots par niveau de L(G) sont :\n");
                        if (k != 0){
                        System.out.println("\n(k = 0)\n");
                        }
                        tree.printLevelOrder();
                    }
                    Arrie4.removeAll(Arrie4);
                    
                }


                if (r == 2) {

                    System.out.println("\nLes mots par niveau de Lr(G) sont :");
                    if (k!=0){
                    System.out.println("\n(k = 0)\n");
                    }

                    tree.printLevelOrder();

                    Arrie4.removeAll(Arrie4);

                }
                if (r == 3) {
                    System.out.println("");
                    Nodelc Laink = new Nodelc();
                    Laink.mot=tree.root.mot;
                    Laink.next = null;
                    System.out.print("Entrez la puissance n: ");
                    clavier = new Scanner(System.in);
                    int n = clavier.nextInt();
                    System.out.println("");
                    if (k==0){
                        System.out.println("Il y a seulement le mot vide dans L(G). Puisque le langage contient uniquement le mot vide, la concatenation avec lui même ou avec le mot vide ne change pas le langage. L^" + n + "(G) contient donc 1 mot. Le mode vide.");
                        Arrie4.removeAll(Arrie4);
                    }else{
                    balls2(tree.root, Laink);
                    Laink = Laink.next;
                    Nodelc Laink2 = copy(Laink);
                    while(Laink2 != null)
                    {
                        Arrie.add(Laink2.mot);
                        Laink2=Laink2.next;
                    }
                    if (k!=0) {
                    Arrie.add("");
                    }
                    int pow=(int)Math.pow(Arrie.size(),n);
                    System.out.println("Ln(G) = " + Arrie);
                    System.out.println("Il y a " + Arrie.size() + " mots dans L(G). L^" + n + "(G) donnera donc " + pow +" mots.\n");
                    runLinkedListExample(n);
                    Arrie4.removeAll(Arrie4);
                }
                }

                if (r == 4){
                    System.out.print("\nEntrez le mot w : ");
                    clavier = new Scanner(System.in);
                    String w = clavier.next();
                    if (w.length() == 0) {
                        System.out.println("w existe dans L(G).");
                    }
                    else {
                            boolean existe = Arrie4.contains(w);
                            if (existe){
                                System.out.println("\n(w = " + w + ") existe dans L(G).");
                            }else{
                                System.out.println("\n(w = " + w + ") n'existe pas dans L(G).");
                            }
                        }
                Arrie4.removeAll(Arrie4);
                }
                System.out.println("\n[ 0 ] Revenir aux choix precedents.\n[ 1 ] Quitter le programme.\n");
                conf = clavier.nextInt();
            }
            System.out.println("\nFin du programme.\n");
    }
}