import java.util.Scanner;

public class Dollar {
    public static void main(String []args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Type the maximum length: ");

        int n = scan.nextInt();
        scan.nextLine();
        System.out.println("(a)");
        Dollar(n, '$');

        System.out.println("(b)");
        Dollar(n, ' ');
    }


    public static void Dollar(int num, char tile){
        int upper= num/4+(num%4+1)/4+1;
        // upper triangle length  for given integer n if n%4=3 the length of upper triangle increases 1 other case 0,
        // Also, the length increases by quotient of n and 4
        // and 1 for default

        int lower=(upper/6+1+num/12-(13-num%13)/13);
        // lower triangle length(from hourglass shape to bottleneck)
        // For given input I could formulate the length for given integer n if n%13=12 the length of lower triangle decreases 1 other case 0,
        // Also, the length increases by quotient of upper length and 6  and quotient of n and 12
        // and 1 for default


        for(int i=1;i<=upper; i++){   // draw top
            draw(i,num, tile,false);
        }

        for(int i=Math.max(1,upper-1); i>=Math.max(1,upper-1)-lower+1;i--){  // draw middle lower
            draw(i,num,tile, true);
        }
        for(int i=Math.max(1,upper-1)-lower+2; i<=upper-1; i++){  // draw middle upper
            draw(i,num,tile, false);
        }

        for(int i=upper; i>=1; i--){  // draw bottom
            draw(i,num,tile,false);
        }
    }
    public static void draw(int i, int num, char tile, boolean atsign) {
        int l=num/4+(num%4+1)/4+1;
        int dl=(l/6+1+num/12-(13-num%13)/13);
        int last=(num-1)/2 - 2*i + 2;

        // atsign is appears when the number of pattern in bottleneck part is less or equal to the half of given integer
        if(atsign&& tile==' '&& i==Math.max(1,l-1)-dl+1 && num/2 >=num - Math.max(0, 2*last)){ // print @ when the minimum # of star is smaller than a half of given number
            for (int j = 1; j <= last-1 ; j++) {
                System.out.print(" ");
            }
            System.out.print("@");
        }
        else{
            for (int j = 1; j <= last; j++) {
                System.out.print(" ");
            }
        }

        System.out.print("$");


        for (int j = 1; j < num - Math.max(0, 2*last)-1 ; j++) {
            System.out.print(tile);
        }

        if (((num % 2 == 0) || (i != 1))) System.out.print("$");


        if(atsign&&tile==' '&&i==Math.max(1,l-1)-dl+1 && num/2 >=num - Math.max(0, 2*last)) System.out.print("@");

        System.out.println();
    }

}
