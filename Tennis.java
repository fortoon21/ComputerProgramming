import java.util.Scanner;

public class Tennis {
    public static void main(String []args){
        Scanner scan=new Scanner(System.in);

        System.out.print("Type the match (A: Australian Open/U: US Open): ");
        String where= scan.nextLine();
        System.out.print("Type the gender (F: Female/M: Male): ");
        String gender= scan.nextLine();

        Score(Chosen(where, gender));

        System.out.println("Game finished!");
    }

    public static int[] Chosen(String where, String gender){
        String [] match={"Australian Open", "US Open", "Female", "Male"};
        String open;
        String gender2;
        int[] rule=new int[2];   // rule[0] is total set number  rule[1] is whether the tie break would be held in last set 0-false 1-true
        if(where.equals("A")){
            open=match[0];
            rule[1]=0;
            if(gender.equals("F")) {
                gender2=match[2];
                rule[0]=3;
            }
            else{
                gender2=match[3];
                rule[0]=5;
            }
        }
        else{
            open=match[1];
            rule[1]=1;
            if(gender.equals("F")) {
                gender2=match[2];
                rule[0]=3;
            }
            else {
                gender2=match[3];
                rule[0]=5;
            }
        }
        System.out.println(open+"/"+gender2+" chosen.");
        System.out.println("Current: 0-0");
        return rule;
    }

    public static void Score(int [] rule){
        boolean flag=false;
        boolean lwin;
        String tieresult;
        int setl=0;
        int setr=0;
        int gamel=0;
        int gamer=0;

        String scoreboard="Current:";
        while (Math.max(setl,setr)!=(rule[0]+1)/2){  // when one of player get set point larger than half of given number of set play game ends.
            while(true){
                if(Doeslgetgame(scoreboard+" "+gamel+"-"+gamer)) gamel++;
                else gamer++;
                System.out.println(scoreboard+" "+gamel+"-"+gamer);
                if(gamel==6&& gamer==6 && (!((setl+setr)==rule[0]-1) || rule[1]==1 )){  // Except Australian open at last set, gamel, gamer in 6-6 situation -> tiebreak!
                    tieresult=tiebreak(scoreboard+" "+gamel+"-"+gamer);
                    if(tieresult.startsWith("0"))   gamel++;
                    else gamer++;
                    scoreboard=scoreboard+" "+gamel+"-"+gamer+tieresult.substring(1);
                    System.out.println(scoreboard);
                    break;
                }
                if(Math.abs(gamel-gamer)>=2 && (gamel>=6||gamer>=6)){
                    scoreboard=scoreboard+" "+gamel+"-"+gamer;
                    break;
                }
            }
            if(gamel>gamer) setl++;
            else setr++;
            gamel=0;
            gamer=0;
        }
    }

    public static boolean Doeslgetgame(String current){  //get game score and return winner side true-left false-right
        String [] point={"0","15","30","40", "40A"};
        Scanner side=new Scanner(System.in);
        int pl=0;
        int pr=0;
        boolean deuce=false;
        String winner;
        while (true){
            System.out.print("Type the winner (L: Left/R: Right): ");
            winner=side.nextLine();
            if(winner.compareTo("L")==0)    pl++;
            else    pr++;
            if(pl>3||pr>3){
                if(Math.abs(pl-pr)<2) deuce=true;
                break;
            }
            System.out.println(current+"("+point[pl]+"-"+point[pr]+")");
        }
        if(deuce){
            System.out.println(current+"("+point[pl]+"-"+point[pr]+")");
            while (true){
                System.out.print("Type the winner (L: Left/R: Right): ");
                winner=side.nextLine();
                if(winner.compareTo("L")==0)    pl++;
                else    pr++;
                if(Math.abs(pl-pr)==2) break;
                if(pl==pr) {  // as string [] is finite -> synchronize
                    pl=3;
                    pr=3;
                }
                System.out.println(current+"("+point[pl]+"-"+point[pr]+")");
            }
        }
        return pl>pr;
    }

    public static String tiebreak(String current){  // return which side won, tie break result    0-left  1-right
        Scanner side=new Scanner(System.in);
        int pl=0;
        int pr=0;

        String winner;
        int whoiswinner=0;

        while (true){
            System.out.print("Type the winner (L: Left/R: Right): ");
            winner=side.nextLine();
            if(winner.compareTo("L")==0)    pl++;
            else    pr++;

            if((pl>=7||pr>=7)&&Math.abs(pl-pr)>=2) break;   // when it's not deuce situation break
            System.out.println(current+"("+pl+"-"+pr+")");
        }
        if(pl<pr) whoiswinner=1;

        return whoiswinner+"("+pl+"-"+pr+")";
    }
}
